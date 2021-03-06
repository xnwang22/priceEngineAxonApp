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

package com.myproject.axom.priceengine.api;

import java.io.Serializable;

/**
 * @author Robin Wang 9/19/2016
 */
public class ProductCreatedEvent implements Serializable {
    private final String name;
    private final String market;


    public ProductCreatedEvent(String name, String market) {
        this.market = market;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getMarket() {
        return market;
    }
}
