package com.barclar.tests.axon.priceengine.model;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by rwang on 9/15/2016.
 */
public class CreateProductMarketCommand {

    @TargetAggregateIdentifier
    private final String productId;
    private final String market;

    public CreateProductMarketCommand(String productId, String market) {
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