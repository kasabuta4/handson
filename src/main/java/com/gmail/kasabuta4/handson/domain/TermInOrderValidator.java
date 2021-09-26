package com.gmail.kasabuta4.handson.domain;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TermInOrderValidator implements ConstraintValidator<InOrder, Term<?>> {

  @Override
  public void initialize(InOrder constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(Term<?> value, ConstraintValidatorContext context) {
    if (value == null || value.getFrom() == null || value.getTo() == null) return true;
    return ((Comparable) value.getFrom()).compareTo((Comparable) (value.getTo())) <= 0;
  }
}
