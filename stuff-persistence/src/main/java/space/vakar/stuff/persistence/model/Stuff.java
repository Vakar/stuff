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

  public static final Stuff EMPTY_STUFF = new Stuff(0, "", "", "", BigDecimal.ZERO, null);

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;
  private String brand;
  private String description;
  private BigDecimal cost;

  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

  /** Default constructor. */
  public Stuff() {}

  /**
   * {@link Stuff} parametrised constructor.
   *
   * @param id {@link Stuff} entity id
   * @param name {@link Stuff} name
   * @param brand {@link Stuff} brand
   * @param description {@link Stuff} description
   * @param cost {@link Stuff} cost
   * @param user {@link User} owner of this {@link Stuff}
   */
  public Stuff(int id, String name, String brand, String description, BigDecimal cost, User user) {
    this.id = id;
    this.name = name;
    this.brand = brand;
    this.description = description;
    this.cost = cost;
    this.user = user;
  }

  /**
   * Get {@link Stuff} entity id.
   *
   * @return {@link Stuff} entity id
   */
  public int getId() {
    return id;
  }

  /**
   * Set {@link Stuff} entity id.
   *
   * @param id {@link Stuff} entity id
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Get {@link Stuff} name.
   *
   * @return {@link Stuff} name
   */
  public String getName() {
    return name;
  }

  /**
   * Set {@link Stuff} name.
   *
   * @param name {@link Stuff} name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Get {@link Stuff} brand.
   *
   * @return {@link Stuff} brand
   */
  public String getBrand() {
    return brand;
  }

  /**
   * Set {@link Stuff} brand.
   *
   * @param brand {@link Stuff} brand
   */
  public void setBrand(String brand) {
    this.brand = brand;
  }

  /**
   * Get {@link Stuff} description.
   *
   * @return {@link Stuff} description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Set {@link Stuff} description.
   *
   * @param description {@link Stuff} brand
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Get {@link Stuff} cost.
   *
   * @return {@link Stuff} cost
   */
  public BigDecimal getCost() {
    return cost;
  }

  /**
   * Set {@link Stuff} cost.
   *
   * @param cost {@link Stuff} cost
   */
  public void setCost(BigDecimal cost) {
    this.cost = cost;
  }

  /**
   * Get {@link User} owner of this stuff.
   *
   * @return {@link User} owner of this {@link Stuff}
   */
  public User getUser() {
    return user;
  }

  /**
   * Set {@link User} owner of this stuff.
   *
   * @param user owner of this {@link Stuff}
   */
  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Stuff stuff = (Stuff) o;
    return id == stuff.id
        && Objects.equals(name, stuff.name)
        && Objects.equals(brand, stuff.brand)
        && Objects.equals(description, stuff.description)
        && Objects.equals(cost, stuff.cost);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, brand, description, cost);
  }

  @Override
  public String toString() {
    return "Stuff{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", brand='"
        + brand
        + '\''
        + ", description='"
        + description
        + '\''
        + ", cost="
        + cost
        + '}';
  }
}
