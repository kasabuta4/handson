package com.gmail.kasabuta4.handson.interfaces.web;

import java.util.Set;
import javax.faces.application.FacesMessage;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolation;

public class ConstraintViolationUtil {

  public static <T> void reportMessages(Set<ConstraintViolation<T>> violations) {
    violations.stream()
        .map(violation ->
            new FacesMessage(SEVERITY_ERROR, violation.getMessage(), violation.getMessage()))
        .forEach(message -> FacesContext.getCurrentInstance().addMessage(null, message));
  }

}
