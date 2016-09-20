/*
 * Copyright (c) 2010. Axon Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.myproject.axom.priceengine.command;

import com.myproject.axom.priceengine.api.CreateProductsCommand;
import com.myproject.axom.priceengine.api.Price;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import com.myproject.axom.priceengine.api.RegisterPriceCommand;

import com.myproject.axom.priceengine.query.ProductRepository;
import org.axonframework.unitofwork.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;


/**
 * <p>Command handler that can be used to create and update Contacts. It can also be used to register and remove
 * addresses.</p>
 * <p>The provided repository is used to store the changes.</p>
 *
 * @author Robin Wang 9/19/2016
 */
public class ProductCommandHandler {

    private final static Logger logger = LoggerFactory.getLogger(ProductCommandHandler.class);
    private Repository<Product> repository;
    private ProductRepository productRepository;

    /**
     * Sets the contact domain event repository.
     *
     * @param repository the contact repository
     */
    public void setRepository(Repository<Product> repository) {
        this.repository = repository;
    }


    /**
     * Sets the query repository for contacts
     *
     * @param productRepository for the query database
     */
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Creates a new contact based on the provided data. The provided user name is tested for uniqueness before
     * continuing.
     * <p/>
     * BEWARE
     * The mechanism to guarantee uniqueness is not a best practice for axon. This is a pretty expensive operation
     * especially when the number of contacts increases. It is better to make the client responsible for guaranteeing
     * unique contact names and make an explicit process to overcome the very rare situation where a duplicate contact
     * name is entered.
     *
     * @param command    CreateProductsCommand object that contains the needed data to create a new contact
     * @param unitOfWork Unit of work for the current running thread
     */
    @CommandHandler
    public void handle(final CreateProductsCommand command, UnitOfWork unitOfWork) {
        logger.debug("Received a command for a new contact with name : {}", command.getProductName());
        Assert.notNull(command.getProductName(), "Name may not be null");


            String contactId = command.getProductId();

            Product product = new Product(contactId, command.getProductName());
            repository.add(product);

    }



    /**
     * Registers an price for the product
     *
     * @param command RegisterPriceCommand that contains all required data
     */
    @CommandHandler
    public void handle(RegisterPriceCommand command) {
        Assert.notNull(command.getProductId(), "Product Identifier may not be null");

        Price price = new Price(command.getName(), command.getCompetitor(), command.getPrice());
        Product product = repository.load(command.getProductId());
        product.registerPrice(price);
    }


}
