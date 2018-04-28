package com.expedia.linkli;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.kinesis.clientlibrary.interfaces.v2.IRecordProcessorFactory;
import com.amazonaws.services.kinesis.clientlibrary.lib.worker.KinesisClientLibConfiguration;
import com.amazonaws.services.kinesis.clientlibrary.lib.worker.Worker;
import com.expedia.linkli.implement.RecordProcessorFactory;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutionException;

/**
 * Created by linkli on 4/28/2018.
 */
public class TestKinesisKCL {

     public static void main(String[] args) throws UnsupportedEncodingException, ExecutionException, InterruptedException {

         AWSCredentials awsCredentials = new BasicAWSCredentials("AKIAJVANSAR36WDGBCYA"
                 ,"YqmTcYEEr72byrGHrxbjt+y744N/ex8zLIpMe78y");
         AWSStaticCredentialsProvider credentialsProvider= new AWSStaticCredentialsProvider(awsCredentials);

         final KinesisClientLibConfiguration config = new KinesisClientLibConfiguration("ews-linker-test",
                 "ews-linker-test",credentialsProvider,"kinesis-test");
         config.withRegionName("us-west-2");
         final IRecordProcessorFactory recordProcessorFactory = new RecordProcessorFactory();
         final Worker worker = new Worker.Builder()
                 .recordProcessorFactory(recordProcessorFactory)
                 .config(config)
                 .build();
         worker.run();
         while(true){

         }
     }
}
