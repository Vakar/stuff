package space.vakar.stuff.persistence.impl.hql;

import space.vakar.stuff.persistence.api.Hql;

import java.util.Objects;

public class HqlGetAll implements Hql {

  private Class clazz;

  public HqlGetAll(Class clazz) {
    this.clazz = clazz;
  }

  @Override
  public String getHql() {
    return String.format("FROM %1$s", clazz.getName());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    HqlGetAll hqlGetAll = (HqlGetAll) o;
    return Objects.equals(clazz, hqlGetAll.clazz);
  }

  @Override
  public int hashCode() {
    return Objects.hash(clazz);
  }

  @Override
  public String toString() {
    return "HqlSpecGetAll{" + "clazz=" + clazz + '}';
  }
}
