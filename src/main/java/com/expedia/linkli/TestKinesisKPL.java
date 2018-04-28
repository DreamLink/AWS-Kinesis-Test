package com.expedia.linkli;

import com.amazonaws.services.kinesis.producer.Attempt;
import com.amazonaws.services.kinesis.producer.KinesisProducer;
import com.amazonaws.services.kinesis.producer.KinesisProducerConfiguration;
import com.amazonaws.services.kinesis.producer.UserRecordResult;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by linkli on 4/28/2018.
 */
public class TestKinesisKPL {

     public static void main(String[] args) throws UnsupportedEncodingException, ExecutionException, InterruptedException {

         KinesisProducerConfiguration config = new KinesisProducerConfiguration()
                 .setRecordMaxBufferedTime(3000)
                 .setMaxConnections(1)
                 .setRequestTimeout(60000)
                 .setRegion("us-west-2");

         KinesisProducer kinesis = new KinesisProducer(config);

        // Put some records and save the Futures
         List<Future<UserRecordResult>> putFutures = new LinkedList<Future<UserRecordResult>>();
         for (int i = 0; i < 100; i++) {
             Thread.sleep(1000);
             ByteBuffer data = ByteBuffer.wrap("myData".getBytes("UTF-8"));
             // doesn't block
             putFutures.add(
                     kinesis.addUserRecord("ews-linker-test", "myPartitionKey", data));
         }

            // Wait for puts to finish and check the results
         for (Future<UserRecordResult> f : putFutures) {
             UserRecordResult result = f.get(); // this does block
             if (result.isSuccessful()) {
                 System.out.println("Put record into shard " +
                         result.getShardId());
             } else {
                 for (Attempt attempt : result.getAttempts()) {
                     // Analyze and respond to the failure
                 }
             }
         }
     }
}
