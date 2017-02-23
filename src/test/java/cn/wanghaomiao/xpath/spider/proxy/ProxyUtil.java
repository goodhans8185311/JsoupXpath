package cn.wanghaomiao.xpath.spider.proxy;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;

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
					  		.connect("http://www.autohome.com.cn").userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0")
					  		//.proxy("181.215.114.246", 8080)
					  		.proxy(proxyHost, proxyPort)
					  		.timeout(3000)
					  		.get();
				 } catch (IOException e) {
					 System.out.println("##################### error = " + proxyHost+":"+proxyPort);
					 continue;
				 }
                 System.out.println("!!!!!!!!!!!!!!!!!!!!!! passed = " +  proxyHost+":"+proxyPort);
//                int statusCode = 0;
//                try {
//                      HttpClient httpClient = new HttpClient();
//                      httpClient.getHostConfiguration().setProxy(proxyHost, proxyPort);
//
//                      // 连接超时时间（默认10秒 10000ms） 单位毫秒（ms）
//                      int connectionTimeout = 10000;
//                      // 读取数据超时时间（默认30秒 30000ms） 单位毫秒（ms）
//                      int soTimeout = 30000;
//                      httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(connectionTimeout);
//                      httpClient.getHttpConnectionManager().getParams().setSoTimeout(soTimeout);
//                      HttpMethod method = new GetMethod(reqUrl);
//
//                      statusCode = httpClient.executeMethod(method);
//                } catch (Exception e) {
//                      e.printStackTrace();
//                }
//                System.out.format("%s:%s-->%sn", proxyHost, proxyPort, statusCode);
          }
    }
    
    
    public static void checkProxyIp(String proxyIp, int proxyPort, String reqUrl) {
        Map<String, Integer> proxyIpMap = new HashMap<String, Integer>();
        proxyIpMap.put(proxyIp, proxyPort);
        checkProxyIp(proxyIpMap, reqUrl);
   }
    
    public static void main(String[] args) {
        
        Map<String, Integer> proxyIpMap = new HashMap<String, Integer>();
        proxyIpMap.put("59.152.219.54", 8080);
        proxyIpMap.put("218.104.148.157", 8080);
        proxyIpMap.put("201.16.147.193", 80);
        proxyIpMap.put("188.113.185.73", 3128);
        proxyIpMap.put("144.217.12.240", 8080);
        proxyIpMap.put("192.241.186.239", 8080);
        checkProxyIp(proxyIpMap, "http://t.sohu.com/new_index");

  }

}
