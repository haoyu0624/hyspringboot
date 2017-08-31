package com.hy.kafka.glodon.dto;

import com.glodon.kafka.api.internal.producer.avro.schema.SchemaMetadata;
import com.glodon.kafka.api.internal.producer.avro.type.PrimitiveTypes;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;

/**
 * Created by haoy on 2017/7/18.
 */
public class OctopusLogger {

    public static void main(String[] args) throws Exception {
        OctopusLogger s = new OctopusLogger();
//        GenericRecord studentRecord = new GenericData.Record(s.getStuedent());
//        studentRecord.put("studentName", "haoyu");
//        studentRecord.put("age", 32);
    }

    public Schema getOctopusLogger()throws Exception{
        SchemaMetadata log = SchemaMetadata.builder().name("log").type("record").namespace("example.avro")
                .addFilelds("msgKey", PrimitiveTypes.string)
                .addFilelds("reqId", PrimitiveTypes.string)
                .addFilelds("data", PrimitiveTypes.string).build();
        return new Schema.Parser().parse(log.json());
    }

}
