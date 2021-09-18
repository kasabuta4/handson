package com.gmail.kasabuta4.handson.domain;

import java.io.Serializable;

public class C日次新規感染者数検索条件 implements Serializable {

  private static final long serialVersionUID = 1L;

  private String p都道府県;

  public C日次新規感染者数検索条件() {
  }

  public String getP都道府県() {
    return p都道府県;
  }

  public void setP都道府県(String p都道府県) {
    this.p都道府県 = p都道府県;
  }
}
