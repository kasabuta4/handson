package com.gmail.kasabuta4.handson.domain;

import static java.text.MessageFormat.format;
import static java.time.format.DateTimeFormatter.ISO_DATE;
import static java.util.stream.Collectors.joining;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class SqlBooleanExpression {

  public static final SqlBooleanExpression EMPTY = new Empty();

  public final SqlBooleanExpression and(SqlBooleanExpression another) {
    return (this instanceof Empty)
        ? another
        : ((another instanceof Empty) ? this : new And(this, another));
  }

  public final SqlBooleanExpression or(SqlBooleanExpression another) {
    return (this instanceof Empty)
        ? another
        : ((another instanceof Empty) ? this : new Or(this, another));
  }

  public final SqlBooleanExpression not() {
    return (this instanceof Empty)
        ? EMPTY
        : ((this instanceof Not) ? ((Not) this).component : new Not(this));
  }

  public final String toWhereClause() {
    return (this instanceof Empty) ? "" : format("WHERE {0}", toText());
  }

  public abstract String toText();

  public static SqlBooleanExpression eqString(String columnName, String value) {
    return value == null ? EMPTY : new Terminal(columnName, "=", format("''{0}''", value));
  }

  public static SqlBooleanExpression likeString(String columnName, String value) {
    return value == null ? EMPTY : new Terminal(columnName, "LIKE", format("''%{0}%''", value));
  }

  public static SqlBooleanExpression geLocalDate(String columnName, LocalDate value) {
    return value == null
        ? EMPTY
        : new Terminal(columnName, ">=", format("''{0}''", value.format(ISO_DATE)));
  }

  public static SqlBooleanExpression leLocalDate(String columnName, LocalDate value) {
    return value == null
        ? EMPTY
        : new Terminal(columnName, "<=", format("''{0}''", value.format(ISO_DATE)));
  }

  private static class Empty extends SqlBooleanExpression {
    private Empty() {}

    @Override
    public String toText() {
      return "";
    }
  }

  private static class And extends SqlBooleanExpression {

    private final List<SqlBooleanExpression> components;

    private And(SqlBooleanExpression c1, SqlBooleanExpression c2) {
      if (c1 instanceof And && c2 instanceof And) {
        And ac1 = (And) c1;
        And ac2 = (And) c2;
        components = new ArrayList<>(ac1.components.size() + ac2.components.size());
        components.addAll(ac1.components);
        components.addAll(ac2.components);
      } else if (c1 instanceof And) {
        And ac1 = (And) c1;
        components = new ArrayList<>(ac1.components.size() + 1);
        components.addAll(ac1.components);
        components.add(c2);
      } else if (c2 instanceof And) {
        And ac2 = (And) c2;
        components = new ArrayList<>(1 + ac2.components.size());
        components.add(c1);
        components.addAll(ac2.components);
      } else {
        components = new ArrayList<>(2);
        components.add(c1);
        components.add(c2);
      }
    }

    @Override
    public String toText() {
      return components.stream()
          .map(condition -> format("({0})", condition.toText()))
          .collect(joining(" AND "));
    }
  }

  private static class Or extends SqlBooleanExpression {

    private List<SqlBooleanExpression> components;

    private Or(SqlBooleanExpression c1, SqlBooleanExpression c2) {
      if (c1 instanceof Or && c2 instanceof Or) {
        Or ac1 = (Or) c1;
        Or ac2 = (Or) c2;
        components = new ArrayList<>(ac1.components.size() + ac2.components.size());
        components.addAll(ac1.components);
        components.addAll(ac2.components);
      } else if (c1 instanceof Or) {
        Or ac1 = (Or) c1;
        components = new ArrayList<>(ac1.components.size() + 1);
        components.addAll(ac1.components);
        components.add(c2);
      } else if (c2 instanceof Or) {
        Or ac2 = (Or) c2;
        components = new ArrayList<>(1 + ac2.components.size());
        components.add(c1);
        components.addAll(ac2.components);
      } else {
        components = new ArrayList<>(2);
        components.add(c1);
        components.add(c2);
      }
    }

    @Override
    public String toText() {
      return components.stream()
          .map(condition -> format("({0})", condition.toText()))
          .collect(joining(" OR "));
    }
  }

  private static class Not extends SqlBooleanExpression {

    private final SqlBooleanExpression component;

    private Not(SqlBooleanExpression component) {
      this.component = component;
    }

    @Override
    public String toText() {
      return format("NOT ({0})", component.toText());
    }
  }

  private static class Terminal extends SqlBooleanExpression {

    private final String name;
    private final String operator;
    private final String value;

    private Terminal(String name, String operator, String value) {
      this.name = name;
      this.operator = operator;
      this.value = value;
    }

    @Override
    public String toText() {
      return format("{0} {1} {2}", name, operator, value);
    }
  }
}
