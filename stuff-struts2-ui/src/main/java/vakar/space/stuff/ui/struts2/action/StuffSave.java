package vakar.space.stuff.ui.struts2.action;

import com.opensymphony.xwork2.ActionSupport;
import space.vakar.stuff.persistence.api.RepositoryService;
import space.vakar.stuff.persistence.impl.StuffService;
import space.vakar.stuff.persistence.model.Stuff;

import java.math.BigDecimal;

public class StuffSave extends ActionSupport {

  private transient RepositoryService<Stuff> service = new StuffService();

  private String name;
  private String cost;

  @Override
  public String execute() {
    Stuff newStuff = new Stuff(name, new BigDecimal(cost));
    service.add(newStuff);
    return SUCCESS;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCost() {
    return cost;
  }

  public void setCost(String cost) {
    this.cost = cost;
  }

  public RepositoryService<Stuff> getService() {
    return service;
  }

  public void setService(RepositoryService<Stuff> service) {
    this.service = service;
  }
}
