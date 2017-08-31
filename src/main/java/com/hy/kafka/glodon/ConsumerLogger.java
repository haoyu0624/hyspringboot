package com.hy.kafka.glodon;

import com.glodon.kafka.api.internal.consumer.handler.MessageHandingException;
import com.glodon.kafka.api.internal.consumer.handler.async.AsyncMessageHandler;
import com.glodon.kafka.api.internal.consumer.handler.async.AsyncMethodCallback;
import com.glodon.kafka.api.internal.consumer.runner.ConsumerRunner;
import com.glodon.kafka.api.internal.producer.reporter.Order;
import com.hy.kafka.glodon.dto.OctopusLogger;
import com.hy.kafka.glodon.dto.Student;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.concurrent.Executors;

/**
 * Created by haoy on 2017/7/18.
 */
public class ConsumerLogger extends Thread {

    public static void main(String[] args) {
        ConsumerLogger c = new ConsumerLogger();
        c.start();
    }
    @Override
    public void run() {
        try {
            Schema octopusLogger = new OctopusLogger().getOctopusLogger();
            ConsumerRunner triggerRunner = ConsumerRunner.newBuilder().keepInOrder(Order.FALSE).maxPollRecords(1)
                    .bootstrapServers("10.129.56.193:9097").addTopic("octopus-log", octopusLogger).groupId("octopus-hy").messageHandler(new AsyncMessageHandler<Void>(){

                        @Override
                        public Void doSomething(ConsumerRecord<String, GenericRecord> record)
                                throws MessageHandingException {
                            GenericRecord payload = record.value();
                            String msgKey = ((org.apache.avro.util.Utf8)payload.get("msgKey")).toString();
                            System.out.println("msgKey======>>>"+msgKey);
                            return null;
                        }

                        @Override
                        protected AsyncMethodCallback<Void> crateCallback() {
                            return new AsyncMethodCallback<Void>(){

                                @Override
                                public void onError(Throwable arg0) {

                                }

                                @Override
                                public void onSuccess(Void arg0) {

                                }

                            };
                        }

                    }).asyncExecutorService(Executors.newFixedThreadPool(1)).build();


            triggerRunner.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
