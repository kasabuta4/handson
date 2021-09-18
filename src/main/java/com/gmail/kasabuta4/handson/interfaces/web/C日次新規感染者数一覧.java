package com.gmail.kasabuta4.handson.interfaces.web;

import com.gmail.kasabuta4.handson.application.C日次新規感染者数検索;
import com.gmail.kasabuta4.handson.domain.C日次新規感染者数;
import com.gmail.kasabuta4.handson.domain.C日次新規感染者数検索条件;
import static com.gmail.kasabuta4.handson.interfaces.web.RedirectUtil.redirectTo;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("日次新規感染者数一覧")
@ConversationScoped
public class C日次新規感染者数一覧 implements Serializable {

  @Inject private transient Conversation conversation;

  @Inject private transient C日次新規感染者数検索 検索;

  private C日次新規感染者数検索条件 p検索条件 = new C日次新規感染者数検索条件();

  private List<C日次新規感染者数> p検索結果;

  public String execute() {
    if(conversation.isTransient()) conversation.begin();
    doExecute();
    return redirectTo("result.xhtml");
  }

  public void endConversation() {
    if(!conversation.isTransient()) conversation.end();
  }

  private void doExecute() {
    p検索結果 = 検索.execute(p検索条件);
  }

  public C日次新規感染者数検索条件 getP検索条件() {
    return p検索条件;
  }

  public List<C日次新規感染者数> getP検索結果() {
    return p検索結果;
  }
}
