package com.gmail.kasabuta4.handson.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.validation.constraints.NotEmpty;

public class C日次新規感染者数検索条件 implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotEmpty(message="必須項目です")
  private String p都道府県;

  private Term<LocalDate> p表示期間 = new LocalDateTerm();

  public C日次新規感染者数検索条件() {
  }

  public C日次新規感染者数検索条件 normalized() {
    C日次新規感染者数検索条件 result = new C日次新規感染者数検索条件();

    result.p都道府県 = p都道府県;
    result.p表示期間 = p表示期間.normalized();

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
