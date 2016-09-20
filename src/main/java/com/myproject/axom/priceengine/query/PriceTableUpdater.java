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

package com.myproject.axom.priceengine.query;

import org.axonframework.eventhandling.annotation.EventHandler;
import com.myproject.axom.priceengine.api.PriceAddedEvent;

import com.myproject.axom.priceengine.api.ProductCreatedEvent;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Robin Wang 9/19/2016
 */
public class PriceTableUpdater {

    @PersistenceContext
    private EntityManager entityManager;

    @EventHandler
    public void handleProductCreatedEvent(ProductCreatedEvent event) {
        ProductEntry entry = new ProductEntry();
        entry.setMarket(event.getMarket());
        entry.setName(event.getName());
        entityManager.persist(entry);
    }



    @EventHandler
    public void handlePriceAddedEvent(PriceAddedEvent event) {
        ProductEntry productEntry = (ProductEntry)
                entityManager.createQuery("SELECT e from ProductEntry e WHERE e.name = :name")
                             .setParameter("name", event.getName())
                             .getSingleResult();
        PriceEntry entry = new PriceEntry();
        entry.setName(event.getName());
        entry.setName(productEntry.getName());
        entry.setCompetitor(event.getCompetitor());
        entry.setPrice(event.getPrice());
        entityManager.persist(entry);
    }
}
