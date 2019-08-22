package vakar.space.stuff.ui.struts2.action;

import com.opensymphony.xwork2.ActionSupport;
import space.vakar.stuff.persistence.api.RepositoryService;
import space.vakar.stuff.persistence.impl.StuffService;
import space.vakar.stuff.persistence.model.Stuff;

import java.math.BigDecimal;
import java.util.List;

public class StuffList extends ActionSupport {

  private transient RepositoryService<Stuff> service = new StuffService();

  private List<Stuff> list;
  private String totalCost;

  @Override
  public String execute() {
    list = service.readAll();
    System.out.println(list);
    BigDecimal total = new BigDecimal(0);
    for (Stuff stuff : list) {
      total = total.add(stuff.getCost());
    }
    totalCost = total.toPlainString();
    return SUCCESS;
  }

  public List<Stuff> getList() {
    return list;
  }

  public void setList(List<Stuff> list) {
    this.list = list;
  }

  public String getTotalCost() {
    return totalCost;
  }

  public void setTotalCost(String totalCost) {
    this.totalCost = totalCost;
  }

  public RepositoryService<Stuff> getService() {
    return service;
  }

  public void setService(RepositoryService<Stuff> service) {
    this.service = service;
  }
}
