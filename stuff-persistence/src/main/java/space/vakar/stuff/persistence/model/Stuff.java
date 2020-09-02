package space.vakar.stuff.persistence.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.StringJoiner;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "STUFF")
public class Stuff implements Serializable {

  public static final Stuff EMPTY_STUFF = new Builder()
          .id(0)
          .name("")
          .brand("")
          .description("")
          .cost(BigDecimal.ZERO)
          .picture(new byte[1])
          .commissionDate(new GregorianCalendar(1970, Calendar.JANUARY, 1))
          .user(null)
          .build();

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotNull private String name;

  @NotNull private String brand;

  @NotNull
  @Length(max = 1024)
  private String description;

  @NotNull private BigDecimal cost;

  @Lob @NotNull private byte[] picture;

  @NotNull
  @Temporal(TemporalType.DATE)
  @Column(name = "COMMISSION_DATE")
  private Calendar commissionDate;

  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

  /** Default constructor. */
  public Stuff() {}

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

  /**
   * Get picture of stuff.
   *
   * @return picture of {@link Stuff}
   */
  public byte[] getPicture() {
    return picture;
  }

  /**
   * Set picture of stuff.
   *
   * @param picture picture of {@link Stuff}
   */
  public void setPicture(byte[] picture) {
    this.picture = picture;
  }

  /**
   * Get stuff commission date.
   *
   * @return {@link Stuff} commission date
   */
  public Calendar getCommissionDate() {
    return commissionDate;
  }

  /**
   * Set stuff commission date.
   *
   * @param commissionDate {@link Stuff} commission date
   */
  public void setCommissionDate(Calendar commissionDate) {
    this.commissionDate = commissionDate;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Stuff stuff = (Stuff) obj;
    return id == stuff.id
        && Objects.equals(name, stuff.name)
        && Objects.equals(brand, stuff.brand)
        && Objects.equals(description, stuff.description)
        && Objects.equals(cost, stuff.cost)
        && Arrays.equals(picture, stuff.picture)
        && Objects.equals(commissionDate, stuff.commissionDate);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(id, name, brand, description, cost, commissionDate);
    result = 31 * result + Arrays.hashCode(picture);
    return result;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Stuff.class.getSimpleName() + "[", "]")
            .add("id=" + id)
            .add("name='" + name + "'")
            .add("brand='" + brand + "'")
            .add("description='" + description + "'")
            .add("cost=" + cost)
            .add("picture=" + Arrays.toString(picture))
            .add("commissionDate=" + commissionDate)
            .add("user=" + user)
            .toString();
  }

  public static class Builder {
    private int id;
    private String name;
    private String brand;
    private String description;
    private BigDecimal cost;
    private User user;
    private byte[] picture;
    private Calendar commissionDate;

    public Builder id(int val) {
      id = val;
      return this;
    }

    public Builder name(String val) {
      name = val;
      return this;
    }

    public Builder brand(String val) {
      brand = val;
      return this;
    }

    public Builder description(String val) {
      description = val;
      return this;
    }

    public Builder cost(BigDecimal val) {
      cost = val;
      return this;
    }

    public Builder user(User val) {
      user = val;
      return this;
    }

    public Builder picture(byte[] val) {
      picture = val;
      return this;
    }

    public Builder commissionDate(Calendar val) {
      commissionDate = val;
      return this;
    }

    public Stuff build() {
      return new Stuff(this);
    }
  }

  private Stuff(Builder builder) {
    id = builder.id;
    name = builder.name;
    brand = builder.brand;
    description = builder.description;
    cost = builder.cost;
    user = builder.user;
    picture = builder.picture;
    commissionDate = builder.commissionDate;
  }
}
