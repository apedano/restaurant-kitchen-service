package com.apedano.kitchen;

import com.apedano.restaurant.api.model.KitchenOrderDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import java.util.Random;

import static com.apedano.restaurant.common.ResturantUtility.sleepRandomSeconds;

@ApplicationScoped
@Slf4j
public class KitchenService {
    private static final int MIN_DURATION = 1000; // Minimum sleep duration in milliseconds
    private static final int MAX_DURATION = 5000; // Maximum sleep duration in milliseconds


    @Inject
    @Channel("kitchen-outgoing")
    Emitter<KitchenOrderDto> kitchenOrderEmitter;

    @Incoming("kitchen-incoming")
    public void handleOrderedDish(KitchenOrderDto kitchenOrderDto) {
        log.info("Received kitchen order: {}", kitchenOrderDto);
        sleepRandomSeconds(MIN_DURATION, MAX_DURATION);
        log.info("Preparing kitchen order: {}", kitchenOrderDto);
        sleepRandomSeconds(MIN_DURATION, MAX_DURATION);
        log.info("Ready kitchen order: {}", kitchenOrderDto);
        kitchenOrderEmitter.send(kitchenOrderDto);
    }


}
