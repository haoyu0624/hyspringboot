package com.hy.kafka.glodon;

import com.glodon.kafka.api.internal.consumer.handler.MessageHandingException;
import com.glodon.kafka.api.internal.consumer.handler.async.AsyncMessageHandler;
import com.glodon.kafka.api.internal.consumer.handler.async.AsyncMethodCallback;
import com.glodon.kafka.api.internal.consumer.runner.ConsumerRunner;
import com.glodon.kafka.api.internal.producer.reporter.Order;
import com.hy.kafka.glodon.dto.Student;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.concurrent.Executors;

/**
 * Created by haoy on 2017/7/18.
 */
public class Consumer2 extends Thread {

    public static void main(String[] args) {
        Consumer2 c = new Consumer2();
        c.start();
    }
    @Override
    public void run() {
        try {
            Schema studentSchema = new Student().getStuedent();
            ConsumerRunner triggerRunner = ConsumerRunner.newBuilder().keepInOrder(Order.FALSE).maxPollRecords(1)
                    .bootstrapServers("slave2:9097").addTopic("hytestOne", studentSchema).groupId("octopus-hy").messageHandler(new AsyncMessageHandler<Void>(){

                        @Override
                        public Void doSomething(ConsumerRecord<String, GenericRecord> record)
                                throws MessageHandingException {
                            GenericRecord payload = record.value();
                            String studentName = ((org.apache.avro.util.Utf8)payload.get("studentName")).toString();
                            Integer age = (Integer) payload.get("age");
                            System.out.println("studentName======>>>"+studentName);
                            System.out.println("age======>>>"+age);
                            try {
                                Thread.sleep(50L);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
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
