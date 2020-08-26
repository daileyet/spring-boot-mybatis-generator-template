package com.openthinks.demo.sprj.core.mapper;

import com.openthinks.demo.sprj.core.model.SystemRole;
import com.openthinks.demo.sprj.core.model.SystemRoleExample;
import java.util.List;
import net.sourceforge.orm.mybatis.Page;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;

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

    default SystemRole selectOneByExampleSafely(SystemRoleExample example) {
        List<SystemRole> list=this.selectByExample(example);
        if(list.isEmpty()) return null;
        if(list.size()>1) {LoggerFactory.getLogger(SystemRoleMapper.class).info("select one retured {} rows",list.size());}
        return list.get(0);
    }
}