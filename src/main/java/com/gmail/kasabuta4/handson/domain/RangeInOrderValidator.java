package com.gmail.kasabuta4.handson.domain;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RangeInOrderValidator implements ConstraintValidator<InOrder, Range<?>> {

  @Override
  public void initialize(InOrder constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean isValid(Range<?> value, ConstraintValidatorContext context) {
    if (value == null || value.getStart() == null || value.getEnd() == null) return true;
    return ((Comparable) value.getStart()).compareTo((Comparable) (value.getEnd())) <= 0;
  }
}
