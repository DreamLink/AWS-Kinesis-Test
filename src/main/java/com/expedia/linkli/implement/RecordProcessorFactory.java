package com.expedia.linkli.implement;

import com.amazonaws.services.kinesis.clientlibrary.interfaces.v2.IRecordProcessor;
import com.amazonaws.services.kinesis.clientlibrary.interfaces.v2.IRecordProcessorFactory;

/**
 * Created by pact on 4/28/2018.
 */
public class RecordProcessorFactory implements IRecordProcessorFactory {
    public IRecordProcessor createProcessor() {
        return new RecordProcessor() ;
    }
}
