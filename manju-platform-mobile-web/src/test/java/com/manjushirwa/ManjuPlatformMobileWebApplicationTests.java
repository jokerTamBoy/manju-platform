//package com.manjushirwa;
//
//import com.manjushirwa.pojo.mobile.po.Users;
//import com.manjushirwa.service.api.UsersService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.annotation.Resource;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.is;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class ManjuPlatformMobileWebApplicationTests {
//
//	private static final Logger log = LoggerFactory.getLogger(ManjuPlatformMobileWebApplicationTests.class);
//
//	@Resource
//	private UsersService usersService;
//
//	/**
//	 * 测试增删改查
//	 */
//	@Test
//	public void test() {
//		// 核心数据库中的用户id=1
//		Users users = usersService.findById(1);
//		assertThat(users.getUsername(), is("admin"));
//
//		// biz数据库中的用户id=1
//		Users users1 = usersService.findById1(1);
//		assertThat(users1.getUsername(), is("admin1"));
//	}
//
//}
