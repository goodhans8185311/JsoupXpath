package cn.wanghaomiao.xpath.spider.proxy;

import java.io.IOException;
import java.util.List;

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
 * http://www.cybersyndrome.net/pla.html
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(DataProviderRunner.class)
public class ProxyIpSpider {

    private static String nextPage;
    
    public static void main(String []args){
    	
        String beginSuffix = "/all/1/#liststart";
        try {
			loopMutilPages(beginSuffix);
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
    public static void loopMutilPages(String suffix) throws XpathSyntaxErrorException, IOException {
    	
    	Document doc = Jsoup
         		.connect("http://www.autohome.com.cn"+suffix).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0")
         		.proxy("106.14.61.27", 3128)
         		.get();
    	String rootXpath = "//ul[@class='article']/li";
    	JXDocument  autoHomeTest = new JXDocument(doc);
        List<JXNode> jxNodeList = autoHomeTest.selN(rootXpath);
        for (JXNode node : jxNodeList) {
            if (!node.isText()) {
          	   System.out.println("文章ID = "  + StringUtils.join(node.sel("/a/div[2]/span[2]/em[2]/@data-articleid"), ""));
          	   System.out.println("文章标题 = "  + StringUtils.join(node.sel( "/a/h3/allText()"), ""));
          	   System.out.println("文章超链接  = " + StringUtils.join(node.sel("/a/@href"), ""));
               System.out.println("文章主题" +  StringUtils.join(node.sel("/a/p/allText()"), ""));
               System.out.println("图片链接" +  StringUtils.join(node.sel("/a/div[1]/img/@src"), ""));
               System.out.println("已经阅读 量= " + StringUtils.join(node.sel("/a/div[2]/span[2]/em[1]/text()"), ""));
               System.out.println("发布时间 = " + StringUtils.join(node.sel("/a/div[2]/span[1]/allText()"), ""));
               System.out.println("#######################################################################################################");
            }
        }
        //爬下一页
        nextPage = StringUtils.join(autoHomeTest.sel("//div[@class='page']/a[@class='page-item-next']/@href"),"");
        System.out.println( "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@   网络爬虫开始抓取下一页  = " + nextPage + "   @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        try {
			Thread.sleep(1000*10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        loopMutilPages(nextPage);
    }
    
  
   

}
