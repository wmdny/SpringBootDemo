package com.xxs.plat.system.user.dao;

import com.xxs.plat.system.user.model.PlatUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PlatUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PlatUser record);

    int insertSelective(PlatUser record);

    PlatUser selectByPrimaryKey(Integer id);
    
    PlatUser selectByLoginName(String loginName);

    int updateByPrimaryKeySelective(PlatUser record);

    int updateByPrimaryKey(PlatUser record);
    
    PlatUser selectByUser(Map param);
    
    List<Map<String, Object>> list(Map param);
}
