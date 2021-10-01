package com.gmail.kasabuta4.handson.infrastructure.jpa;

import static java.text.MessageFormat.format;
import static java.util.stream.Collectors.toList;

import com.gmail.kasabuta4.handson.domain.C日次新規感染者数;
import com.gmail.kasabuta4.handson.domain.C日次新規感染者数Dao;
import com.gmail.kasabuta4.handson.domain.C日次新規感染者数検索条件;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Stream;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class Jpa日次新規感染者数Dao implements C日次新規感染者数Dao {

  private static final ResourceBundle SQL_STATEMENTS =
      SqlStatementsResourceUtil.getBundle(Jpa日次新規感染者数Dao.class);

  private static final String SEARCH_SQL_KEY = "Jpa日次新規感染者数DaoSql.SEARCH";

  @Inject @JsfDemoDB private EntityManager em;

  @Override
  public List<C日次新規感染者数> search(C日次新規感染者数検索条件 検索条件) {
    Stream rawStream =
        em.createNativeQuery(format(SQL_STATEMENTS.getString(SEARCH_SQL_KEY), 検索条件.toWhereClause()))
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
