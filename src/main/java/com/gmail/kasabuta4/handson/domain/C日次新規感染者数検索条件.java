package com.gmail.kasabuta4.handson.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.validation.constraints.NotEmpty;

public class C日次新規感染者数検索条件 implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotEmpty(message="必須項目です")
  private String p都道府県;

  private Term<LocalDate> p表示期間 = new Term<>();

  public C日次新規感染者数検索条件() {
  }

  public C日次新規感染者数検索条件 normalized() {
    C日次新規感染者数検索条件 result = new C日次新規感染者数検索条件();

    result.p都道府県 = p都道府県;
    result.p表示期間.setFrom(
        p表示期間.getFrom() == null ? LocalDate.of(2019, 1, 1) : p表示期間.getFrom());
    result.p表示期間.setTo(p表示期間.getTo() == null ? LocalDate.now() : p表示期間.getTo());

    return result;
  }

  public String getP都道府県() {
    return p都道府県;
  }

  public void setP都道府県(String p都道府県) {
    this.p都道府県 = p都道府県;
  }

  public Term<LocalDate> getP表示期間() {
    return p表示期間;
  }
}
