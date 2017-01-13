package com.lonsec.pojo;

import org.jsefa.common.converter.BigDecimalConverter;
import org.jsefa.csv.annotation.CsvDataType;
import org.jsefa.csv.annotation.CsvField;

import java.math.BigDecimal;
import java.util.Date;

@CsvDataType()
public class FundReturn {

    @CsvField(pos = 1)
    private String fundCode;

    @CsvField(pos = 2, format = "dd/MM/yyyy")
    private Date date;

    @CsvField(pos = 3,converterType = BigDecimalConverter.class)
    private BigDecimal fundReturn;

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getFundReturn() {
        return fundReturn;
    }

    public void setFundReturn(BigDecimal fundReturn) {
        this.fundReturn = fundReturn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FundReturn that = (FundReturn) o;

        if (!fundCode.equals(that.fundCode)) return false;
        if (!date.equals(that.date)) return false;
        return fundReturn.equals(that.fundReturn);

    }

    @Override
    public int hashCode() {
        int result = fundCode.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + fundReturn.hashCode();
        return result;
    }
}
