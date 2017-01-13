package com.lonsec.pojo;

import org.jsefa.common.converter.BigDecimalConverter;
import org.jsefa.csv.annotation.CsvDataType;
import org.jsefa.csv.annotation.CsvField;

import java.math.BigDecimal;
import java.util.Date;

@CsvDataType()
public class BenchReturn {
    @CsvField(pos = 1)
    private String benchCode;

    @CsvField(pos = 2, format = "yyyy-MM-dd")
    private Date date;

    @CsvField(pos = 3,converterType = BigDecimalConverter.class)
    private BigDecimal benchReturn;

    public String getBenchCode() {
        return benchCode;
    }

    public void setBenchCode(String benchCode) {
        this.benchCode = benchCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getBenchReturn() {
        return benchReturn;
    }

    public void setBenchReturn(BigDecimal benchReturn) {
        this.benchReturn = benchReturn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BenchReturn that = (BenchReturn) o;

        if (!benchCode.equals(that.benchCode)) return false;
        if (!date.equals(that.date)) return false;
        return benchReturn.equals(that.benchReturn);

    }

    @Override
    public int hashCode() {
        int result = benchCode.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + benchReturn.hashCode();
        return result;
    }
}
