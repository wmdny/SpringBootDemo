package com.xxs.plat.system.menu.dao;

import com.xxs.plat.system.menu.model.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MenuMapper {
    
    /**
     * 根据菜单Id字符串获取菜单
     * @param menuIds
     * @return
     */
    List<Map<String, Object>> getMenuByIds(String menuIds);
    
    
    /*-------------------------下方是自动生成的方法 -------------------------*/
    
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
    /*-------------------------上方是自动生成的方法 -------------------------*/
}
