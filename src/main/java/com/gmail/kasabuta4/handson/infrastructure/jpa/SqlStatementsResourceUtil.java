package com.gmail.kasabuta4.handson.infrastructure.jpa;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class SqlStatementsResourceUtil {

  public static ResourceBundle getBundle(Class<?> daoImplClass) {
    return ResourceBundle.getBundle(MessageFormat.format("{0}Sql", daoImplClass.getName()));
  }
}
