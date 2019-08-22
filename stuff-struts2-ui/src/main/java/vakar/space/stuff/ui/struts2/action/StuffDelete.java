package vakar.space.stuff.ui.struts2.action;

import com.opensymphony.xwork2.ActionSupport;
import space.vakar.stuff.persistence.api.RepositoryService;
import space.vakar.stuff.persistence.impl.StuffService;
import space.vakar.stuff.persistence.model.Stuff;

public class StuffDelete extends ActionSupport {

  private int id;

  private transient RepositoryService<Stuff> service = new StuffService();

  @Override
  public String execute() {
    service.remove(id);
    return SUCCESS;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public RepositoryService<Stuff> getService() {
    return service;
  }

  public void setService(RepositoryService<Stuff> service) {
    this.service = service;
  }
}
