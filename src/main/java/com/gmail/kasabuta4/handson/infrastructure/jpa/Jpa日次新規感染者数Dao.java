package com.gmail.kasabuta4.handson.infrastructure.jpa;

import com.gmail.kasabuta4.handson.domain.C日次新規感染者数;
import com.gmail.kasabuta4.handson.domain.C日次新規感染者数Dao;
import com.gmail.kasabuta4.handson.domain.C日次新規感染者数検索条件;
import java.sql.Date;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import java.util.List;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class Jpa日次新規感染者数Dao implements C日次新規感染者数Dao {

  private static final String QUERY_STRING
      = "SELECT "
      + "    REPORTED_DATE, "
      + "    CASES "
      + "FROM "
      + "    NEWLY_CONFIRMED_CASES_DAILY "
      + "WHERE "
      + "        PREFECTURE = ? "
      + "    AND REPORTED_DATE >= ? "
      + "    AND REPORTED_DATE <= ? "
      + "ORDER BY "
      + "    REPORTED_DATE";

  @Inject @JsfDemoDB private EntityManager em;

  @Override
  public List<C日次新規感染者数> search(C日次新規感染者数検索条件 検索条件) {
    C日次新規感染者数検索条件 normalized検索条件 = 検索条件.normalized();

    Stream rawStream = em.createNativeQuery(QUERY_STRING)
        .setParameter(1, normalized検索条件.getP都道府県())
        .setParameter(2, normalized検索条件.getP表示期間From().format(ISO_LOCAL_DATE))
        .setParameter(3, normalized検索条件.getP表示期間To().format(ISO_LOCAL_DATE))
        .getResultStream();

    @SuppressWarnings("unchecked")
    Stream<Object[]> stream = (Stream<Object[]>) rawStream;

    return stream.map(Jpa日次新規感染者数Dao::to日次新規感染者数).collect(toList());
  }

  private static C日次新規感染者数 to日次新規感染者数(Object[] o) {
    C日次新規感染者数 result = new C日次新規感染者数();

    result.setP報告日(((Date) o[0]).toLocalDate());
    result.setP新規感染者数((int) o[1]);

    return result;
  }
}
