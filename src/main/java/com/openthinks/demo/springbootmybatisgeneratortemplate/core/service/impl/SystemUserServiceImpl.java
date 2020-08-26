package com.openthinks.demo.springbootmybatisgeneratortemplate.core.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openthinks.demo.springbootmybatisgeneratortemplate.core.AuthRole;
import com.openthinks.demo.springbootmybatisgeneratortemplate.core.mapper.SystemRoleMapper;
import com.openthinks.demo.springbootmybatisgeneratortemplate.core.mapper.SystemUserMapper;
import com.openthinks.demo.springbootmybatisgeneratortemplate.core.mapper.SystemUserRoleMapper;
import com.openthinks.demo.springbootmybatisgeneratortemplate.core.model.SystemRole;
import com.openthinks.demo.springbootmybatisgeneratortemplate.core.model.SystemRoleExample;
import com.openthinks.demo.springbootmybatisgeneratortemplate.core.model.SystemUser;
import com.openthinks.demo.springbootmybatisgeneratortemplate.core.model.SystemUserExample;
import com.openthinks.demo.springbootmybatisgeneratortemplate.core.model.SystemUserRole;
import com.openthinks.demo.springbootmybatisgeneratortemplate.core.model.SystemUserRoleExample;
import com.openthinks.demo.springbootmybatisgeneratortemplate.core.service.SystemUserService;

import net.sourceforge.orm.mybatis.Page;

/**
 * ClassName: SystemUserServiceImpl </br>
 */
@Service
public class SystemUserServiceImpl implements SystemUserService {
  private static final Logger LOGGER = LoggerFactory.getLogger(SystemUserServiceImpl.class);
  @Autowired
  SystemUserMapper userMapper;
  @Autowired
  SystemRoleMapper roleMapper;
  @Autowired
  SystemUserRoleMapper userRoleMapper;

  @Override
  public SystemUser getSystemUser(Long uid) {
    return userMapper.selectByPrimaryKey(uid);
  }

  @Override
  public SystemUser getSystemUser(String username) {
    SystemUserExample systemUserExample = new SystemUserExample();
    systemUserExample.createCriteria().andUserNameEqualTo(username);
    return userMapper.selectOneByExample(systemUserExample);
  }

  @Override
  public SystemUser findSystemUser(String uname, String upass) {
    SystemUserExample example = new SystemUserExample();
    example.createCriteria().andUserNameEqualTo(uname).andUserPassEqualTo(upass);
    return userMapper.selectOneByExample(example);
  }

  @Override
  public List<SystemUser> listSystemUser(SystemUserExample example, Page<SystemUser> page) {
    return userMapper.selectByExampleWithRowbounds(example, page);
  }

  @Override
  public long countSystemUser(SystemUserExample example) {
    return userMapper.countByExample(example);
  }

  @Transactional
  @Override
  public SystemUser saveSystemUser(SystemUser record) {
    try {
      if (record.getId() == null) {// insert
        if (record.getCreateTime() == null) {
          record.setCreateTime(new Date());
        }
        userMapper.insertSelective(record);
      } else {
        userMapper.updateByPrimaryKeySelective(record);
      }
    } catch (Exception e) {
      LOGGER.error("Failed to save {} by reason: {}", record, e);
      return null;
    }
    return record;
  }

  @Transactional
  @Override
  public boolean removeSystemUser(Long uid) {
    try {
      removeSystemUserRoleByUser(uid);
      userMapper.deleteByPrimaryKey(uid);
      return true;
    } catch (Exception e) {
      LOGGER.error("Failed to remove {} by reason: {}", uid, e);
      return false;
    }
  }

  @Override
  public SystemRole getSystemRole(Long rid) {
    return roleMapper.selectByPrimaryKey(rid);
  }

