package com.myproject.axom.priceengine.api;

import java.io.Serializable;

/**
 * Created by robinwang on 9/19/16.
 */
public class PriceEntryRegisteredEvent implements Serializable {

    private final String name;
    private final String competitor;
    private final double price;

    protected PriceEntryRegisteredEvent(String name, String competitor, double price) {
        this.name = name;
        this.competitor = competitor;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getCompetitor() {
        return competitor;
    }

    public double getPrice() {
        return price;
    }
}