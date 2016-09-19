package com.barclar.tests.axon.priceengine.model;

import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by rwang on 9/15/2016.
 */
public class ProductMarketTest {

    private FixtureConfiguration fixture;

    @Before
    public void setUp() throws Exception {
        fixture = Fixtures.newGivenWhenThenFixture(ProductMarket.class);
    }

    @Test
    public void testCreateToDoItem() throws Exception {
        fixture.given()
                .when(new CreateProductMarketCommand("todo1", "need to implement the aggregate"))
                .expectEvents(new ProductMarketCreatedEvent("todo1", "need to implement the aggregate"));
    }

    @Test
    public void testMarkToDoItemAsCompleted() throws Exception {
        fixture.given(new ProductMarketCreatedEvent("todo1", "need to implement the aggregate"))
                .when(new MarkCompletedCommand("todo1"))
                .expectEvents(new ProductMarketCompletedEvent("todo1"));
    }
}