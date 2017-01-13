package com.lonsec.util;

import com.lonsec.pojo.PerformanceResult;
import org.jsefa.Serializer;
import org.jsefa.csv.CsvIOFactory;
import org.jsefa.csv.config.CsvConfiguration;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Set;

public class ReportGenerator {
    public static void generateReport(Set<PerformanceResult> performanceResults) throws IOException {
        Serializer serializer = CsvIOFactory.createFactory(getCsvConfiguration(), PerformanceResult.class)
                .createSerializer();
        Path path = Paths.get("Report.csv");
        BufferedWriter writer = new BufferedWriter(Files.newBufferedWriter(path));
        writer.write("FundName,Date,Return,Outperformance,Rank\r\n");
        serializer.open(writer);
        Iterator iterator = performanceResults.iterator();
        while(iterator.hasNext()) {
            serializer.write(iterator.next());
        }
        serializer.close(true);
    }
    private static CsvConfiguration getCsvConfiguration() {
        CsvConfiguration config = new CsvConfiguration();
        config.setFieldDelimiter(',');

        return config;
    }
}
