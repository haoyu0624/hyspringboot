package com.hy.kafka.basic;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by haoy on 2017/7/20.
 */
public class ConsumerBasic1 {
    public static void main(String[] args) throws InterruptedException {
        ConsumerBasic1 cb = new ConsumerBasic1();
        cb.offsetCommitting();
    }
    /**
     * Automatic Offset Committing
     * @return
     */
    public void offsetCommitting()  throws InterruptedException{
        Properties props = new Properties();
        props.put("bootstrap.servers", "slave2:9097");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("max.poll.records", "1");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("basic-topic"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
                Thread.sleep(500L);
            }
        }
    }

    /**
     * Manual Offset Control
     * @return
     * @throws InterruptedException
     */
    public void offsetControl()  throws InterruptedException{
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "false");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("foo", "bar"));
        final int minBatchSize = 200;
        List<ConsumerRecord<String, String>> buffer = new ArrayList<>();
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                buffer.add(record);
            }
            if (buffer.size() >= minBatchSize) {
                //insertIntoDb(buffer);
                Thread.sleep(500L);
                consumer.commitSync();
                buffer.clear();
            }
        }
    }
}
