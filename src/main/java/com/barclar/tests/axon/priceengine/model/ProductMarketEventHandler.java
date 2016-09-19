package com.barclar.tests.axon.priceengine.model;

import org.axonframework.eventhandling.annotation.EventHandler;

/**
 * Created by rwang on 9/15/2016.
 */
public class ProductMarketEventHandler{

    @EventHandler
    public void handle(ProductMarketCreatedEvent event) {
        System.out.println("We've got product do: " + event.getMarket() + " (" + event.getProductId()+ ")");
    }

    @EventHandler
    public void handle(ProductMarketCompletedEvent event) {
        System.out.println("We've completed a task: " + event.getProductId());
    }
}