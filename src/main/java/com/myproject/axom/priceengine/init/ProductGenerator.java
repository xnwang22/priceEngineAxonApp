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

package com.myproject.axom.priceengine.init;

import com.myproject.axom.priceengine.api.CreateProductsCommand;
import com.myproject.axom.priceengine.api.RegisterPriceCommand;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.GenericCommandMessage;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author Robin Wang 9/19/2016
 */
public class ProductGenerator implements ApplicationListener {

    private CommandBus commandBus;
    private AtomicBoolean initialized = new AtomicBoolean();
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public ProductGenerator(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    public void onApplicationEvent(ApplicationEvent event) {
        if (!initialized.get() && event instanceof ContextRefreshedEvent) {
            initializeData();
        }
    }

    public void initializeData() {

        try {
            loadProducts();
            loadPriceDetails();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void loadProducts() throws IOException{

        System.out.println("Please give product count:");
        int productCount = Integer.parseInt(reader.readLine());
        if (productCount < 1) {
            System.out.println("Number of product count has to be greater than 0");
            throw new IllegalArgumentException("Number of product count has to be greater than 0");
        }
        for (int i = 0; i < productCount; i++) {
            String input = reader.readLine();
            String[] fields = input.split(" ");
            if (fields == null || fields.length != 3) {
                throw new IllegalArgumentException("Product Market details has " + fields.length + " fields.");
            }

            CreateProductsCommand command = new CreateProductsCommand();
            command.setProductName(fields[0]);
            command.setMarket(fields[1] + fields[2]);

            commandBus.dispatch(new GenericCommandMessage<Object>(command));

        }

    }

    private void loadPriceDetails() throws IOException {

        System.out.println("Please give price details count:");
        int productDetailsCount = Integer.parseInt(reader.readLine());

        if (productDetailsCount < 1) {
            System.out.println("Number of price count has to be greater than 0");
            throw new IllegalArgumentException("Number of price count has to be greater than 0");
        }

        for (int i = 0; i < productDetailsCount; i++) {
            String input = reader.readLine();
            String[] fields = input.split(" ");
            if (fields == null || fields.length != 3) {
                throw new IllegalArgumentException("Product price details has " + fields.length + " fields.");
            }
            RegisterPriceCommand registerPriceCommand = new RegisterPriceCommand();
            registerPriceCommand.setName(fields[0]);
            registerPriceCommand.setCompetitor(fields[1]);
            registerPriceCommand.setPrice(Double.valueOf(fields[2]));
            commandBus.dispatch(new GenericCommandMessage<Object>(registerPriceCommand));
        }

    }
}
