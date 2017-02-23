package cn.wanghaomiao.xpath.spider.proxy;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;

public class ProxyUtil {

	/**
     * 批量代理IP有效检测
     *
     * @param proxyIpMap
     * @param reqUrl
     */
    public static void checkProxyIp(Map<String, Integer> proxyIpMap, String reqUrl) {

          for (String proxyHost : proxyIpMap.keySet()) {
                 Integer proxyPort = proxyIpMap.get(proxyHost);
                 try {
					   Jsoup
					  		.connect(reqUrl).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0")
					  		.proxy(proxyHost, proxyPort)
					  		.timeout(3000*2)
					  		.get();
				 } catch (IOException e) {
					// System.out.println("##################### error = " + proxyHost+":"+proxyPort);
					 continue;
				 }
                 System.out.println("!!!!!!!!!!!!!!!!!!!!!! passed = " +  proxyHost+":"+proxyPort);
          }
    }
    
    
    public static void checkProxyIp(String proxyIp, int proxyPort, String reqUrl) {
        Map<String, Integer> proxyIpMap = new HashMap<String, Integer>();
        proxyIpMap.put(proxyIp, proxyPort);
        checkProxyIp(proxyIpMap, reqUrl);
    }
    
    
    
    public static void main(String[] args) {
        
    	Map<String, Integer> proxyIpMap = null;
		try {
			proxyIpMap = ProxyIpSpider.getList();
		} catch (XpathSyntaxErrorException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        checkProxyIp(proxyIpMap, "http://t.sohu.com/new_index");

  }

}
