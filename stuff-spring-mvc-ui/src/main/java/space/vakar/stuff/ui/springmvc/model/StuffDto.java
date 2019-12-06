package space.vakar.stuff.ui.springmvc.model;

import java.math.BigDecimal;
import java.util.Objects;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class StuffDto {

  private int id;

  @NotBlank
  @Length(max = 128)
  private String name = "";

  @NotBlank
  @Length(max = 128)
  private String brand = "";

  @NotBlank
  @Length(max = 1024)
  private String description = "";

  @DecimalMin(value = "0.0", inclusive = false)
  @Digits(integer = 7, fraction = 2)
  private BigDecimal cost = BigDecimal.ZERO;

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

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
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
    StuffDto stuffDto = (StuffDto) o;
    return id == stuffDto.id &&
            Objects.equals(name, stuffDto.name) &&
            Objects.equals(brand, stuffDto.brand) &&
            Objects.equals(description, stuffDto.description) &&
            Objects.equals(cost, stuffDto.cost);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, brand, description, cost);
  }

  @Override
  public String toString() {
    return "StuffDto{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", brand='" + brand + '\'' +
            ", description='" + description + '\'' +
            ", cost=" + cost +
            '}';
  }
}
