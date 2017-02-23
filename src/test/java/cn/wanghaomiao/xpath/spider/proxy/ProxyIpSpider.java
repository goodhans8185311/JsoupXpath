package cn.wanghaomiao.xpath.spider.proxy;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import cn.wanghaomiao.xpath.model.JXNode;

import com.tngtech.java.junit.dataprovider.DataProviderRunner;

/**
 * 代理服务器ip爬虫
 * http://www.cybersyndrome.net  代理服务器ip 网站
 * http://www.cybersyndrome.net/pla.html
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(DataProviderRunner.class)
public class ProxyIpSpider {
    
	public static  Map<String, Integer> proxyIpMap = new HashMap<String, Integer>();
	
    public static void main(String []args){
    	
        
        try {
        	getList();
		} catch (XpathSyntaxErrorException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 循环抓取分页列表数据
     * @param suffix
     */
    public static Map<String, Integer> getList() throws XpathSyntaxErrorException, IOException {
    	
    	Document doc = Jsoup
         		.connect("http://www.cybersyndrome.net/pla.html").userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0")
         		.get();
    	String rootXpath = "//ol/li";
    	JXDocument  autoHomeTest = new JXDocument(doc);
        List<JXNode> jxNodeList = autoHomeTest.selN(rootXpath);
        System.out.println("jxNodeList = " + jxNodeList.size());
        for (JXNode node : jxNodeList) {
            if (!node.isText()) {
          	   String ip = StringUtils.join(node.sel("/a/text()"), "");
          	   System.out.println("代理ip = "  + ip);
          	   String []arr = ip.split(":");
          	   proxyIpMap.put(arr[0], Integer.parseInt(arr[1]));
            }
        }
        return proxyIpMap;
    }

    /**
     * 循环抓取分页列表数据
     * @param suffix
     */
    public static Map<String, Integer> getTable() throws XpathSyntaxErrorException, IOException {
    	
    	Document doc = Jsoup
         		.connect("http://www.cybersyndrome.net/pla.html").userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0")
         		.get();
    	String rootXpath = "//ol/li";
    	JXDocument  autoHomeTest = new JXDocument(doc);
        List<JXNode> jxNodeList = autoHomeTest.selN(rootXpath);
        System.out.println("jxNodeList = " + jxNodeList.size());
        for (JXNode node : jxNodeList) {
            if (!node.isText()) {
          	   String ip = StringUtils.join(node.sel("/a/text()"), "");
          	   System.out.println("代理ip = "  + ip);
          	   String []arr = ip.split(":");
          	   proxyIpMap.put(arr[0], Integer.parseInt(arr[1]));
            }
        }
        return proxyIpMap;
    }

}
