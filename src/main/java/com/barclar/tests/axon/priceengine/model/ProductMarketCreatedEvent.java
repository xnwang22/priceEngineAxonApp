package com.barclar.tests.axon.priceengine.model;

/**
 * Created by rwang on 9/15/2016.
 */
public class ProductMarketCreatedEvent {

    private final String productId;
    private final String market;

    public ProductMarketCreatedEvent(String productId, String market) {
        this.productId = productId;
        this.market = market;
    }

    public String getProductId() {
        return productId;
    }

    public String getMarket() {
        return market;
    }
}
