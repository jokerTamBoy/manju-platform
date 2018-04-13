package com.manjushirwa.kits.shiro;

import com.manjushirwa.pojo.admin.po.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Description: 验证码过滤器此过滤器已经在shiro中配置，这里不需要再次配置拦截路径
 */
public class LoginFilter extends FormAuthenticationFilter {

    public static final String DEFAULT_CAPTCHA_PARAM = "captcha";

    private String captchaParam = DEFAULT_CAPTCHA_PARAM;

    private static final Logger _logger = LoggerFactory.getLogger(LoginFilter.class);

    //登录验证
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response)
            throws Exception {

        UsernamePasswordToken token = createToken(request, response);
        try {
            _logger.info("loginFilter.executeLogin");

            Subject subject = getSubject(request, response);
            subject.login(token);//正常验证

            //到这里就算验证成功了,把用户信息放到session中
            User user = ShiroKit.getUser();
            ((HttpServletRequest) request).getSession().setAttribute("user", user);

            return onLoginSuccess(token, subject, request, response);

        } catch (AuthenticationException e) {
            return onLoginFailure(token, e, request, response);
        }
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject,
                                     ServletRequest request, ServletResponse response) throws Exception {
        issueSuccessRedirect(request, response);
        //we handled the success redirect directly, prevent the chain from continuing:
        return false;
    }

    protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
        WebUtils.issueRedirect(request, response, "/", null, true);
    }

    @Override
    protected UsernamePasswordToken createToken(ServletRequest request, ServletResponse response) {

        String username = getUsername(request);
        String password = getPassword(request);
        String captcha = getCaptcha(request);
        boolean rememberMe = isRememberMe(request);
        String host = getHost(request);

        return new UsernamePasswordToken(username, password.toCharArray(), rememberMe, host);
    }

    public String getCaptchaParam() {
        return captchaParam;
    }

    public void setCaptchaParam(String captchaParam) {
        this.captchaParam = captchaParam;
    }

    protected String getCaptcha(ServletRequest request) {
        return WebUtils.getCleanParam(request, getCaptchaParam());
    }

    //保存异常对象到request
    @Override
    protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
        request.setAttribute(getFailureKeyAttribute(), ae);
    }

}
