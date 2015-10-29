package com.abiao.action;

import com.abiao.module.User;
import com.abiao.utils.ProjectUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 登录action
 * @author wanbiao
 * 
 */
public class LoginAction extends ActionSupport{
	//登录用户信息
	private User user;
	//系统cpu使用情况
	private String cpuInfoStr;
	//系统内存使用情况
	private String memoryInfoStr;

	/**
	 * 登录方法
	 * @return
	 */
	public String execute(){
		if(!user.getUsername().equals("admin")
				||!user.getPassword().equals("admin"))
			return ERROR;
		return SUCCESS;
	}
	/**
	 * 获取内存信息
	 * @return
	 */
	public String queryMemoryInfo(){
		memoryInfoStr = ProjectUtil.infoService.memory();
		return "MEMORY";
	}
	/**
	 * 获取系统cpu信息
	 * @return
	 */
	public String queryCpuInfo(){
		cpuInfoStr = ProjectUtil.infoService.cpu();
		return "CPU";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public String getCpuInfoStr() {
		return cpuInfoStr;
	}
	public void setCpuInfoStr(String cpuInfoStr) {
		this.cpuInfoStr = cpuInfoStr;
	}
	public String getMemoryInfoStr() {
		return memoryInfoStr;
	}
	public void setMemoryInfoStr(String memoryInfoStr) {
		this.memoryInfoStr = memoryInfoStr;
	}
	
	
	
}
