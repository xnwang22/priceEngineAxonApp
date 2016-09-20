/*
 * Copyright (c) 2010. Axon Framework
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

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Robin Wang 9/19/2016
 */
@Repository
@Transactional(readOnly = true)
public class ProductRepositoryImpl implements ProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ProductEntry> findAllProducts() {
        return entityManager.createQuery("SELECT e FROM ProductEntry e")

                .getResultList();
    }

    @Override
    public List<PriceEntry> findAllPriceForProduct(String productName) {
        return entityManager.createQuery("SELECT e FROM PriceEntry e WHERE e.name = :name")
                .setParameter("name", productName)
                .getResultList();
    }
    @Override
    public PriceQuery findFrequentPriceForProduct(String productName) {
        return (PriceQuery) entityManager.createQuery("SELECT e.name, count (e.price), avg (e.price), e.price FROM PriceEntry e WHERE e.name = :name " +
                "group by e.price desc order by count(e.price) desc " +
                "having e.price >= avg(e.price) * 1.5 and e.price <= avg(e.price) * 1.5")
                .setParameter("name", productName).getResultList().get(0);
    }
}