  @Transactional
  @Override
  public SystemRole saveSystemRole(SystemRole record) {
    try {
      if (record.getId() == null) {// insert
        if (record.getCreateTime() == null) {
          record.setCreateTime(new Date());
        }
        roleMapper.insertSelective(record);
      } else {
        roleMapper.updateByPrimaryKeySelective(record);
      }
    } catch (Exception e) {
      LOGGER.error("Failed to save {} by reason: {}", record, e);
      return null;
    }
    return record;
  }

  @Transactional
  @Override
  public boolean removeSystemRole(Long rid) {
    try {
      removeSystemUserRoleByRole(rid);
      roleMapper.deleteByPrimaryKey(rid);
      return true;
    } catch (Exception e) {
      LOGGER.error("Failed to remove {} by reason: {}", rid, e);
      return false;
    }
  }

  @Override
  public List<SystemRole> listAllSystemRoles() {
    return roleMapper.selectByExample(new SystemRoleExample());
  }

  @Override
  public List<SystemRole> listSystemRolesByUser(Long uid) {
    SystemRoleExample example = new SystemRoleExample();
    SystemUserRoleExample example2 = new SystemUserRoleExample();
    example2.createCriteria().andUserIdEqualTo(uid);
    List<Long> roleIds = userRoleMapper.selectByExample(example2).stream()
        .mapToLong(ur -> ur.getRoleId()).boxed().collect(Collectors.toList());
    if (roleIds.isEmpty()) {
      return Collections.emptyList();
    }
    example.createCriteria().andIdIn(roleIds);
    return roleMapper.selectByExample(example);
  }

  @Override
  public boolean hasRole(Long uid, Long rid) {
    SystemUserRoleExample example = new SystemUserRoleExample();
    example.createCriteria().andUserIdEqualTo(uid).andRoleIdEqualTo(rid);
    return userRoleMapper.countByExample(example) > 0;
  }

  @Transactional
  @Override
  public SystemUserRole saveSystemUserRole(SystemUserRole record) {
    try {
      if (record.getId() == null) {// insert
        userRoleMapper.insertSelective(record);
      } else {
        userRoleMapper.updateByPrimaryKeySelective(record);
      }
    } catch (Exception e) {
      LOGGER.error("Failed to save {} by reason: {}", record, e);
      return null;
    }
    return record;
  }

  @Transactional
  @Override
  public boolean removeSystemUserRole(Long urid) {
    return userRoleMapper.deleteByPrimaryKey(urid) > 0;
  }

  @Transactional
  @Override
  public boolean removeSystemUserRoleByRole(Long rid) {
    SystemUserRoleExample example = new SystemUserRoleExample();
    example.createCriteria().andRoleIdEqualTo(rid);
    return userRoleMapper.deleteByExample(example) > 0;
  }

  @Transactional
  @Override
  public boolean removeSystemUserRoleByUser(Long uid) {
    SystemUserRoleExample example = new SystemUserRoleExample();
    example.createCriteria().andUserIdEqualTo(uid);
    return userRoleMapper.deleteByExample(example) > 0;
  }

  @Transactional
  @Override
  public boolean saveSystemUser(SystemUser user, List<AuthRole> authRoles) {
    SystemUser savedUser = saveSystemUser(user);
    if (savedUser == null)
      return false;
    removeSystemUserRoleByUser(savedUser.getId());
    List<SystemRole> allSysRoles = listAllSystemRoles();
    List<SystemRole> newSysRoles = allSysRoles.stream().filter((role) -> {
      return authRoles.stream()
          .anyMatch(authRole -> authRole == AuthRole.toAuthRole(role.getRoleName()));
    }).collect(Collectors.toList());

    for (SystemRole role : newSysRoles) {
      SystemUserRole record =
          new SystemUserRole.Builder().roleId(role.getId()).userId(savedUser.getId()).build();
      boolean isSuccess = saveSystemUserRole(record) != null;
      if (isSuccess == false) {
        return false;
      }
    }
    return true;
  }
}
