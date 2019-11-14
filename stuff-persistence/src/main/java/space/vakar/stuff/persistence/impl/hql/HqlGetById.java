package space.vakar.stuff.persistence.impl.hql;

import java.util.Objects;
import space.vakar.stuff.persistence.api.Hql;

public class HqlGetById implements Hql {

  private final Class clazz;
  private final int id;

  public HqlGetById(Class clazz, int id) {
    this.clazz = clazz;
    this.id = id;
  }

  @Override
  public String getHql() {
    return String.format("FROM %1$s AS E WHERE E.id = %2$s", clazz.getName(), id);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HqlGetById that = (HqlGetById) o;
    return id == that.id && Objects.equals(clazz, that.clazz);
  }

  @Override
  public int hashCode() {
    return Objects.hash(clazz, id);
  }

  @Override
  public String toString() {
    return "HqlSpecGetById{" + "clazz=" + clazz + ", id=" + id + '}';
  }
}
