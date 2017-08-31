package com.hy.trigger.activepush;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by haoy on 2017/7/12.
 */
public class TriggerActivePush {
    public static void main(String[] args) throws IOException {
        HashMap map = new HashMap();
        map.put("octopusReqId","fsdfsfsdfsdfsdfdsgfdsfds");
        map.put("octopusTimestamp",System.currentTimeMillis());
        List list = new ArrayList();
        for (int i = 0; i < 5; i++) {
            HashMap data = new HashMap();
            data.put("id",i);
            data.put("name","张三");
            data.put("age",28);
            data.put("salary",10000);
            list.add(data);
        }
        map.put("octopusData",list);
        String url = "http://127.0.0.1:9031/triggerReceive/hytest/hy_activepush_id";
        TriggerActivePush tap = new TriggerActivePush();
        String post = tap.post(url, map);
        System.out.println(post.toString());
    }


    private String post(String url, Map data) throws IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Content-type", "application/json");
		httpPost.setEntity(new StringEntity(JSONObject.toJSONString(data),"UTF-8"));
		CloseableHttpResponse response2 = httpclient.execute(httpPost);
        String responseContent;
		try {
		    System.out.println(response2.getStatusLine());
		    HttpEntity entity2 = response2.getEntity();
            responseContent = EntityUtils.toString(entity2, "UTF-8");
            // do something useful with the response body
		    // and ensure it is fully consumed
		    EntityUtils.consume(entity2);
		} finally {
		    response2.close();
		}
		return responseContent;
    }
}
