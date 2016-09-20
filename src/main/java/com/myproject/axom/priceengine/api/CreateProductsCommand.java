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

package com.myproject.axom.priceengine.api;

import org.springframework.util.Assert;

/**
 * <p>Create a new contact with the provided name</p>
 *
 * @author Robin Wang 9/19/2016
 */
public class CreateProductsCommand extends AbstractOrderCommand {
    private String productName;
    private String market;

   /* public CreateProductsCommand(String name, String market) {

        this.productName = name;
        this.market = market;
    }*/


    public void setProductName(String productName) {
        Assert.hasText(productName, "Name for new product package must contain text");
        this.productName = productName;
    }


    public String getProductName() {
        return productName;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }
}
