package com.openthinks.demo.springbootmybatisgeneratortemplate.core.service;

import java.util.List;

import com.openthinks.demo.springbootmybatisgeneratortemplate.core.AuthRole;
import com.openthinks.demo.springbootmybatisgeneratortemplate.core.model.SystemRole;
import com.openthinks.demo.springbootmybatisgeneratortemplate.core.model.SystemUser;
import com.openthinks.demo.springbootmybatisgeneratortemplate.core.model.SystemUserExample;
import com.openthinks.demo.springbootmybatisgeneratortemplate.core.model.SystemUserRole;

import net.sourceforge.orm.mybatis.Page;

/**
 * ClassName: SystemUserService </br>
 * Function: System user and role service. </br>
 */
public interface SystemUserService {

  /**
   * 
   * getSystemUser: get {@link SystemUser} by key. </br>
   * 
   * @param uid
   * @return {@link SystemUser}
   */
  public SystemUser getSystemUser(Long uid);

  /**
   * get {@link SystemUser} by username
   */
  public SystemUser getSystemUser(String username);

  /**
   * 
   * findSystemUser:get {@link SystemUser} by user name and pass. </br>
   * 
   * @param uname
   * @param upass
   * @return {@link SystemUser}
   */
  public SystemUser findSystemUser(String uname, String upass);

  public long countSystemUser(SystemUserExample example);

  public List<SystemUser> listSystemUser(SystemUserExample example, Page<SystemUser> page);

  /**
   * 
   * saveSystemUser:insert or update {@link SystemUser}. </br>
   * 
   * @param record {@link SystemUser}
   * @return {@link SystemUser}
   */
  public SystemUser saveSystemUser(SystemUser record);

  /**
   * 
   * removeSystemUser: remove {@link SystemUser} by key and remove all related SystemUserRole. </br>
   * 
   * @param uid
   * @return true or false
   */
  public boolean removeSystemUser(Long uid);

  /**
   * 
   * getSystemRole:get {@link SystemRole} by key. </br>
   * 
   * @param rid
   * @return {@link SystemRole}
   */
  public SystemRole getSystemRole(Long rid);

  /**
   * 
   * saveSystemRole:insert or update {@link SystemRole}. </br>
   * 
   * @param record {@link SystemRole}
   * @return {@link SystemRole}
   */
  public SystemRole saveSystemRole(SystemRole record);

  /**
   * 
   * removeSystemRole:remove {@link SystemRole} by key and remove all related SystemUserRole. </br>
   * 
   * @param rid
   * @return true or false
   */
  public boolean removeSystemRole(Long rid);

  /**
   * 
   * listAllSystemRoles: list all of {@link SystemRole}. </br>
   * 
   * @return
   */
  public List<SystemRole> listAllSystemRoles();

  /**
   * 
   * listSystemRolesByUser:list assigned user's roles. </br>
   * 
   * @param uid user id
   * @return
   */
  public List<SystemRole> listSystemRolesByUser(Long uid);

  /**
   * 
   * hasRole:check assigned user has role or not . </br>
   * 
   * @param uid checked user id
   * @param rid need check has role id
   * @return true or false
   */
  public boolean hasRole(Long uid, Long rid);

  /**
   * 
   * saveSystemUserRole:insert or update {@link SystemUserRole}. </br>
   * 
   * @param record {@link SystemUserRole}
   * @return {@link SystemUserRole}
   */
  public SystemUserRole saveSystemUserRole(SystemUserRole record);

  /**
   * 
   * removeSystemUserRole:remove {@link SystemUserRole} by key. </br>
   * 
   * @param urid
   * @return
   */
  public boolean removeSystemUserRole(Long urid);

  /**
   * 
   * removeSystemUserRoleByRole:remove all {@link SystemUserRole} which related role id is assigned.
   * </br>
   * 
   * @param rid role id
   * @return
   */
  public boolean removeSystemUserRoleByRole(Long rid);

  /**
   * 
   * removeSystemUserRoleByUser:remove all {@link SystemUserRole} which related user id is assigned.
   * </br>
   * 
   * @param uid user id
   * @return
   */
  public boolean removeSystemUserRoleByUser(Long uid);

  public boolean saveSystemUser(SystemUser user, List<AuthRole> authRoles);

}
