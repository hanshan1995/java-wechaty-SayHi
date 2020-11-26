package com.sq.external.tianxing;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @program: sayhi
 * @description:
 * @author: zxw_
 * @create: 2020-11-20 17:19
 */
public class ApiPost {

    private static final Log log = LogFactory.getCurrentLogFactory().getLog(ApiPost.class);

    public static String request(String httpUrl,String param) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(httpUrl);
        StringEntity stringEntity = new StringEntity(param,"UTF-8");
        post.setEntity(stringEntity);
        HttpEntity httpEntity = null;
        CloseableHttpResponse response = null;
        String result = "";
        try {
            response = httpClient.execute(post);
            httpEntity= response.getEntity();
            result = EntityUtils.toString(httpEntity);
        }catch (IOException e) {
            log.error("post request error",e);
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
