package space.vakar.stuff.springmvc.model;

import java.math.BigDecimal;
import java.util.List;

public class StuffListDto {

  private List<StuffDto> stuff;
  private BigDecimal totalSum;

  public StuffListDto() {}

  public StuffListDto(List<StuffDto> stuff, BigDecimal totalSum) {
    this.stuff = stuff;
    this.totalSum = totalSum;
  }

  public List<StuffDto> getStuff() {
    return stuff;
  }

  public void setStuff(List<StuffDto> stuff) {
    this.stuff = stuff;
  }

  public BigDecimal getTotalSum() {
    return totalSum;
  }

  public void setTotalSum(BigDecimal totalSum) {
    this.totalSum = totalSum;
  }
}
