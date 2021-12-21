package com.emirates.priceengine.api;

import com.emirates.priceengine.model.PriceModel;
import com.emirates.priceengine.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/prices")
@RequiredArgsConstructor
public class PriceController {

  private final PriceService priceService;

  @GetMapping("/price")
  public Mono<PriceModel> price(
      @RequestParam("flightNumber") String flightNumber,
      @RequestParam("date") String date) {
    return priceService.getPrice(flightNumber, date);
  }
}
