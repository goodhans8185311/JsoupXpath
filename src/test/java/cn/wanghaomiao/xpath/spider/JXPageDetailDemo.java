package cn.wanghaomiao.xpath.spider;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
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
 * 汽车之家详情页
 * 颜值控的选择 长安马自达CX-5长测（1）
 * http://www.autohome.com.cn/drive/201702/890776.html
 * http://www.autohome.com.cn/drive/201702/890776-all.html?pvareaid=101380
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(DataProviderRunner.class)
public class JXPageDetailDemo {

    private JXDocument autoHomeTest;
    
    @Before
    public void before() throws Exception {
        if (autoHomeTest == null) {
            Document doc = Jsoup
            		.connect("http://www.autohome.com.cn/drive/201702/890776-all.html?pvareaid=101380").userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0")
            		.get();
            autoHomeTest = new JXDocument(doc);
        }
    }
    
    /**
     * 详情页爬虫
     * http://www.autohome.com.cn/drive/201702/890776.html
     */
 
    @Test
    @DataProvider(value = {
    		"//div[@class='area article']"
    })
    public void mutilPages(String xpath) throws XpathSyntaxErrorException {
      List<JXNode> jxNodeList = autoHomeTest.selN(xpath);
      for (JXNode node : jxNodeList) {
          if (!node.isText()) {
//        	 System.out.println("文章ID = "  + StringUtils.join(node.sel("/a/div[2]/span[2]/em[2]/@data-articleid"), ""));
        	 System.out.println("文章标题 = "  + StringUtils.join(node.sel( "/h1/allText()"), ""));
        	 List<JXNode> pNodeList = node.sel("/div[@class='article-content']/p");
        	 for(int i=0;i<pNodeList.size();i++){//从零开始遍历
        		 JXNode pnode = pNodeList.get(i);
        		 String p_align = StringUtils.join(pnode.sel( "/@align"), "");  //属性
//        		 System.out.println("align = " + p_align);
        		 String p_text = StringUtils.join(pnode.sel( "/allText()"), "").trim();
        	
        		 if("center".equals(p_align)){
        			 List<JXNode> aNodeList =  pnode.sel("/a");
        			
        			 for(JXNode anode:aNodeList){
        			    String imgSrc = StringUtils.join(anode.sel("/img/@src"),"");
        			    if(!"".equals(imgSrc))
        				System.out.println("文章内部图片   = " +i+ " " + imgSrc);
        			 }
        			// System.out.println("a size = " + aNodeList.size());
        			// System.out.println("    文章中的图片  = " +i + pnode.sel("/a[2]/img/@src").toString());
        		 }else if("left".equals(p_align)){
        			 if(!StringUtil.isBlank(p_text)){
        				 System.out.println("    文章段落  " +i+ " = "  + p_text);
            		 }
        		 }else{
        			 if(!StringUtil.isBlank(p_text)){
        				 System.out.println("    文章段落  " +i+ " = "  + p_text);
            		 }
        			 
        		 }
        	 }
             //System.out.println("#######################################################################################################");
          }
      }
    }
}
