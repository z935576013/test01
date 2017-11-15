/*
 * Copyright (C), 2013-2014, 
 * FileName: UserInfo.java
 * Author:   zhuliang
 * Date:     2014年10月16日 下午1:30:32
 */
package com.merak.lzpt.util;

import java.io.Serializable;
import java.util.List;

/**
 * 实体类用户
 * 
 * @author zhuliang
 */
public class AdminInfo implements Serializable {
	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 编号ID 编号ID
	 */
	private Long id;
	/**
	 * 手机号 手机号
	 */
	private String mobile;
	/**
	 * 密码 密码
	 */
	private String password;
	/**
	 * 姓名 姓名
	 */
	private String name;

	private Integer adminType;

	String authoritys = null;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the adminType
	 */
	public Integer getAdminType() {
		return adminType;
	}

	/**
	 * @param adminType
	 *            the adminType to set
	 */
	public void setAdminType(Integer adminType) {
		this.adminType = adminType;
	}

	/**
	 * @return the authoritys
	 */
	public String getAuthoritys() {
		return authoritys;
	}

	/**
	 * @param authoritys
	 *            the authoritys to set
	 */
	public void setAuthoritys(String authoritys) {
		this.authoritys = authoritys;
	}

}
