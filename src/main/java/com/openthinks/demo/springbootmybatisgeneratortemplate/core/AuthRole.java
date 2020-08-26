package com.openthinks.demo.springbootmybatisgeneratortemplate.core;

import java.util.HashSet;
import java.util.Set;

import com.openthinks.demo.springbootmybatisgeneratortemplate.core.model.SystemRole;
import com.openthinks.libs.utilities.json.Valueable;

public enum AuthRole implements Valueable<Integer> {
    /**
     * 可以查看，修改系统用户
     */
    ROLE_ADMIN {
      @Override
      public Integer value() {
        return 3;
      }
    },
    /**
     * 可以查看，修改日志配置
     */
    ROLE_CONTRIBUTOR {
      @Override
      public Integer value() {
        return 2;
      }
    },
    /**
     * 可以查看，下载上传的日志
     */
    ROLE_VIEWER {
      @Override
      public Integer value() {
        return 1;
      }
    },
    ROLE_ANONYMOUS {
      @Override
      public Integer value() {
        return 0;
      }
    };

    public String shortName() {
      return name().split("_")[1];
    }
    
    public static AuthRole toAuthRole(Object obj) {
        if (obj == null)
          return null;
        Object target = obj;
        if (obj instanceof SystemRole) {
          target = ((SystemRole) obj).getRoleName();
        }
        for (AuthRole state : AuthRole.values()) {
          try {
            if (state.value().intValue() == Integer.valueOf(target.toString())) {
              return state;
            }
          } catch (NumberFormatException e) {
            if (state.name().equalsIgnoreCase(target.toString())) {
              return state;
            }
          }
        }
        return null;
      }

      public static Set<AuthRole> toAuthRoles(Object... objects) {
        Set<AuthRole> set = new HashSet<>();
        if (objects != null) {
          for (Object obj : objects) {
            AuthRole role = AuthRole.toAuthRole(obj);
            if (role != null) {
              set.add(role);
            }
          }
        }
        return set;
      }
  }
