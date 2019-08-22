package vakar.space.stuff.ui.struts2.action;

import com.opensymphony.xwork2.ActionSupport;
import space.vakar.stuff.persistence.api.RepositoryService;
import space.vakar.stuff.persistence.impl.StuffService;
import space.vakar.stuff.persistence.model.Stuff;

public class StuffEdit extends ActionSupport {

  private transient RepositoryService<Stuff> service = new StuffService();

  private int id;
  private String name;
  private String cost;

  @Override
  public String execute() {
    Stuff stuff = service.readById(id);
    name = stuff.getName();
    cost = stuff.getCost().toPlainString();
    return SUCCESS;
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
