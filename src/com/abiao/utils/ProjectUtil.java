package com.abiao.utils;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.Enumeration;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import redis.clients.jedis.Jedis;

import com.abiao.service.ISystemInfoService;

/**
 * user-manage系统辅助类
 * @author wanbiao
 *
 */
public class ProjectUtil {
	//暂时先固定ip
	public static String ip = "10.11.1.146";
	public static Jedis edis = new Jedis(ip);
	public static ISystemInfoService infoService;
	/**
	 * wsdl获取service服务
	 */
	static{  
        try {
			//创建访问wsdl服务地址的url 
			String serverIp = edis.get("system-manage-ip");
			URL url = new URL("http://"+serverIp+":456/systemInfo?wsdl");  
			//通过Qname指明服务的具体信息  
			QName qname = new QName("http://impl.service.gewanbiao.com",
					"SystemInfoServiceImplService");  
			//创建服务  
			Service service =  Service.create(url, qname);  
			//实现接口  
			infoService = service.getPort(ISystemInfoService.class);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}  
          
    }  
	
	/**
	 * 获取本机的ip
	 * @return
	 */
	public static String getRealIp() {
		String localip = null;// 本地IP，如果没有配置外网IP的时候
		String netip = null;// 外网IP
		try {
			Enumeration<NetworkInterface> netInterfaces =
			NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
			boolean finded = false;// 是否找到外网IP
			while (netInterfaces.hasMoreElements() && !finded) {
				NetworkInterface ni = netInterfaces.nextElement();
				Enumeration<InetAddress> address = ni.getInetAddresses();
				while (address.hasMoreElements()) {
					ip = address.nextElement();
					if (!ip.isSiteLocalAddress()
					&& !ip.isLoopbackAddress()
					&& ip.getHostAddress().indexOf(":") == -1) {// 外网IP
						netip = ip.getHostAddress();
						finded = true;
						break;
					} else if (ip.isSiteLocalAddress()
						&& !ip.isLoopbackAddress()
						&& ip.getHostAddress().indexOf(":") == -1) {// 内网IP
						localip = ip.getHostAddress();
					}
				}
			}
	
		} catch (SocketException e) {
			e.printStackTrace();
		}
		if (netip != null && !"".equals(netip)) {
			return netip;
		} else {
			return localip;
		}
	}
}
