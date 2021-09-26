package com.gmail.kasabuta4.handson.infrastructure.application;

import com.gmail.kasabuta4.handson.application.C日次新規感染者数検索;
import com.gmail.kasabuta4.handson.domain.C日次新規感染者数;
import com.gmail.kasabuta4.handson.domain.C日次新規感染者数Dao;
import com.gmail.kasabuta4.handson.domain.C日次新規感染者数検索条件;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class C日次新規感染者数検索Impl implements C日次新規感染者数検索 {

  @Inject private C日次新規感染者数Dao dao;

  @Transactional
  @Override
  public List<C日次新規感染者数> execute(C日次新規感染者数検索条件 検索条件) {
    return dao.search(検索条件);
  }
}
