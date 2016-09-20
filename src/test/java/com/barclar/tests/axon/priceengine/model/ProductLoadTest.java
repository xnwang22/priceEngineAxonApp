package com.barclar.tests.axon.priceengine.model;

import com.myproject.axom.priceengine.api.CreateProductsCommand;
import com.myproject.axom.priceengine.api.ProductCreatedEvent;
import com.myproject.axom.priceengine.command.ProductsLoadCompletedEvent;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by rwang on 9/15/2016.
 */
public class ProductLoadTest {

    private FixtureConfiguration fixture;
//
//    @Before
//    public void setUp() throws Exception {
//        fixture = Fixtures.newGivenWhenThenFixture(Product.class);
//    }
//
//    @Test
//    public void testCreateProducts() throws Exception {
//        fixture.given()
//                .when(new CreateProductsCommand("todo1", "HH"))
//                .expectEvents(new ProductCreatedEvent("todo1", "need to implement the aggregate"));
//    }
//
//    @Test
//    public void testMarkProductsLoadCompleted() throws Exception {
//        fixture.given(new CreateProductsCommand("todo1", "HH"))
//                .when(new MarkCompletedCommand("todo1"))
//                .expectEvents(new ProductsLoadCompletedEvent("todo1"));
//    }
}