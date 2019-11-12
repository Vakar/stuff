package vakar.space.stuff.ui.springmvc.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.Objects;

public class StuffDto {

    private int id;

    @NotBlank
    @Length(max = 128)
    private String name = "";

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=7, fraction=2)
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
        StuffDto that = (StuffDto) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(cost, that.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cost);
    }

    @Override
    public String toString() {
        return "StuffDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }
}
