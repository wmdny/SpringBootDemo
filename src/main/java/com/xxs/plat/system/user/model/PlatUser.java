package com.xxs.plat.system.user.model;

import lombok.Data;

import java.io.Serializable;

/**
 * plat_user
 * @author
 */
@Data
public class PlatUser implements Serializable {
    /**
     * 用户表ID
     */
    private Integer id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 登录密码（md5）加密
     */
    private String password;

    /**
     * 角色id
     */
    private int role;

    /**
     * 是否启用
     */
    private Boolean isUse;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 地址
     */
    private String address;

    /**
     * 电话
     */
    private String tel;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 备注
     */
    private String remark;

    private static final long serialVersionUID = 1L;


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userName=").append(userName);
        sb.append(", loginName=").append(loginName);
        sb.append(", password=").append(password);
        sb.append(", role=").append(role);
        sb.append(", isUse=").append(isUse);
        sb.append(", avatar=").append(avatar);
        sb.append(", address=").append(address);
        sb.append(", tel=").append(tel);
        sb.append(", email=").append(email);
        sb.append(", createTime=").append(createTime);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
