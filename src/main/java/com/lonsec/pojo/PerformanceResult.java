package com.lonsec.pojo;

import org.jsefa.common.converter.BigDecimalConverter;
import org.jsefa.csv.annotation.CsvDataType;
import org.jsefa.csv.annotation.CsvField;

import java.math.BigDecimal;
import java.util.Date;

@CsvDataType()
public class PerformanceResult {
    @CsvField(pos = 1)
    private String fundName;
    @CsvField(pos = 2, format = "dd/MM/yyyy")
    private Date date;
    @CsvField(pos = 3,converterType = BigDecimalConverter.class)
    private BigDecimal excess;
    @CsvField(pos = 4)
    private String outperformance;
    @CsvField(pos = 5)
    private int rank;

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getExcess() {
        return excess;
    }

    public void setExcess(BigDecimal excess) {
        this.excess = excess;
        setOutperformance(getOutperformance());
    }

    public String getOutperformance() {
        BigDecimal underPerformRange = new BigDecimal(-1);
        BigDecimal outPerformRange = new BigDecimal(1);
        if (getExcess().compareTo(underPerformRange) < 0 ) {
            return "underPerformed";
        } else if (getExcess().compareTo(outPerformRange) > 0 ) {
            return "outPerformed";
        } else {
            return "satisfactory";
        }
    }

    public void setOutperformance(String outperformance) {
        this.outperformance = outperformance;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PerformanceResult that = (PerformanceResult) o;

        if (!fundName.equals(that.fundName)) return false;
        return date.equals(that.date);

    }

    @Override
    public int hashCode() {
        int result = fundName.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }

}
