package com.manjushira.service.impl;

import com.manjushira.mapper.admin.RuleMapper;
import com.manjushira.pojo.admin.po.Rule;
import com.manjushira.service.RuleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 后台权限管理
 */

@Service
public class RuleServiceImpl implements RuleService{

    @Resource
    private RuleMapper ruleMapper;

    /**
     * 通过ID查找角色
     * @param id
     * @return
     */
    public Rule findById(String id) {
        return ruleMapper.selectById(id);
    }


    /**
     * 获取角色
     * @param Role
     * @return
     */
    public List<Rule> selectRoles(Rule Role){ return ruleMapper.selectList(null);}

    /**
     * 新增角色
     * @param Rule
     */
    public void insertRole(Rule Rule) {
        ruleMapper.insert(Rule);
    }

    /**
     * 修改角色
     * @param Role
     */
    public void updateRole(Rule Role) {
        ruleMapper.updateById(Role);
    }

    /**
     * 删除角色
     * @param id
     */
    public void deleteRole(String id) {
        ruleMapper.deleteById(id);
    }

}
