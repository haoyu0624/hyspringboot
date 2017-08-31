package com.hy.springdata.elasticsearch;

import org.apache.kafka.common.metrics.stats.Count;
import org.elasticsearch.action.ActionResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHitField;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.*;
import org.elasticsearch.search.aggregations.bucket.filter.FilterAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.filter.InternalFilter;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.Sum;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by haoy on 2017/8/22.
 */
//@SpringBootApplication
//@RunWith(SpringJUnit4ClassRunner.class)
public class EslService {

    private static Logger logger = LoggerFactory.getLogger(EslService.class);
//

    //@Test
//    public void findCliente()
//    {
////        String id = "11";
////        Cliente cliente = clienteDao.findOne(id);
////        logger.info(" get cliente by id {} is {}", id, cliente);
////        //curl -XGET 'localhost:9200/logstash-octo-task-2017.08.24/octo-task/AV4TfpRNpa09AHZ_3AI4?pretty'
////        String triggerId = "AV4TfpRNpa09AHZ_3AI4";
////        TriggerLogDto TriggerLog = TriggerLogDao.findOne(triggerId);
////        logger.info(" get TriggerLog by triggerId {} is {}", triggerId, TriggerLog);
//        //this.repository.deleteAll();
//        //saveCustomers();
//        fetchAllCustomers();
////        fetchIndividualCustomers();
//    }

    public static void main(String aa[])
    {
        findCount();
    }

//    @Test
    public static void findCount()
    {
        TransportClient client = null;
        try {
            client = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("10.129.57.79"), 9300));
            customCount(false,"*:*",client);
        } catch (Exception e) {
            logger.info("========catch========>>"+e.getMessage());
            e.printStackTrace();
        }finally {
            logger.info("========final========>>");
            client.close();
        }
    }

    private static long customCount(boolean isQueryAll, String queryString,TransportClient client) throws Exception {
        try {
            //今天的开始时间 比如2016-04-01 00:00:00
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long today_start = sf.parse("2017-08-31 00:00:00").getTime();
//            String today_start = "2017-08-30 00:00:00";
//            long today_start = TimeTools.getDayTimeStamp(0);
            //今天的结束时间 也就是明天的开始时间 比如2016-04-02 00:00:00
            //一闭区间一开区间即得到一天的统计量
            long today_end = sf.parse("2017-08-31 23:59:59").getTime();
//            String today_end = "2017-08-30 23:59:59";

//            long today_end=TimeTools.getDayTimeStamp(1);
//            StringBuffer fq = new StringBuffer();
//            fq.append("@timestamp:")
//                    .append(" [ ")
//                    .append(today_start)
//                    .append(" TO  ")
//                    .append(today_end)
//                    .append(" } ");
            //构建查询请求，使用Lucene高级查询语法
            QueryBuilder query= QueryBuilders.queryStringQuery(queryString);
            //构建查询请求
            SearchRequestBuilder search = client.prepareSearch("logstash-octo-proxy*").setTypes("octo-proxy");
            //非所有的情况下，设置日期过滤
            if(isQueryAll){
                search.setQuery(query);//查询所有
            }else {//加上日期过滤
//                search.setQuery(QueryBuilders.filteredQuery(query, FilterBuilders.queryFilter(QueryBuilders.queryStringQuery(fq.toString()))));
//                search.setPostFilter(QueryBuilders.rangeQuery("msg_key").from(1).to(2));
//                ActionResponse actionResponse = search.setPostFilter(QueryBuilders.rangeQuery("@timestamp").gte(today_start).lt(today_end).format("epoch_millis")).addAggregation(AggregationBuilders.terms("count").field("msg_key")).execute().actionGet();
//                System.out.println(actionResponse);
                FilterAggregationBuilder aaa = AggregationBuilders.filter("action_count", QueryBuilders.rangeQuery("@timestamp").gte(today_start).lt(today_end).format("epoch_millis"));
                aaa.subAggregation(AggregationBuilders.terms("count").field("msg_key"));
                search.addAggregation(aaa);
                //                search.setQuery(QueryBuilders.termQuery("msg_key","3"));
            }
            SearchResponse r = search.get();//得到查询结果
            System.out.println("=======rrr=======>>"+r);
            Aggregation aggregation1 = r.getAggregations().get("action_count");
            InternalFilter filter = r.getAggregations().get("action_count");
            InternalAggregations internalAggregations = filter.getAggregations();

            for (Aggregation aggregation : internalAggregations)
            {
                System.out.println(((LongTerms)aggregation).getBuckets().get(0).getDocCount());

            }
//            if (aggregation!=null)
//            {
//                for (Terms.Bucket entry : genders.getBuckets()) {
//                    entry.getKey();      // Term
//                    entry.getDocCount(); // Doc count
//                }
//            }

//            Terms aggregations1 = r.getAggregations().get("action_count");
//            Terms agg = r.getAggregations().get("agg");
//            Aggregations aggregation2 = aggregation1.get("count");
//            asMap.get()
//            System.out.println("====name=========>>"+metaData);

//            SearchHits searchHits = r.getHits();
//            for (SearchHit searchHit : searchHits.getHits())
//            {
//                System.out.println(searchHit.getFields());
//                //System.out.println(searchHit.getSource());
////                searchHit.highlightFields();
////                searchHit.fields(new HashMap<String, SearchHitField>());
//            }

//            Aggregations aggregations = r.getAggregations();
//            if (aggregations!=null)
//            {
//                for (Aggregation aggregation : aggregations)
//                {
//                    System.out.println("=====================>"+aggregation.getMetaData());
//                }
//            }
//            long hits = r.getHits().getTotalHits();//读取命中数量
//            logger.info("========hits========>>"+hits);
            return 0;
        }catch (Exception e){
            logger.error("统计日期数量出错！",e);
        }
        return 0;
    }

//    private void saveCustomers() {
//        this.triggerLogRepository.save(new TriggerLogDto("Alice", "Smith"));
//        this.triggerLogRepository.save(new TriggerLogDto("Bob", "Smith"));
//        this.triggerLogRepository.save(new TriggerLogDto("aaaaaa", "haoyu"));
//        this.triggerLogRepository.save(new TriggerLogDto("bbbbbbbb", "jiangty"));
//    }
//
//
//    private void fetchAllCustomers() {
//        System.out.println("Customers found with findAll():");
//        System.out.println("-------------------------------");
//        for (TriggerLogDto customer : this.triggerLogRepository.findAll()) {
//            System.out.println(customer);
//        }
//        System.out.println();
//    }
//
//    private void fetchIndividualCustomers() {
//        System.out.println("Customer found with findByFirstName('Alice'):");
//        System.out.println("--------------------------------");
//        System.out.println(this.triggerLogRepository.findByFirstName("Alice"));
//
//        System.out.println("Customers found with findByLastName('Smith'):");
//        System.out.println("--------------------------------");
////        for (TriggerLogDto customer : this.repository.findByLastName("Smith")) {
////            System.out.println(customer);
////        }
//    }
}
