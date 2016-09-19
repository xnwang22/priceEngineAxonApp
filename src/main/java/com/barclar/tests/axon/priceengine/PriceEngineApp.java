package com.barclar.tests.axon.priceengine;

import com.barclar.tests.axon.priceengine.model.CreateProductMarketCommand;
import com.barclar.tests.axon.priceengine.model.MarkCompletedCommand;
import com.barclar.tests.axon.priceengine.model.ProductMarket;
import com.barclar.tests.axon.priceengine.model.ProductMarketEventHandler;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.annotation.AggregateAnnotationCommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventhandling.annotation.AnnotationEventListenerAdapter;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventstore.EventStore;
import org.axonframework.eventstore.fs.FileSystemEventStore;
import org.axonframework.eventstore.fs.SimpleEventFileResolver;

import java.io.File;
import java.util.UUID;

/**
 * Created by rwang on 9/15/2016.
 */
public class PriceEngineApp {

    public static void main(String[] args) {

        CommandBus commandBus = new SimpleCommandBus();

        CommandGateway commandGateway = new DefaultCommandGateway(commandBus);

        // we'll store Events on the FileSystem, in the "events/" folder
        EventStore eventStore = new FileSystemEventStore(new SimpleEventFileResolver(new File("./events")));


        EventBus eventBus = new SimpleEventBus();

        EventSourcingRepository repository = new EventSourcingRepository(ProductMarket.class, eventStore);
        repository.setEventBus(eventBus);

        AggregateAnnotationCommandHandler.subscribe(ProductMarket.class, repository, commandBus);

        // and let's send some Commands on the CommandBus.
        final String itemId = UUID.randomUUID().toString();
        AnnotationEventListenerAdapter.subscribe(new ProductMarketEventHandler(), eventBus);
        commandGateway.send(new CreateProductMarketCommand(itemId, "Need to do this"));
        commandGateway.send(new MarkCompletedCommand(itemId));
    }
}