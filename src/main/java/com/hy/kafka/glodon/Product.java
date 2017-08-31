package com.hy.kafka.glodon;

import com.glodon.kafka.api.internal.producer.reporter.Order;
import com.glodon.kafka.api.internal.producer.reporter.Reporter;
import com.glodon.kafka.api.internal.producer.sender.ProducerProxy;
import com.hy.kafka.glodon.dto.Student;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.Future;

/**
 * Created by haoy on 2017/7/18.
 */
public class Product {
    public static void main(String[] args) throws Exception {
        //,slave2:9098,slave2:9099
        final Reporter reporter = Reporter.newBuilder().keepInOrder(Order.TRUE).bootstrapServers("slave2:9097").build();
        final Schema stuedent = new Student().getStuedent();
        final String topic = "hytestSync";
        final Product p = new Product();
//        new Thread(){
//            @Override
//            public void run() {
                for (int i = 0; i < 10; i++) {
                    GenericRecord studentRecord = new GenericData.Record(stuedent);
                    studentRecord.put("studentName", "zero_haoyu");
                    studentRecord.put("age",i);
                    try {
//                        p.sendMessage(reporter,topic,0,"0",studentRecord);
                        p.sendMessage(reporter,topic,"111",studentRecord);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
//            }
//        }.start();
//        new Thread(){
//            @Override
//            public void run() {
//                for (int i = 0; i < 100; i++) {
//                    GenericRecord studentRecord = new GenericData.Record(stuedent);
//                    studentRecord.put("studentName", "one_haoyu");
//                    studentRecord.put("age",i);
//                    try {
////                        p.sendMessage(reporter,topic,1,"1",studentRecord);
//                        p.sendMessage(reporter,topic,"222",studentRecord);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }.start();
//        new Thread(){
//            @Override
//            public void run() {
//                for (int i = 0; i < 100; i++) {
//                    GenericRecord studentRecord = new GenericData.Record(stuedent);
//                    studentRecord.put("studentName", "two_haoyu");
//                    studentRecord.put("age",i);
//                    try {
////                        p.sendMessage(reporter,topic,2,"2",studentRecord);
//                        p.sendMessage(reporter,topic,"333",studentRecord);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }.start();
//        new Thread(){
//            @Override
//            public void run() {
//                for (int i = 0; i < 100; i++) {
//                    GenericRecord studentRecord = new GenericData.Record(stuedent);
//                    studentRecord.put("studentName", "three_haoyu");
//                    studentRecord.put("age",i);
//                    try {
////                        p.sendMessage(reporter,topic,3,"3",studentRecord);
//                        p.sendMessage(reporter,topic,"444",studentRecord);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }.start();

    }

    public void sendMessage(Reporter reporter,String topic,String key,GenericRecord record) throws Exception{
//    public void sendMessage(Reporter reporter,String topic,Integer par,String key,GenericRecord record) throws Exception{
        ProducerRecord<String, GenericRecord> producerRecord = new ProducerRecord<String, GenericRecord>(topic, key, record);
        ProducerProxy<String, GenericRecord> producer = reporter.getSender();
        Future<RecordMetadata> future = producer.send(producerRecord);
        future.get();
    }

}
