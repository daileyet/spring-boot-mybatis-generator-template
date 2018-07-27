package com.openthinks.demo.springbootmybatisgeneratortemplate.core.mapper;

import com.openthinks.demo.springbootmybatisgeneratortemplate.core.model.SystemRole;
import com.openthinks.demo.springbootmybatisgeneratortemplate.core.model.SystemRoleExample;
import java.util.List;
import net.sourceforge.orm.mybatis.Page;
import org.apache.ibatis.annotations.Param;

public interface SystemRoleMapper {
    long countByExample(SystemRoleExample example);

    int deleteByExample(SystemRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SystemRole record);

    int insertSelective(SystemRole record);

    List<SystemRole> selectByExampleWithRowbounds(SystemRoleExample example, Page<SystemRole> rowBounds);

    SystemRole selectOneByExample(SystemRoleExample example);

    List<SystemRole> selectByExample(SystemRoleExample example);

    SystemRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SystemRole record, @Param("example") SystemRoleExample example);

    int updateByExample(@Param("record") SystemRole record, @Param("example") SystemRoleExample example);

    int updateByPrimaryKeySelective(SystemRole record);

    int updateByPrimaryKey(SystemRole record);
}