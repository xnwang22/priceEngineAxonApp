package com.barclar.tests.axon.priceengine.model;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by rwang on 9/15/2016.
 */
public class MarkCompletedCommand {

    @TargetAggregateIdentifier
    private final String productId;

    public MarkCompletedCommand(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }
}
