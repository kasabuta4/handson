package com.gmail.kasabuta4.handson.interfaces.web;

import com.gmail.kasabuta4.handson.application.C日次新規感染者数検索;
import com.gmail.kasabuta4.handson.domain.C日次新規感染者数;
import com.gmail.kasabuta4.handson.domain.C日次新規感染者数検索条件;
import java.io.Serializable;
import static java.util.Collections.emptyList;
import static java.util.Collections.unmodifiableMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_WARN;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;

@Named("日次新規感染者数一覧")
@RequestScoped
public class C日次新規感染者数一覧 implements Serializable {

  private static final String EXECUTE_COMPONENT_CLIENT_ID = "search_condition:execute";

  private static final String NOT_FOUND_MESSAGE = "検索結果がありません";

  @Inject private Validator validator;

  @Inject private transient C日次新規感染者数検索 検索;

  @NotNull @Valid private C日次新規感染者数検索条件 p検索条件 = new C日次新規感染者数検索条件();

  private Map<String, String> p都道府県Options;

  private List<C日次新規感染者数> p検索結果 = emptyList();

  @PostConstruct
  private void init() {
    Map<String, String> map = new LinkedHashMap<>();
    map.put("北海道", "Hokkaido");
    map.put("東京都", "Tokyo");
    map.put("大阪府", "Osaka");
    map.put("沖縄県", "Okinawa");
    p都道府県Options = unmodifiableMap(map);
  }

  public void execute() {
    Set<ConstraintViolation<C日次新規感染者数検索条件>> violations = validator.validate(p検索条件);

    violations.stream()
        .map(violation -> new FacesMessage(SEVERITY_ERROR, violation.getMessage(), violation.getMessage()))
        .forEach(message -> FacesContext.getCurrentInstance().addMessage(null, message));

    if (!violations.isEmpty()) return;

    doExecute();

    if (p検索結果.isEmpty()) {
      FacesContext.getCurrentInstance()
          .addMessage(EXECUTE_COMPONENT_CLIENT_ID, creeateNotFoundMessage());
    }
  }

  private void doExecute() {
    p検索結果 = 検索.execute(p検索条件);
  }

  private FacesMessage creeateNotFoundMessage() {
    return new FacesMessage(SEVERITY_WARN, NOT_FOUND_MESSAGE, NOT_FOUND_MESSAGE);
  }

  public C日次新規感染者数検索条件 getP検索条件() {
    return p検索条件;
  }

  public Map<String, String> getP都道府県Options() {
    return p都道府県Options;
  }

  public List<C日次新規感染者数> getP検索結果() {
    return p検索結果;
  }
}
