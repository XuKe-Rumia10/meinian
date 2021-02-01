package com.atguigu.test;

import com.atguigu.utils.HttpUtils;
import org.apache.http.HttpResponse;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author XuKe   Email:xuke598654158@126.com
 * @Description
 * @date 2021/1/27  11:58
 */
public class TestSMS {
    @Test
    public void testSMS(){
        String host = "http://dingxin.market.alicloudapi.com";//固定地址
        String path = "/dx/sendSms";//固定映射地址
        String method = "POST";//必须post请求
        String appcode = "ffee4bb4488240f089064cfe45f04247";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);

        //三个参数名称是固定的
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("mobile", "17777791657");
        querys.put("param", "code:abcd");//code:开头，只支持数字和字母
        querys.put("tpl_id", "TP1711063");//测试模板ID，固定。如果需要自定义模板，请联系客服
        Map<String, String> bodys = new HashMap<String, String>();


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
