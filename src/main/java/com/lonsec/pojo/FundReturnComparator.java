package com.lonsec.pojo;

import java.util.Comparator;

public class FundReturnComparator implements Comparator<FundReturn> {


    @Override
    public int compare(FundReturn o1, FundReturn o2) {
       return o1.getFundReturn().compareTo(o2.getFundReturn());
    }
}
