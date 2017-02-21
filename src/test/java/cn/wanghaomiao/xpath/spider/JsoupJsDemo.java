package cn.wanghaomiao.xpath.spider;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

import com.google.gson.Gson;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;

/**
 * jsoup 调用js返回json 解析
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(DataProviderRunner.class)
public class JsoupJsDemo {

    private String json;
    //http://reply.autohome.com.cn/api/getData_ReplyCounts.ashx?appid=1&dateType=jsonp&objids=898917.898916.898913
    @Before
    public void before() throws Exception {
    
        Response res = Jsoup.connect("http://reply.autohome.com.cn/api/getData_ReplyCounts.ashx?appid=1&dateType=jsonp&objids=898917.898916.898913")
        		.header("Accept", "*/*")
        		.header("Accept-Encoding", "gzip, deflate")
        		.header("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
        		.header("Content-Type", "application/json;charset=UTF-8")
        		.header("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
        		.timeout(10000).ignoreContentType(true).execute();//.get();
        json = res.body();
//        System.out.println(body);
//        json = JSONObject.fromObject(body);
    }
   
    
    @Test
    public void praseJson(){
       Gson gson = new Gson();
       json = json.substring(1, json.length());
       json = json.substring(0,json.length()-1);
       CommentListBean bean =  gson.fromJson(json, CommentListBean.class);
       System.out.println(bean.getCommentlist().size());
    }

}
