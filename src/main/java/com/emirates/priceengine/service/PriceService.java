package com.emirates.priceengine.service;

import com.emirates.priceengine.model.PriceModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PriceService {

  private final ReactiveRedisOperations<String, PriceModel> priceOps;

  //TODO: Validate date error
  public Mono<PriceModel> getPrice(final String flightNumber, final String date) {
    final String priceKey = new PriceModel().getPriceKey(flightNumber, date);
    return priceOps.opsForValue().get(priceKey);
  }
}
