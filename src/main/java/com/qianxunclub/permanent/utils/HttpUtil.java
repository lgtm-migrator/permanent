package com.qianxunclub.permanent.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.Map;

@Slf4j
public class HttpUtil {

    public static String httpGet(String requestURL, Map<String, String> params) {
        String response = "";
        try {
            URIBuilder builder = new URIBuilder(requestURL);
            if (params != null) {
                params.keySet().forEach(k -> builder.setParameter(k, params.get(k)));
            }

            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet get = new HttpGet(builder.build());
            CloseableHttpResponse httpResponse = httpClient.execute(get);
            HttpEntity entity = httpResponse.getEntity();
            response = EntityUtils.toString(entity, "UTF-8");

        } catch (Exception e) {
            log.error("Http {} request fail for：{}", requestURL, e.getMessage());
        }

        return response;
    }
}
