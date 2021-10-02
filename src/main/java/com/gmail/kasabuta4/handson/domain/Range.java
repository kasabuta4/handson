package com.gmail.kasabuta4.handson.domain;

import java.io.Serializable;

@InOrder
public class Range<T extends Comparable<? super T>> implements Serializable {

  private T start;
  private T end;

  public Range() {};

  public T getStart() {
    return start;
  }

  public void setStart(T start) {
    this.start = start;
  }

  public T getEnd() {
    return end;
  }

  public void setEnd(T end) {
    this.end = end;
  }
}
