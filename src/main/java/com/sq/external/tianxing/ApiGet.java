package com.sq.external.tianxing;


import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @program: sayhi
 * @description: 网易云api
 * @author: zxw_
 * @create: 2020-11-12 17:07
 */
public class ApiGet {

    public static String request(String httpUrl) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        String result = null;
        CloseableHttpResponse response = null;
        try {
            HttpGet client = new HttpGet(httpUrl);
            response = httpClient.execute(client);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity);
        } catch (IOException | ParseException e) {
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
