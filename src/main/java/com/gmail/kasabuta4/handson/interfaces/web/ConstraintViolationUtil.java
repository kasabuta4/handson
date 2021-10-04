package com.gmail.kasabuta4.handson.interfaces.web;

import static java.text.MessageFormat.format;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;

import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolation;

public class ConstraintViolationUtil {

  public static <T> void reportMessages(Set<ConstraintViolation<T>> violations) {
    violations.stream()
        .map(ConstraintViolationUtil::toFacesMessage)
        .forEach(message -> FacesContext.getCurrentInstance().addMessage(null, message));
  }

  private static <T> FacesMessage toFacesMessage(ConstraintViolation<T> violation) {
    String message =
        format("{0}:{1}", violation.getPropertyPath().toString(), violation.getMessage());
    return new FacesMessage(SEVERITY_ERROR, message, message);
  }
}
