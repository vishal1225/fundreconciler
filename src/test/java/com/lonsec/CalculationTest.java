package com.lonsec;

import com.lonsec.pojo.PerformanceResult;
import com.lonsec.service.CalculatorService;
import com.lonsec.util.FileReaderUtil;
import junitparams.JUnitParamsRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class CalculationTest {
    @Test
    public void calculationTest () throws IOException, URISyntaxException {
        String fundsFilePath = "fund.csv";
        String benchmarkFilePath = "benchmark.csv";
        String fundReturnsFilePath = "FundReturnSeries.csv";
        String benchMarkReturnsFilePath = "BenchReturnSeries.csv";
        String reportGenerationPath = "Report.csv";
        CalculatorService calculatorService = new CalculatorService(fundsFilePath, benchmarkFilePath,
                fundReturnsFilePath, benchMarkReturnsFilePath, reportGenerationPath);
        calculatorService.performCalculations();
        calculatorService.generateReport();
        Assert.assertTrue(Files.exists(Paths.get("Report.csv")));
        Set<PerformanceResult> expectedPerformanceResultSet = FileReaderUtil.readReport("ExpectedReport.csv");
        Assert.assertEquals(36,expectedPerformanceResultSet.size());

        Set<PerformanceResult> actualPerformanceResult = calculatorService.getPerformanceResults();

        List<PerformanceResult> interstectionofBothSets = expectedPerformanceResultSet.stream().filter
                (actualPerformanceResult::contains).collect(Collectors
                .toList
                ());
        actualPerformanceResult.retainAll(expectedPerformanceResultSet);
        assertEquals(expectedPerformanceResultSet.size(), interstectionofBothSets.size());
        assertEquals(actualPerformanceResult.size(), interstectionofBothSets.size());
    }
}
