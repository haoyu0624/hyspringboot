package com.hy.kafka.glodon.dto;

import com.glodon.kafka.api.internal.producer.avro.schema.SchemaMetadata;
import com.glodon.kafka.api.internal.producer.avro.type.PrimitiveTypes;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;

/**
 * Created by haoy on 2017/7/18.
 */
public class Student {

    public static void main(String[] args) throws Exception {
        Student s = new Student();
        GenericRecord studentRecord = new GenericData.Record(s.getStuedent());
//        studentRecord.put("studentName", "haoyu");
//        studentRecord.put("age", 32);
    }

    public Schema getStuedent()throws Exception{
        SchemaMetadata student = SchemaMetadata.builder().name("Student").type("record").namespace("example.avro")
                .addFilelds("studentName", PrimitiveTypes.string)
                .addFilelds("age", PrimitiveTypes.Int)
                .build();
        return new Schema.Parser().parse(student.json());
    }

}
