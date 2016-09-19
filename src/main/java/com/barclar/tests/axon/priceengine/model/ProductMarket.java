package com.barclar.tests.axon.priceengine.model;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by rwang on 9/15/2016.
 */
public class ProductMarket extends AbstractAnnotatedAggregateRoot{
    @AggregateIdentifier
    private String id;
    private String market; // "HH" "HL"...
    public ProductMarket()
    {}
    @CommandHandler
    public ProductMarket(CreateProductMarketCommand command) {
        apply(new ProductMarketCreatedEvent(command.getProductId(), command.getMarket()));
    }
    @EventHandler
    public void on(ProductMarketCreatedEvent event) {
        this.id = event.getProductId();
    }
    @CommandHandler
    public void markCompleted(MarkCompletedCommand command) {
        apply(new ProductMarketCompletedEvent(id));
    }
}
