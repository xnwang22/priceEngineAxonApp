/*
 * Copyright (c) 2010-2011. Axon Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.myproject.axom.priceengine.command;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import com.myproject.axom.priceengine.api.Price;
import com.myproject.axom.priceengine.api.PriceAddedEvent;

import com.myproject.axom.priceengine.api.ProductCreatedEvent;


/**
 * <p>The Aggregate root component of the sample application. This component handles all contact as well as address
 * domain events.</p>
 *
 * @author Robin Wang 9/19/2016
 */
class Product extends AbstractAnnotatedAggregateRoot {


    private String name;

    public Product(String identifier, String name) {
        apply(new ProductCreatedEvent(identifier, name));
    }

    @SuppressWarnings("UnusedDeclaration")
    Product() {
    }

    /**
     * Register the provided price with the provided type. If a contact already has an price of the provided type,
     * that price is changed.
     * @param price Price to add or change
     */
    public void registerPrice(Price price) {

            apply(new PriceAddedEvent( price.getName(), price.getCompetitor(), price.getPrice()));

    }


    @EventHandler
    protected void handleContactCreatedEvent(ProductCreatedEvent event) {
        this.name = event.getName();
    }

    public Object getIdentifier() {
        return name;
    }
}
