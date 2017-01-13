package com.lonsec;


import com.lonsec.pojo.BenchMark;
import com.lonsec.pojo.BenchReturn;
import com.lonsec.pojo.Fund;
import com.lonsec.pojo.FundReturn;
import com.lonsec.util.FileReaderUtil;
import junitparams.JUnitParamsRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Set;

@RunWith(JUnitParamsRunner.class)
public class FileReaderTest {

    @Test
    public void readFundFile() throws IOException, URISyntaxException {
        String filePath = "fund.csv";
        Set<Fund> fundsFromFile = FileReaderUtil.readFundsFile(filePath);
        Assert.assertEquals(6, fundsFromFile.size());
    }

    @Test
    public void readBenchMarkFile() throws IOException, URISyntaxException {
        String filePath = "benchmark.csv";
        Set<BenchMark> benchMarksFromFile = FileReaderUtil.readBenchMarksFile(filePath);
        Assert.assertEquals(2, benchMarksFromFile.size());
    }

    @Test
    public void readFundReturnsFile() throws IOException, URISyntaxException {
        String filePath = "FundReturnSeries.csv";
        Set<FundReturn> fundReturnsFromFile = FileReaderUtil.readFundReturnsFile(filePath);
        //fundretunrs file has duplicates
        Assert.assertEquals(36, fundReturnsFromFile.size());
    }

    @Test
    public void readBenchReturnsFile() throws IOException, URISyntaxException {
        String filePath = "BenchReturnSeries.csv";
        Set<BenchReturn> bechReturnsFromFile = FileReaderUtil.readBenchReturnsFile(filePath);
        Assert.assertEquals(14, bechReturnsFromFile.size());
    }
}
