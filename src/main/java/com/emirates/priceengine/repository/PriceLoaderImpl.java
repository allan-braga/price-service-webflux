package com.emirates.priceengine.repository;

import com.emirates.priceengine.model.PriceModel;
import java.time.LocalDate;
import javax.annotation.PostConstruct;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class PriceLoaderImpl implements PriceLoader {

  private final ReactiveRedisConnectionFactory factory;
  private final ReactiveRedisOperations<String, PriceModel> priceOps;

  public PriceLoaderImpl(
      ReactiveRedisConnectionFactory factory,
      ReactiveRedisOperations<String, PriceModel> priceOps) {
    this.factory = factory;
    this.priceOps = priceOps;
  }

  @PostConstruct
  @Override
  public void loadData() {
    factory
        .getReactiveConnection()
        .serverCommands()
        .flushAll()
        .thenMany(prices())
        .thenMany(priceOps.keys("*"))
        .flatMap(priceOps.opsForValue()::get)
        .subscribe(System.out::println);
  }

  private Flux<Object> prices() {
    return Flux.just(RandomValues.randomFlightNumbers().toArray())
        .map(
            flightNumber ->
                PriceModel.builder()
                    .flightNumber(flightNumber.toString())
                    .date(LocalDate.now().toString())
                    .price(RandomValues.randomPrice())
                    .build())
        .flatMap(price -> priceOps.opsForValue().set(price.getPriceKey(), price));
  }
}
