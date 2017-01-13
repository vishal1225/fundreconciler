package com.lonsec.util;

import org.junit.Assert;
import org.junit.Test;

public class FileReaderUtilTest {

    @Test(expected = AssertionError.class)
    public void readFundsFile() throws Exception {
        FileReaderUtil.readFundsFile(null);
        FileReaderUtil.readFundsFile("");
        Assert.assertEquals(6,FileReaderUtil.readFundsFile("funds.csv").size());
    }

    @Test(expected = AssertionError.class)
    public void readBenchMarksFile() throws Exception {
        FileReaderUtil.readBenchMarksFile(null);
        FileReaderUtil.readBenchMarksFile("");
        Assert.assertEquals(2,FileReaderUtil.readBenchMarksFile("benchmark.csv").size());
    }

    @Test(expected = AssertionError.class)
    public void readFundReturnsFile() throws Exception {
        FileReaderUtil.readFundReturnsFile(null);
        FileReaderUtil.readFundReturnsFile("");
        Assert.assertEquals(14, FileReaderUtil.readFundReturnsFile("FundReturnSeries.csv").size());
    }

    @Test(expected = AssertionError.class)
    public void readBenchReturnsFile() throws Exception {
        FileReaderUtil.readBenchMarksFile(null);
        FileReaderUtil.readBenchMarksFile("");
        Assert.assertEquals(14, FileReaderUtil.readFundReturnsFile("BenchReturnSeries.csv").size());
    }

}