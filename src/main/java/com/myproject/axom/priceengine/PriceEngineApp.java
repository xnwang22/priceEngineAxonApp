package com.myproject.axom.priceengine;

import com.myproject.axom.priceengine.api.CreateProductsCommand;
import com.myproject.axom.priceengine.command.ProductsLoadCompletedEvent;
import com.myproject.axom.priceengine.init.ProductGenerator;
import com.myproject.axom.priceengine.query.PriceEntry;
import com.myproject.axom.priceengine.query.PriceQuery;
import com.myproject.axom.priceengine.query.ProductEntry;
import com.myproject.axom.priceengine.query.ProductRepository;
import com.myproject.axom.priceengine.rules.MarketPriceRules;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.commandhandling.SimpleCommandBus;

import com.myproject.axom.priceengine.api.RegisterPriceCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.eventhandling.EventBus;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


/**
 * Created by rwang on 9/15/2016.
 */
public class PriceEngineApp {
    private CommandBus commandBus;

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/spring/application-context.xml",
                "classpath:/META-INF/spring/database-context.xml", "classpath:/META-INF/spring/datainit-context.xml");

        PriceEngineApp theApp = new PriceEngineApp();
        EventBus eventBus = (EventBus) context.getBean("eventBus");
        ProductGenerator productGenerator = (ProductGenerator) context.getBean("productGenerator");
        theApp.commandBus = (CommandBus) context.getBean("commandBus"); //new SimpleCommandBus();
//        try {
//            theApp.loadProducts();
//            theApp.loadPriceDetails();
//            theApp.commandBus.dispatch();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

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