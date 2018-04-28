package com.expedia.linkli.implement;

import com.amazonaws.services.kinesis.clientlibrary.exceptions.InvalidStateException;
import com.amazonaws.services.kinesis.clientlibrary.exceptions.ShutdownException;
import com.amazonaws.services.kinesis.clientlibrary.interfaces.v2.IRecordProcessor;
import com.amazonaws.services.kinesis.clientlibrary.lib.worker.ShutdownReason;
import com.amazonaws.services.kinesis.clientlibrary.types.InitializationInput;
import com.amazonaws.services.kinesis.clientlibrary.types.ProcessRecordsInput;
import com.amazonaws.services.kinesis.clientlibrary.types.ShutdownInput;

/**
 * Created by pact on 4/28/2018.
 */
public class RecordProcessor implements IRecordProcessor {

    public RecordProcessor(){

    }

    public void initialize(InitializationInput initializationInput) {

    }

    public void processRecords(ProcessRecordsInput processRecordsInput) {

        processRecordsInput.getRecords().stream().forEach(re->{
            String date = new String(re.getData().array());
            System.out.print(re.toString()+"=="+date);
            try {
                processRecordsInput.getCheckpointer().checkpoint();
            } catch (InvalidStateException e) {
                e.printStackTrace();
            } catch (ShutdownException e) {
                e.printStackTrace();
            }
        });

    }

    public void shutdown(ShutdownInput shutdownInput) {
        if(shutdownInput.getShutdownReason() == ShutdownReason.TERMINATE){
            try {
                shutdownInput.getCheckpointer().checkpoint();
            } catch (InvalidStateException e) {
                e.printStackTrace();
            } catch (ShutdownException e) {
                e.printStackTrace();
            }
        }
    }
}
