package com.myproject.axom.priceengine.command;

import com.myproject.axom.priceengine.query.PriceQuery;
import com.myproject.axom.priceengine.query.ProductEntry;
import com.myproject.axom.priceengine.query.ProductRepository;
import com.myproject.axom.priceengine.rules.MarketPriceRules;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by rwang on 9/15/2016.
 */
public class ProductsLoadCompleteEventHandler {
    @Autowired
    ProductRepository queryProductRepository;
    @EventHandler
    public void handle(ProductsLoadCompletedEvent event) {
        System.out.println("handling product load complete event");
        List<ProductEntry> productList = queryProductRepository.findAllProducts();
        for (ProductEntry product : productList)
        {
            PriceQuery priceResult = queryProductRepository.findFrequentPriceForProduct(product.getName());
            System.out.println(MarketPriceRules.applyRule(product.getMarket(), priceResult.getPrice()));
        }

    }
}