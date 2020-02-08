package com.wisdom.controller;

import com.wisdom.entity.UsersEntity;
import com.wisdom.utils.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

/**
 * Controller公共组件
 *
 */
@Slf4j
public abstract class AbstractController {
    protected Logger logger = log;


    /**
     * 当前登录用户
     *
     * @return SysUserEntity
     */
    protected UsersEntity getUser() {
        return ShiroUtils.getUserEntity();
    }

    /**
     * 当前登录用户ID
     *
     * @return userId
     */
    protected int getUserId() {
        return getUser().getUid();
    }

    /**
     * 数据权限构造
     *
     * @return DataScope
     */
  /*  protected DataScope getDataScope() {
        DataScope dataScope = new DataScope();
//        dataScope.setOrgNos(sysRoleOrgService.queryOrgNoListByUserId(getUserId()));
        return dataScope;
    }*/

    /**
     * 数据权限构造
     *
     * @return DataScope
     */
   /* protected DataScope getDataScope(String userAlias, String orgAlias) {
        DataScope dataScope = new DataScope();
        dataScope.setUserAlias(userAlias);
        dataScope.setOrgAlias(orgAlias);
//        dataScope.setOrgNos(sysRoleOrgService.queryOrgNoListByUserId(getUserId()));
        return dataScope;
    }*/
}
