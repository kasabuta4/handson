package com.gmail.kasabuta4.handson.interfaces.web;

import com.gmail.kasabuta4.handson.application.C日次新規感染者数検索;
import com.gmail.kasabuta4.handson.domain.C日次新規感染者数;
import com.gmail.kasabuta4.handson.domain.C日次新規感染者数検索条件;
import static com.gmail.kasabuta4.handson.interfaces.web.RedirectUtil.redirectTo;
import java.io.Serializable;
import static java.util.Collections.unmodifiableMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import static javax.faces.application.FacesMessage.SEVERITY_WARN;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Named("日次新規感染者数一覧")
@ConversationScoped
public class C日次新規感染者数一覧 implements Serializable {

  private static final String EXECUTE_COMPONENT_CLIENT_ID = "search_condition:execute";

  private static final String NOT_FOUND_MESSAGE = "検索結果がありません";

  @Inject private transient Conversation conversation;

  @Inject private transient C日次新規感染者数検索 検索;

  @NotNull @Valid private C日次新規感染者数検索条件 p検索条件 = new C日次新規感染者数検索条件();

  private Map<String, String> p都道府県Options;

  private List<C日次新規感染者数> p検索結果;

  @PostConstruct
  private void init() {
    Map<String, String> map = new LinkedHashMap<>();
    map.put("北海道", "Hokkaido");
    map.put("東京都", "Tokyo");
    map.put("大阪府", "Osaka");
    map.put("沖縄県", "Okinawa");
    p都道府県Options = unmodifiableMap(map);
  }

  public String execute() {
    doExecute();

    if (!p検索結果.isEmpty()) {
      if (conversation.isTransient()) conversation.begin();
      return redirectTo("result.xhtml");
    } else {
      FacesContext.getCurrentInstance()
          .addMessage(EXECUTE_COMPONENT_CLIENT_ID, creeateNotFoundMessage());
      return null;
    }
  }

  public void endConversation() {
    if(!conversation.isTransient()) conversation.end();
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
