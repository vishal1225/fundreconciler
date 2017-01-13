package com.lonsec.pojo;

import org.jsefa.csv.annotation.CsvDataType;
import org.jsefa.csv.annotation.CsvField;

@CsvDataType()
public class Fund {
    @CsvField(pos = 1)
    private String fundCode;
    @CsvField(pos = 2)
    private String fundName;
    @CsvField(pos = 3)
    private String benchMarkCode;

    public String getBenchMarkCode() {
        return benchMarkCode;
    }

    public void setBenchMarkCode(String benchMarkCode) {
        this.benchMarkCode = benchMarkCode;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fund fund = (Fund) o;

        return fundCode.equals(fund.fundCode);

    }

    @Override
    public int hashCode() {
        return fundCode.hashCode();
    }
}
