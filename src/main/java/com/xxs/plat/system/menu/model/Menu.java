package com.xxs.plat.system.menu.model;

import lombok.Data;

import java.io.Serializable;

/**
 * plat_menu
 * @author
 */
@Data
public class Menu implements Serializable {
    /**
     * 菜单主键
     */
    private Integer id;

    /**
     * 父级菜单主键
     */
    private Integer parentId;

    /**
     * 组件名称
     */
    private String name;

    /**
     * 项目中组件地址（主菜单默认Layout，子菜单默认前缀@/views，后面自定义
     */
    private String component;

    /**
     * 图标
     */
    private String icon;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 默认重定向
     */
    private String redirect;

    /**
     * 显示标题
     */
    private String title;

    /**
     * 默认隐藏
     */
    private Boolean hidden;

    /**
     * 是否需要缓存
     */
    private Boolean needCache;

    /**
     * 是否传参数
     */
    private Boolean props;

    /**
     * 是否忽略权限
     */
    private Boolean notNeedAuth;

    /**
     * 备注
     */
    private String remark;

    private static final long serialVersionUID = 1L;
}
