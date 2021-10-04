package com.gmail.kasabuta4.handson.domain;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RUNTIME)
@Repeatable(IntegerRangeMin.List.class)
@Target({FIELD, METHOD, CONSTRUCTOR, PARAMETER, TYPE, ANNOTATION_TYPE, TYPE_USE})
@Constraint(validatedBy = {IntegerRangeMinValidator.class})
public @interface IntegerRangeMin {

  int value();

  String message() default "{com.gmail.kasabuta4.handson.domain.IntegerRangeMin.message}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  @Documented
  @Retention(RUNTIME)
  @Target({FIELD, METHOD, CONSTRUCTOR, PARAMETER, TYPE, ANNOTATION_TYPE, TYPE_USE})
  public @interface List {
    IntegerRangeMin[] value();
  }
}
