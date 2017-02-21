package cn.wanghaomiao.xpath.spider;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import cn.wanghaomiao.xpath.model.JXNode;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;

/**
 * 汽车之家栏目分页
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(DataProviderRunner.class)
public class JXPageDetailDemo {

    private JXDocument autoHomeTest;
    
    @Before
    public void before() throws Exception {
        //http://www.autohome.com.cn/all/600/#liststart
        if (autoHomeTest == null) {
            Document doc = Jsoup
            		.connect("http://www.autohome.com.cn/all/1/#liststart").userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0")
            		
            		.get();
            autoHomeTest = new JXDocument(doc);
        }
    }
    
    
    /**
     * 焦点图爬虫
     * @param xpath
     * @throws XpathSyntaxErrorException
     */
//    @Test
//    @DataProvider(value = {
//    		"//div[@class='focusimg-pic']/ul/li"
//    })
//    public void foucs(String xpath) throws XpathSyntaxErrorException {
//      System.out.println("current xpath:" + xpath);
//      List<JXNode> jxNodeList = doubanTest.selN(xpath);
//      System.out.println(jxNodeList.size());
//      for (JXNode node : jxNodeList) {
//          if (!node.isText()) {
//        	 System.out.println("文章标题 = "  + StringUtils.join(node.sel( "/h2/allText()"), ""));
//        	 System.out.println("文章主题 = "  + StringUtils.join(node.sel( "/p/allText()"), ""));
//        	 String articleUrl = StringUtils.join(node.sel("/a/@href"), "");
//        	 System.out.println("文章超链接  = " + articleUrl);
//        	 String []arr = articleUrl.split("\\.");
//        	 arr = arr[3].split("/");
//        	 System.out.println("文章ID = "  + arr[arr.length-1]);
//             System.out.println("图片链接 = " +  StringUtils.join(node.sel("/a/img/@src"), ""));
//             System.out.println("#######################################################################################################");
//          }
//      }
//    }
    
    /**
     * 分页爬虫
     * http://www.autohome.com.cn/all/2/#liststart
     * @param xpath
     * @throws XpathSyntaxErrorException
     */
 
    @Test
    @DataProvider(value = {
    		"//ul[@class='article']/li"
    })
    public void mutilPages(String xpath) throws XpathSyntaxErrorException {
      System.out.println("current xpath:" + xpath);
      List<JXNode> jxNodeList = autoHomeTest.selN(xpath);
      System.out.println(jxNodeList.size());
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
    }

}
