package com.gmail.kasabuta4.handson.application;

import com.gmail.kasabuta4.handson.domain.C日次新規感染者数;
import com.gmail.kasabuta4.handson.domain.C日次新規感染者数検索条件;
import java.util.List;

public interface C日次新規感染者数検索 {

  List<C日次新規感染者数> execute(C日次新規感染者数検索条件 検索条件);
}
