package com.gmail.kasabuta4.handson.domain;

import java.io.Serializable;
import java.time.temporal.Temporal;

public abstract class Term<T extends Temporal & Serializable> implements Serializable {

  private T from;
  private T to;

  protected Term() {};

  public abstract Term<T> normalized();

  public T getFrom() {
    return from;
  }

  public void setFrom(T from) {
    this.from = from;
  }

  public T getTo() {
    return to;
  }

  public void setTo(T to) {
    this.to = to;
  }
}
