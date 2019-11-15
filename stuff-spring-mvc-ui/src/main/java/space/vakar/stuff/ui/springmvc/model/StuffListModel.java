package space.vakar.stuff.ui.springmvc.model;

import java.math.BigDecimal;
import java.util.List;

public class StuffListModel {

  private List<StuffDto> stuffDtoList;
  private BigDecimal totalSum;

  public StuffListModel() {}

  public StuffListModel(List<StuffDto> stuffDtoList, BigDecimal totalSum) {
    this.stuffDtoList = stuffDtoList;
    this.totalSum = totalSum;
  }

  public List<StuffDto> getStuffDtoList() {
    return stuffDtoList;
  }

  public void setStuffDtoList(List<StuffDto> stuffDtoList) {
    this.stuffDtoList = stuffDtoList;
  }

  public BigDecimal getTotalSum() {
    return totalSum;
  }

  public void setTotalSum(BigDecimal totalSum) {
    this.totalSum = totalSum;
  }
}
