package com.gmail.kasabuta4.handson.domain;

import javax.validation.ConstraintDeclarationException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IntegerRangeMinValidator implements ConstraintValidator<IntegerRangeMin, Range> {

  private static final String DECLARATION_ERROR_MESSAGE =
      "{com.gmail.kasabuta4.handson.domain.IntegerRangeMinValidator.declaration_error_message}";

  private int min;

  @Override
  public void initialize(IntegerRangeMin constraintAnnotation) {
    min = constraintAnnotation.value();
  }

  @Override
  public boolean isValid(Range value, ConstraintValidatorContext context) {
    if (value == null) return true;

    if (!isInteger(value.getStart()) || !isInteger(value.getEnd()))
      throw new ConstraintDeclarationException(DECLARATION_ERROR_MESSAGE);

    if(!isValid((Integer)value.getStart())) buildConstraintViolation(context, "start");

    if(!isValid((Integer)value.getEnd())) buildConstraintViolation(context, "end");

    return isValid((Integer)value.getStart()) && isValid((Integer)value.getEnd());
  }

  private boolean isInteger(Object o) {
    return o == null || o instanceof Integer;
  }

  private boolean isValid(Integer value) {
    return value == null || value >= min;
  }

  private void buildConstraintViolation(ConstraintValidatorContext context, String property) {
    context.disableDefaultConstraintViolation();
    context
        .buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
        .addPropertyNode(property)
        .addConstraintViolation();
  }
}
