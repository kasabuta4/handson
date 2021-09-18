package com.gmail.kasabuta4.handson.domain;

import java.io.Serializable;
import java.time.LocalDate;

public class C日次新規感染者数 implements Serializable {

  private static final long serialVersionUID = 1L;

  private LocalDate p報告日;

  private int p新規感染者数;

  public C日次新規感染者数() {
  }

  public LocalDate getP報告日() {
    return p報告日;
  }

  public void setP報告日(LocalDate p報告日) {
    this.p報告日 = p報告日;
  }

  public int getP新規感染者数() {
    return p新規感染者数;
  }

  public void setP新規感染者数(int p新規感染者数) {
    this.p新規感染者数 = p新規感染者数;
  }
}
