package com.lonsec.pojo;

import org.jsefa.csv.annotation.CsvDataType;
import org.jsefa.csv.annotation.CsvField;

@CsvDataType()
public class BenchMark {
    @CsvField(pos = 1)
    private String benchMarkCode;
    @CsvField(pos = 2)
    private String benchMarkName;

    public String getBenchMarkCode() {
        return benchMarkCode;
    }

    public void setBenchMarkCode(String benchMarkCode) {
        this.benchMarkCode = benchMarkCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BenchMark benchMark = (BenchMark) o;

        return benchMarkCode.equals(benchMark.benchMarkCode);

    }

    @Override
    public int hashCode() {
        return benchMarkCode.hashCode();
    }
}

