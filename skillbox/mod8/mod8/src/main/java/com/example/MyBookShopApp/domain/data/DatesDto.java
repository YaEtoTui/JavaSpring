package com.example.MyBookShopApp.domain.data;

import java.util.Date;

public class DatesDto {

    public DatesDto(Date date1, Date date2) {
        this.date1 = date1;
        this.date2 = date2;
    }

    public DatesDto() {
    }

    Date date1;
    Date date2;

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }
}
