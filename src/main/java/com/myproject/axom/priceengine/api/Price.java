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

import java.io.Serializable;

/**
 * <p>Value object representing an price.</p>
 *
 * @author Robin Wang 9/19/2016
 */
public class Price implements Serializable {

    private  String name;
    private  String competitor;
    private  double price;

    public Price(String name, String competitor, double price) {
        this.name = name;
        this.competitor = competitor;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getCompetitor() {
        return competitor;
    }

    public double getPrice() {
        return price;
    }
}
