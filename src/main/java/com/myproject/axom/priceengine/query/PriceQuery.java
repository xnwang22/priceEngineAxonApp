package com.myproject.axom.priceengine.query;

/**
 * Created by robinwang on 9/19/16.
 */
public class PriceQuery {
    private String name;
    private Integer count;
    private double avg;
    private double price;

    public PriceQuery(String name, Integer count, double avg, double price) {
        this.name = name;
        this.count = count;
        this.avg = avg;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
