package com.barclar.tests.axon.priceengine.model;

/**
 * Created by rwang on 9/15/2016.
 */
public class ProductMarketCompletedEvent {

    private final String productId;

    public ProductMarketCompletedEvent(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }
}
