package com.hy.kafka.glodon;

import com.glodon.kafka.api.internal.consumer.handler.MessageHandingException;
import com.glodon.kafka.api.internal.consumer.handler.sync.SyncMessageHandler;
import com.glodon.kafka.api.internal.consumer.runner.ConsumerRunner;
import com.glodon.kafka.api.internal.producer.reporter.Order;
import com.hy.kafka.glodon.dto.Student;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * Created by haoy on 2017/7/18.
 */
public class ConsumerSync1 extends Thread {

    public static void main(String[] args) {
        ConsumerSync1 c = new ConsumerSync1();
        c.start();
    }
    @Override
    public void run() {
        try {
            Schema studentSchema = new Student().getStuedent();
            ConsumerRunner triggerRunner = ConsumerRunner.newBuilder().keepInOrder(Order.TRUE).maxPollRecords(1)
                    //,slave2:9098,slave2:9099
                    .bootstrapServers("slave2:9097").addTopic("hytestSync", studentSchema).groupId("hytest").messageHandler(new SyncMessageHandler<Void>() {
                       @Override
                       public Void doSomething(ConsumerRecord<String, GenericRecord> record) throws MessageHandingException {
                           GenericRecord payload = record.value();
                           String studentName = ((org.apache.avro.util.Utf8)payload.get("studentName")).toString();
                           Integer age = (Integer) payload.get("age");
                           System.out.println("studentName======>>>"+studentName+age);
                           try {
                               Thread.sleep(50L);
                           } catch (InterruptedException e) {
                               e.printStackTrace();
                           }
                           return null;
                       }
                   }).build();
            triggerRunner.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
