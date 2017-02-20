package cn.wanghaomiao.xpath.model;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

import java.util.List;

/**
 * JXDocument Tester.
 *
 * @author <et.tw@163.com>
 * @version 1.0
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(DataProviderRunner.class)
public class JXDocumentTest {

    private JXDocument underTest;

    private JXDocument doubanTest;

    private JXDocument custom;

//    @Before
//    public void before() throws Exception {
//        String html = "<html><body><script>console.log('aaaaa')</script><div class='test'>搜易贷致力于普惠金融，专注于互联网投资理财与小额贷款，搭建中国最大、用户体验最好的个人及中小企业的互联网信贷平台</div><div class='xiao'>Two</div></body></html>";
//        underTest = new JXDocument(html);
//        if (doubanTest == null) {
//            Document doc = Jsoup.connect("https://book.douban.com/subject_search?start=15&search_text=java&cat=1001").userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0").get();
//            doubanTest = new JXDocument(doc);
//        }
//        custom = new JXDocument("<li><b>性别：</b>男</li>");
//    }
    
    
    @Before
    public void before() throws Exception {
       
        if (doubanTest == null) {
            Document doc = Jsoup.connect("http://www.autohome.com.cn/all/").userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0").get();
            doubanTest = new JXDocument(doc);
        }
    }
  //*[@id="auto-channel-lazyload-article"]/ul[1]/li[2]
   /** <li data-artidanchor="898900">
    <a href="http://www.autohome.com.cn/news/201702/898900.html#pvareaid=102624">
        <div class="article-pic"><img src="http://www2.autoimg.cn/newsdfs/g10/M0C/0F/6A/120x90_0_autohomecar__wKgH0ViqwciAEnQqAAFdu48ykUE175.jpg"></div>
        <h3>涉及59辆 奔驰召回部分E级/迈巴赫S级等</h3>
        <div class="article-bar">
            <span class="fn-left">1小时前</span>
            <span class="fn-right">
                <em><i class="icon12 icon12-eye"></i>1.5万</em>
                <em data-class="icon12 icon12-infor" data-articleid="898900"><i class="icon12 icon12-infor"></i>29</em>
            </span>
        </div>
        <p>[汽车之家 新闻]  日前，梅赛德斯-奔驰（中国）汽车销售有限公司及北京奔驰汽车有限公司根据《缺陷汽车产品召回管理条例》的要求，向国家质检总局备案了召...</p>
    </a>
</li>*/
    @Test
    @DataProvider(value = {
    		"//ul[@class='article']/li"
    })
    public void testJXNode(String xpath) throws XpathSyntaxErrorException {
      System.out.println("current xpath:" + xpath);
      List<JXNode> jxNodeList = doubanTest.selN(xpath);
      System.out.println(jxNodeList.size());
      for (JXNode node : jxNodeList) {
          if (!node.isText()) {
        	  //System.out.println("########### " + node.getTextVal());
        	  System.out.println(StringUtils.join(node.sel("/a/@href"), ""));
              System.out.println(StringUtils.join(node.sel("/a/h3/allText()"), ""));
              System.out.println(StringUtils.join(node.sel("/a/p/allText()"), ""));
            //*[@id="auto-channel-lazyload-article"]/ul[1]/li[3]/a/div[2]/span[2]/em[1]/text()
            //*[@id="auto-channel-lazyload-article"]/ul[1]/li[3]/a/div[2]/span[2]/em[2]
             System.out.println("已经阅读 量= " + StringUtils.join(node.sel("/a/div[2]/span[@class='fn-right']/em[1]/text()"), ""));
              System.out.println("已经评论数量 = " + StringUtils.join(node.sel("/a/div[2]/span[@class='fn-right']/em[2]/allText()"), ""));
          }
      }
    }
    
   

    /**
     * Method: sel(String xpath)
     */
//    @Test
//    public void testSel() throws Exception {
//        String xpath = "//script[1]/text()";
//        List<Object> res = underTest.sel(xpath);
//        Assert.assertNotNull(res);
//        Assert.assertTrue(res.size() > 0);
//        
//        System.out.println(StringUtils.join(res, ","));
//    }

//    @Test
//    public void testNotMatchFilter() throws Exception {
//        String xpath = "//div[@class!~'xiao']/text()";
//        List<Object> res = underTest.sel(xpath);
//        System.out.println(StringUtils.join(res, ","));
//    }

//    @Test
//    @DataProvider(value = {
//            "//a/@href",
//            "//div[@class='paginator']/span[@class='next']/a/@href",
//            "//ul[@class='subject-list']/li[position()<3]/div/h2/allText()",
//            "//ul[@class='subject-list']/li[first()]/div/h2/allText()",
//            "//ul[@class='subject-list']/li[./div/div/span[@class='pl']/num()>900]/div/h2/allText()", //查找评论大于1000的条目（当然只是为了演示复杂xpath了，谓语中可以各种嵌套，这样才能测试的更全面）
//            "//ul[@class='subject-list']/li[self::li/div/div/span[@class='pl']/num()>900]/div/h2/allText()",
//            "//*[@id='content']/div/div[1]/ul/li[14]/div[2]/h2/a/text()" //chrome拷贝
//    })
//    public void testXpath(String xpath) throws XpathSyntaxErrorException {
//        System.out.println("current xpath:" + xpath);
//        List<JXNode> rs = doubanTest.selN(xpath);
//        for (JXNode n : rs) {
//            if (!n.isText()) {
//                int index = n.getElement().siblingIndex();
//                System.out.println(index);
//            }
//            System.out.println(n.toString());
//        }
//    }

//    @Test
//    @DataProvider(value = {
//            "//ul[@class='subject-list']/li"
//    })
//    public void testJXNode(String xpath) throws XpathSyntaxErrorException {
//        System.out.println("current xpath:" + xpath);
//        List<JXNode> jxNodeList = doubanTest.selN(xpath);
//        for (JXNode node : jxNodeList) {
//            if (!node.isText()) {
//                System.out.println(StringUtils.join(node.sel("/div/h2/a/text()"), ""));
//            }
//        }
//    }

//    @Test
//    @DataProvider(value = {
//            "//li[@class='subject-item']"
//    })
//    public void testRecursionNode(String xpath) throws XpathSyntaxErrorException {
//        System.out.println("current xpath:" + xpath);
//        List<JXNode> jxNodeList = doubanTest.selN(xpath);
//        System.out.println(jxNodeList.size());
//    }

    //"<li><b>性别：</b>男</li>"
//    @Test
//    public void testAs() throws XpathSyntaxErrorException {
//        List<JXNode> jxNodeList = custom.selN("//b[text()='性别：']/parent::*/text()");
//        for (JXNode jxNode : jxNodeList) {
//            System.out.println(jxNode.getTextVal());
//        }
//    }

}
