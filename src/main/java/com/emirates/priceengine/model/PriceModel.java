package com.emirates.priceengine.model;

import java.beans.Transient;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PriceModel {

  private String date;
  private BigDecimal price;
  private String flightNumber;

  @Transient
  public String getPriceKey() {
    Objects.requireNonNull(date);
    Objects.requireNonNull(flightNumber);

    return flightNumber.concat(";").concat(date);
  }

  public String getPriceKey(final String flightNumber, final String date) {
    Objects.requireNonNull(date);
    Objects.requireNonNull(flightNumber);

    return flightNumber.concat(";").concat(date);
  }
}
