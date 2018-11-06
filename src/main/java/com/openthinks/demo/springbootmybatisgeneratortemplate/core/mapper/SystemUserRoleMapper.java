package com.openthinks.demo.springbootmybatisgeneratortemplate.core.mapper;

import com.openthinks.demo.springbootmybatisgeneratortemplate.core.model.SystemUserRole;
import com.openthinks.demo.springbootmybatisgeneratortemplate.core.model.SystemUserRoleExample;
import java.util.List;
import net.sourceforge.orm.mybatis.Page;
import org.apache.ibatis.annotations.Param;

public interface SystemUserRoleMapper {
    long countByExample(SystemUserRoleExample example);

    int deleteByExample(SystemUserRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SystemUserRole record);

    int insertSelective(SystemUserRole record);

    List<SystemUserRole> selectByExampleWithRowbounds(SystemUserRoleExample example, Page<SystemUserRole> rowBounds);

    SystemUserRole selectOneByExample(SystemUserRoleExample example);

    List<SystemUserRole> selectByExample(SystemUserRoleExample example);

    SystemUserRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SystemUserRole record, @Param("example") SystemUserRoleExample example);

    int updateByExample(@Param("record") SystemUserRole record, @Param("example") SystemUserRoleExample example);

    int updateByPrimaryKeySelective(SystemUserRole record);

    int updateByPrimaryKey(SystemUserRole record);
}