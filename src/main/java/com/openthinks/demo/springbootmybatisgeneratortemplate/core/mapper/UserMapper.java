package com.openthinks.demo.springbootmybatisgeneratortemplate.core.mapper;

import com.openthinks.demo.springbootmybatisgeneratortemplate.core.model.User;
import com.openthinks.demo.springbootmybatisgeneratortemplate.core.model.UserExample;
import java.util.List;
import net.sourceforge.orm.mybatis.Page;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectOneByExample(UserExample example);

    List<User> selectByExampleWithRowbounds(UserExample example, Page rowBounds);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}