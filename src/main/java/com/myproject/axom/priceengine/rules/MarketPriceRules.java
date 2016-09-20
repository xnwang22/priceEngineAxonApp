package com.myproject.axom.priceengine.rules;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by robinwang on 9/17/16.
 */
public class MarketPriceRules {
    private static Map<String, MarketRule> ruleMap;
    // use lambda function to capture rules. Will be easy to extend.
    static MarketRule noChange = (double a) -> {return a;};
    static MarketRule increaseTenPercent = (double a) -> a * 1.10;
    static MarketRule increaseFivePercent = (double a) -> a * 1.05;
    static MarketRule decreaseFivePercent = (double a) -> a * 0.95;
    static {
        ruleMap = new HashMap<>();
        ruleMap.put("HH", noChange);
        ruleMap.put("LL", increaseTenPercent);
        ruleMap.put("LH", increaseFivePercent);
        ruleMap.put("HL", decreaseFivePercent);
    }
    public static double applyRule(String market, double price) {

        MarketRule marketRule = ruleMap.get(market);
        if(marketRule == null)
            throw new IllegalArgumentException("Cannot find matching rule for market supply and demand " + market);
        return marketRule.operation(price);
    }
}

