package com.lonsec.util;

import com.lonsec.pojo.BenchMark;
import com.lonsec.pojo.BenchReturn;
import com.lonsec.pojo.Fund;
import com.lonsec.pojo.FundReturn;
import com.lonsec.pojo.PerformanceResult;
import org.jsefa.Deserializer;
import org.jsefa.csv.CsvIOFactory;
import org.jsefa.csv.config.CsvConfiguration;

import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class FileReaderUtil {
    public static Set<Fund> readFundsFile(String filePath) throws URISyntaxException, IOException {
        assert filePath!= null && !filePath.equals("") : "funds file path can not be null or empty.";
        Set<Fund> funds = new HashSet<>();
        String content = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(filePath).toURI())));
        content = content.substring(content.indexOf("\r\n") + 2,content.length());
        Deserializer deserializer = CsvIOFactory.createFactory(getCsvConfiguration(), Fund.class).createDeserializer();
        StringReader reader = new StringReader(content);
        deserializer.open(reader);
        while (deserializer.hasNext()) {
            Fund fund = deserializer.next();
            funds.add(fund);
        }
        deserializer.close(true);
        return funds;
    }

    private static CsvConfiguration getCsvConfiguration() {
        CsvConfiguration config = new CsvConfiguration();
        config.setFieldDelimiter(',');

        return config;
    }

    public static Set<BenchMark> readBenchMarksFile(String filePath) throws URISyntaxException, IOException {
        assert filePath!= null && !filePath.equals("") : "benchMarks file path can not be null or empty.";
        Set<BenchMark> benchMarks= new HashSet<>();
        String content = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(filePath).toURI())));
        content = content.substring(content.indexOf("\r\n") + 2,content.length());
        Deserializer deserializer = CsvIOFactory.createFactory(getCsvConfiguration(), BenchMark.class)
                .createDeserializer();        StringReader reader = new StringReader(content);
        deserializer.open(reader);
        while (deserializer.hasNext()) {
            BenchMark benchMark = deserializer.next();
            benchMarks.add(benchMark);
        }
        deserializer.close(true);
        return benchMarks;
    }

    public static Set<FundReturn> readFundReturnsFile(String filePath) throws URISyntaxException, IOException {
        assert filePath!= null && !filePath.equals("") : "fundReturns file path can not be null or empty.";
        Set<FundReturn> fundReturns= new HashSet<>();
        String content = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(filePath).toURI())));
        content = content.substring(content.indexOf("\r\n") + 2,content.length());
        CsvConfiguration config =getCsvConfiguration();
        Deserializer deserializer = CsvIOFactory.createFactory(config, FundReturn.class)
                .createDeserializer();
        StringReader reader = new StringReader(content);
        deserializer.open(reader);
        while (deserializer.hasNext()) {
            FundReturn fundReturn = deserializer.next();
            fundReturns.add(fundReturn);
        }
        deserializer.close(true);
        return fundReturns;
    }

    public static Set<BenchReturn> readBenchReturnsFile(String filePath) throws URISyntaxException, IOException {
        assert filePath!= null && !filePath.equals("") : "benchMarkReturns file path can not be null or empty.";
        Set<BenchReturn> benchReturns= new HashSet<>();
        String content = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(filePath).toURI())));
        content = content.substring(content.indexOf("\r\n") + 2,content.length());
        CsvConfiguration config =getCsvConfiguration();
        Deserializer deserializer = CsvIOFactory.createFactory(config, BenchReturn.class)
                .createDeserializer();
        StringReader reader = new StringReader(content);
        deserializer.open(reader);
        while (deserializer.hasNext()) {
            BenchReturn benchReturn = deserializer.next();
            benchReturns.add(benchReturn);
        }
        deserializer.close(true);
        return benchReturns;
    }

    public static Set<PerformanceResult> readReport(String reportGenerationPath) throws URISyntaxException, IOException {
        assert reportGenerationPath!= null && !reportGenerationPath.equals("") : "Report file path can not be null or" +
                " " +
                "empty.";
        Set<PerformanceResult> performanceResults = new HashSet<>();
        String content = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(reportGenerationPath).toURI())));
        content = content.substring(content.indexOf("\n") + 1,content.length());
        CsvConfiguration config =getCsvConfiguration();
        Deserializer deserializer = CsvIOFactory.createFactory(config, PerformanceResult.class)
                .createDeserializer();
        StringReader reader = new StringReader(content);
        deserializer.open(reader);
        while (deserializer.hasNext()) {
            PerformanceResult performanceResult = deserializer.next();
            performanceResults.add(performanceResult);
        }
        deserializer.close(true);
        return performanceResults;
    }
}
