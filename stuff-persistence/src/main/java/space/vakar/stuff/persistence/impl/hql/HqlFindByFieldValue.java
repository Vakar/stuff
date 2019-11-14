package space.vakar.stuff.persistence.impl.hql;

import java.util.Objects;
import space.vakar.stuff.persistence.api.Hql;

public class HqlFindByFieldValue implements Hql {

  private final Class clazz;
  private final String fieldName;
  private final String value;

  public HqlFindByFieldValue(Class clazz, String fieldName, String value) {
    this.clazz = clazz;
    this.fieldName = fieldName;
    this.value = value;
  }

  @Override
  public String getHql() {
    return String.format("FROM %1$s AS E WHERE E.%2$s='%3$s'", clazz.getName(), fieldName, value);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HqlFindByFieldValue that = (HqlFindByFieldValue) o;
    return Objects.equals(clazz, that.clazz)
        && Objects.equals(fieldName, that.fieldName)
        && Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(clazz, fieldName, value);
  }

  @Override
  public String toString() {
    return "HqlFindByColumnValue{"
        + "aClass="
        + clazz
        + ", fieldName='"
        + fieldName
        + '\''
        + ", value='"
        + value
        + '\''
        + '}';
  }
}
