package com.xxs.plat.system.menu.service.impl;

import com.xxs.plat.system.menu.dao.MenuMapper;
import com.xxs.plat.system.menu.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@Slf4j
public class MenuServiceImpl implements MenuService {
    
    @Resource
    MenuMapper menuMapper;
    @Override
    public Map<String, Object> menuTree(Map user) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            List<Map<String, Object>> menus = menuMapper.getMenuByIds("");
            Map node = new HashMap();
            node.put("id", 0);
            List<Map<String, Object>> menuList = buildMenuTree(node, menus);
            resultMap.put("data", menuList);
            resultMap.put("status", 200);
        } catch (Exception e) {
            log.error("获取用户菜单异常,用户：{}", user.toString(), e);
            throw new RuntimeException(e);
        }
        return resultMap;
    }
    
    private List<Map<String, Object>> buildMenuTree(Map<String, Object> node, List<Map<String, Object>> menus) {
        List<Map<String, Object>> childList = new ArrayList<>();
        for (Map<String, Object> menu : menus) {
            if (String.valueOf(node.get("id")).equals(String.valueOf(menu.get("parentId")))) {
                List<Map<String, Object>> childs = buildMenuTree(menu, menus);
                menu.put("childList", childs);
                childList.add(menu);
            }
        }
        return childList;
    }
}
