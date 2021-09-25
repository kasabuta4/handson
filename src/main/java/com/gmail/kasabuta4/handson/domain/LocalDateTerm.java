package com.gmail.kasabuta4.handson.domain;

import java.time.LocalDate;

public class LocalDateTerm extends Term<LocalDate> {

  private static final LocalDate LOWER_BOUND = LocalDate.of(2019, 1, 1);
  private static final LocalDate UPPER_BOUND = LocalDate.now();

  public LocalDateTerm() {
    super();
  }

  @Override
  public Term<LocalDate> normalized() {
    Term<LocalDate> result = new LocalDateTerm();

    result.setFrom(getFrom() == null ? LOWER_BOUND : getFrom());
    result.setTo(getTo() == null ? UPPER_BOUND : getTo());

    return result;
  }
}
