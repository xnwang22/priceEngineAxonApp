package com.myproject.axom.priceengine.rules;

/**
 * Created by robinwang on 9/18/16.
 */
@FunctionalInterface
interface MarketRule {
    double operation(double a);
}
