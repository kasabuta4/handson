package com.gmail.kasabuta4.handson.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

public class C日次新規感染者数検索条件 implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotEmpty(message = "必須項目です")
  private String p都道府県;

  @Valid private Range<LocalDate> p表示期間 = new Range<>();

  @Valid
  @IntegerRangeMin(1000)
  @IntegerRangeMax(5000)
  private Range<Integer> p感染者数範囲 = new Range<>();

  public C日次新規感染者数検索条件() {}

  public String toWhereClause() {
    return SqlBooleanExpression.eqString("PREFECTURE", p都道府県)
        .and(SqlBooleanExpression.geLocalDate("REPORTED_DATE", p表示期間.getStart()))
        .and(SqlBooleanExpression.leLocalDate("REPORTED_DATE", p表示期間.getEnd()))
        .and(SqlBooleanExpression.geInteger("CASES", p感染者数範囲.getStart()))
        .and(SqlBooleanExpression.leInteger("CASES", p感染者数範囲.getEnd()))
        .toWhereClause();
  }

  public String getP都道府県() {
    return p都道府県;
  }

  public void setP都道府県(String p都道府県) {
    this.p都道府県 = p都道府県;
  }

  public Range<LocalDate> getP表示期間() {
    return p表示期間;
  }

  public Range<Integer> getP感染者数範囲() {
    return p感染者数範囲;
  }
}
