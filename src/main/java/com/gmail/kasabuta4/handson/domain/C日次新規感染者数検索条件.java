package com.gmail.kasabuta4.handson.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.validation.constraints.NotEmpty;

public class C日次新規感染者数検索条件 implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotEmpty(message="必須項目です")
  private String p都道府県;

  private LocalDate p表示期間From;

  private LocalDate p表示期間To;

  public C日次新規感染者数検索条件() {
  }

  public C日次新規感染者数検索条件 normalized() {
    C日次新規感染者数検索条件 result = new C日次新規感染者数検索条件();

    result.p都道府県 = p都道府県;
    result.p表示期間From = p表示期間From == null ? LocalDate.of(2019, 1, 1) : p表示期間From;
    result.p表示期間To = p表示期間To == null ? LocalDate.now() : p表示期間To;

    return result;
  }

  public String getP都道府県() {
    return p都道府県;
  }

  public void setP都道府県(String p都道府県) {
    this.p都道府県 = p都道府県;
  }

  public LocalDate getP表示期間From() {
    return p表示期間From;
  }

  public void setP表示期間From(LocalDate p表示期間From) {
    this.p表示期間From = p表示期間From;
  }

  public LocalDate getP表示期間To() {
    return p表示期間To;
  }

  public void setP表示期間To(LocalDate p表示期間To) {
    this.p表示期間To = p表示期間To;
  }
}
