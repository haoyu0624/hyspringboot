package com.hy.httppostsend;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by haoy on 2017/7/12.
 */
public class PostSend {
    public static void main(String[] args) throws IOException {
        EventRecord er = new EventRecord();
//        er.setEventUrl("http://127.0.0.1:8585/samplecontroller/test1");
//        er.setEventUrl("http://192.168.72.103:9052/proxy/hytest/gzy_hytest");
        er.setEventUrl("http://10.129.56.191:9052/proxy/hytest/gzy_hytest");

        er.setEventUrlParam("?salary=${salary}&name=${name}");
        er.setEventHeaderParam("adminId=${adminId}");
        er.setEventBodyParam("user=${user}&age=${age}");
        TriggerRespDataItemRecord t = new TriggerRespDataItemRecord();
        HashMap map = new HashMap();
        map.put("user","郝宇");
        map.put("age",22);
        map.put("salary","10000");
        map.put("name","123123");
        t.setCustData(map);
        PostSend ps = new PostSend();

        //------------替换数据-------222-------//
        //获得custdata数据
        Map custData = t.getCustData();
//        //替换url参数
        String eventUrl = er.getEventUrl();
        String eventUrlParam = er.getEventUrlParam();
        String eventHeaderParam = er.getEventHeaderParam();
        String eventBodyParam = er.getEventBodyParam();
        String urlFullPath = ps.getUrlData(eventUrl,eventUrlParam,custData);
        System.out.println("======>>"+urlFullPath);
        //ps.post(url,t);
        for (int i = 0; i < 1000; i++) {
            ps.postNew(urlFullPath,eventHeaderParam,eventBodyParam,t);
        }
    }

    /**
     * 根据eventUrl、eventUrlParam、custData替换并拼装返回完整url
     * @param eventUrl
     * @param eventUrlParam
     * @param custData
     * @return
     */
    private String getUrlData(String eventUrl, String eventUrlParam, Map custData) throws UnsupportedEncodingException {
        String eventUrlParamNew = replaceData("1", eventUrlParam,custData);
        return eventUrl + eventUrlParamNew;
    }

    private void postNew(String url, String eventHeaderParam, String eventBodyParam,TriggerRespDataItemRecord data) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        //设置header
        putHeader(httpPost,eventHeaderParam,data.getCustData());
        //设置body
        putBody(httpPost,eventBodyParam,data.getCustData());
        CloseableHttpResponse response2 = httpclient.execute(httpPost);
        try {
            System.out.println(response2.getStatusLine());
            HttpEntity entity2 = response2.getEntity();
            // do something useful with the response body
            // and ensure it is fully consumed
            //logger.debug(EntityUtils.toString(entity2));
            EntityUtils.consume(entity2);
        } finally {
            response2.close();
        }
    }
    /**
     * 根据eventBodyParam、custData 替换并拼装数据
     * @param httpPost
     * @param eventBodyParam
     * @param custData
     */
    private void putBody(HttpPost httpPost, String eventBodyParam, Map<String, ?> custData) throws UnsupportedEncodingException {
        String result = replaceData("",eventBodyParam, custData);
        Map<String, String> paramMap = URLRequest(result);
        StringEntity stringEntity = new StringEntity(JSONObject.toJSONString(paramMap), "UTF-8");
        httpPost.setEntity(stringEntity);
    }

    /**
     * 根据eventHeaderParam、custData 替换并拼装数据
     * @param httpPost
     * @param eventHeaderParam
     * @param custData
     */
    private void putHeader(HttpPost httpPost, String eventHeaderParam, Map<String, ?> custData) throws UnsupportedEncodingException {
        httpPost.setHeader("Content-type", "application/json");
        String result = replaceData("", eventHeaderParam, custData);
        Map<String, String> paramMap = URLRequest(result);
        Set<String> paramKeySet = paramMap.keySet();
        Iterator<String> iterator = paramKeySet.iterator();
        while(iterator.hasNext()){
            String key = iterator.next();
            httpPost.setHeader(key,paramMap.get(key));
        }
    }

    /**
     * 将传入的replaceParam数据的${***} 中的数据替换为custData的值
     *
     * @param transcoding
     * @param replaceParam
     * @param custData
     * @return
     */
    private String replaceData(String transcoding, String replaceParam, Map custData) throws UnsupportedEncodingException {
        Pattern p = Pattern.compile("\\$\\{.*?\\}");
        Matcher m = p.matcher(replaceParam);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String matchParam = m.group();
            String matchValue = matchParam.substring(2, matchParam.length() - 1);
            Object custDataValue = custData.get(matchValue);
            if(null==custDataValue){
                m.appendReplacement(sb, "");
                continue;
            }
            if(null !=transcoding && !"".equals(transcoding) && "1".equals(transcoding)){
                custDataValue = URLEncoder.encode(custDataValue.toString(), "UTF-8");
            }
//            if(custDataValue instanceof String){
//                custDataValue = ("'"+custDataValue+"'");
//            }
//            m.appendReplacement(sb, custDataStr);
            m.appendReplacement(sb, custDataValue.toString());
        }
        return m.appendTail(sb).toString();
    }

    /**
     * 解析出url参数中的键值对
     * 如 "Action=del&id=123"，解析出Action:del,id:123存入map中
     * @param URL  url地址
     * @return  url请求参数部分
     */
    public static Map<String, String> URLRequest(String URL)
    {
        Map<String, String> mapRequest = new HashMap<String, String>();

        String[] arrSplit=null;

        String strUrlParam=URL;
        if(strUrlParam==null)
        {
            return mapRequest;
        }
        //每个键值为一组 www.2cto.com
        arrSplit=strUrlParam.split("[&]");
        for(String strSplit:arrSplit)
        {
            String[] arrSplitEqual=null;
            arrSplitEqual= strSplit.split("[=]");

            //解析出键值
            if(arrSplitEqual.length>1)
            {
                //正确解析
                mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);

            }
            else
            {
                if(arrSplitEqual[0]!="")
                {
                    //只有参数没有值，不加入
                    mapRequest.put(arrSplitEqual[0], "");
                }
            }
        }
        return mapRequest;
    }
}
