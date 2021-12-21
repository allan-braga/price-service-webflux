package com.emirates.priceengine.repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RandomValues {

  private RandomValues() { }

  public static List<String> randomFlightNumbers() {
    return Stream.generate(() -> UUID.randomUUID().toString())
        .limit(1000)
        .collect(Collectors.toList());
  }

  public static BigDecimal randomPrice() {
    return new BigDecimal(BigInteger.valueOf(new Random().nextInt(10000)), 2);
  }
 }
