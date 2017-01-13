package com.lonsec.service;

import com.lonsec.pojo.BenchMark;
import com.lonsec.pojo.BenchReturn;
import com.lonsec.pojo.Fund;
import com.lonsec.pojo.FundReturn;
import com.lonsec.pojo.FundReturnComparator;
import com.lonsec.pojo.PerformanceResult;
import com.lonsec.util.FileReaderUtil;
import com.lonsec.util.ReportGenerator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CalculatorService {

    Log log = LogFactory.getLog(CalculatorService.class);
    Set<Fund> funds;
    Set<BenchMark> benchmarks;
    Set<FundReturn> fundReturns;
    Set<BenchReturn> benchReturns;
    String reportGenerationPath;
    Set<PerformanceResult> performanceResults;

    public Set<PerformanceResult> getPerformanceResults() {
        return performanceResults;
    }

    public CalculatorService(String fundsFilePath, String benchmarkFilePath, String fundReturnsFilePath, String
            benchMarkReturnsFilePath, String reportGenerationPath) {
        assert fundsFilePath != null && !fundsFilePath.equals("") : "Path for BenchMark file can not be null.";
        assert benchmarkFilePath !=null && !benchmarkFilePath.equals("") : "Path for BenchMark file can not be null.";
        assert fundReturnsFilePath !=null && !fundReturnsFilePath.equals("") : "Path for Fund Returns file can not be null.";
        assert benchMarkReturnsFilePath !=null && !benchMarkReturnsFilePath.equals("") : "Path for BenchMark Returns file can " +
                "not be null.";
        assert reportGenerationPath !=null && !reportGenerationPath.equals("") : "Path for generating final report file can " +
                "not be null.";
        try {
            this.funds = FileReaderUtil.readFundsFile(fundsFilePath);
            this.benchmarks = FileReaderUtil.readBenchMarksFile(benchmarkFilePath);
            this.fundReturns = FileReaderUtil.readFundReturnsFile(fundReturnsFilePath);
            this.benchReturns = FileReaderUtil.readBenchReturnsFile(benchMarkReturnsFilePath);
            this.performanceResults = new HashSet<>();
            this.reportGenerationPath = reportGenerationPath;
        } catch (URISyntaxException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public void performCalculations() {
        log.debug("Started performing Calculations.");
        if ( fundReturns != null && benchReturns != null) {
            Iterator iterator = fundReturns.iterator();
            while (iterator.hasNext()) {
                PerformanceResult performanceResult = new PerformanceResult();
                FundReturn fundReturn = (FundReturn) iterator.next();
                BenchReturn benchMarkReturn = benchReturns.stream().filter(benchReturn -> fundReturn.getDate().equals
                        (benchReturn.getDate()))
                        .findFirst().orElse(null);
                BigDecimal excess = fundReturn.getFundReturn().subtract(benchMarkReturn.getBenchReturn()).setScale(2, BigDecimal.ROUND_DOWN);
                performanceResult.setExcess(excess);
                int rank = calculateRank(fundReturn);
                performanceResult.setRank(rank);
                performanceResult.setFundName(getFundName(fundReturn));
                performanceResult.setDate(fundReturn.getDate());
                this.performanceResults.add(performanceResult);
            }
        } else{
            log.error("No fund or benchmark returns to process.");
        }
        log.debug("Finished performing Calculations.");
    }

    private String getFundName(FundReturn fundReturn) {
        Fund fund =  funds.stream().filter(fundItem -> fundReturn.getFundCode()
                .equals(fundItem
                        .getFundCode())).findFirst().orElse(null);
        return fund.getFundName();
    }

    private int calculateRank(FundReturn fundReturn) {
        List<FundReturn> fundReturnForMonth = fundReturns.stream().filter(fundReturnItem -> fundReturn.getDate()
                .equals(fundReturnItem
                .getDate()))
                .collect
                (Collectors.toList());
        Collections.sort(fundReturnForMonth, Collections.reverseOrder(new FundReturnComparator()));
        return fundReturnForMonth.indexOf(fundReturn) + 1;
    }

    public void generateReport() throws IOException {
        log.debug("Started generating final report");
        ReportGenerator.generateReport(performanceResults);
        log.debug("Final report generated at " + reportGenerationPath);
    }
}
