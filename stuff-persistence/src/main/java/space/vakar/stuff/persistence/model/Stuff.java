package space.vakar.stuff.persistence.model;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import space.vakar.stuff.persistence.api.DomainEntity;

@Entity
@Table(name = "STUFF")
public class Stuff implements DomainEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;
  private BigDecimal cost;

  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

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

  public Stuff(int id, String name, BigDecimal cost, User user) {
    this.id = id;
    this.name = name;
    this.cost = cost;
    this.user = user;
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

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
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
