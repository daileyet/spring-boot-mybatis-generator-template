package com.openthinks.demo.springbootmybatisgeneratortemplate.core.mapper;

import com.openthinks.demo.springbootmybatisgeneratortemplate.core.model.SystemUser;
import com.openthinks.demo.springbootmybatisgeneratortemplate.core.model.SystemUserExample;
import java.util.List;
import net.sourceforge.orm.mybatis.Page;
import org.apache.ibatis.annotations.Param;

public interface SystemUserMapper {
    long countByExample(SystemUserExample example);

    int deleteByExample(SystemUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SystemUser record);

    int insertSelective(SystemUser record);

    List<SystemUser> selectByExampleWithRowbounds(SystemUserExample example, Page<SystemUser> rowBounds);

    SystemUser selectOneByExample(SystemUserExample example);

    List<SystemUser> selectByExample(SystemUserExample example);

    SystemUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SystemUser record, @Param("example") SystemUserExample example);

    int updateByExample(@Param("record") SystemUser record, @Param("example") SystemUserExample example);

    int updateByPrimaryKeySelective(SystemUser record);

    int updateByPrimaryKey(SystemUser record);
}