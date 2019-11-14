package space.vakar.stuff.persistence.impl;

import java.util.Objects;

class HqlRemoveById implements space.vakar.stuff.persistence.api.Hql {

  private final Class clazz;
  private final int id;

  HqlRemoveById(Class clazz, int id) {
    this.clazz = clazz;
    this.id = id;
  }

  @Override
  public String getHql() {
    return String.format("DELETE FROM %1$s AS E WHERE E.id = %2$s", clazz.getName(), id);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HqlRemoveById that = (HqlRemoveById) o;
    return id == that.id && Objects.equals(clazz, that.clazz);
  }

  @Override
  public int hashCode() {
    return Objects.hash(clazz, id);
  }

  @Override
  public String toString() {
    return "HqlSpecRemoveById{" + "clazz=" + clazz + ", id=" + id + '}';
  }
}
