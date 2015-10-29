package com.abiao.listener;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import redis.clients.jedis.Jedis;

import com.abiao.utils.ProjectUtil;


/**
 * 向redis注册user-manage服务器的ip
 * @author wanbiao
 *
 */
public class StartUpListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent servletContext) {
		
	}
    /**
     * 项目启动初始化
     */
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		//连接redis服务
		Jedis edis = ProjectUtil.edis;
		String localIp = ProjectUtil.getRealIp();
		edis.set("user-manage-ip", localIp);
	}
	
}
