package space.vakar.stuff.persistence.model;

import space.vakar.stuff.persistence.api.DomainEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Stuff implements DomainEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;
  private BigDecimal cost;

  public Stuff() {}

  public Stuff(String name, BigDecimal cost) {
    this.name = name;
    this.cost = cost;
  }

  public Stuff(int id, String name, BigDecimal cost) {
    this.id = id;
    this.name = name;
    this.cost = cost;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigDecimal getCost() {
    return cost;
  }

  public void setCost(BigDecimal cost) {
    this.cost = cost;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Stuff stuff = (Stuff) o;
    return id == stuff.id && Objects.equals(name, stuff.name) && Objects.equals(cost, stuff.cost);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, cost);
  }

  @Override
  public String toString() {
    return "Stuff{" + "id=" + id + ", name='" + name + '\'' + ", cost=" + cost + '}';
  }
}
