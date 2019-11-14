package vakar.space.stuff.ui.springmvc.util;

import space.vakar.stuff.persistence.model.Stuff;
import vakar.space.stuff.ui.springmvc.model.StuffDto;

public class Mapper {

  private Mapper() {}

  public static Stuff from(StuffDto model) {
    Stuff stuff = new Stuff();
    stuff.setId(model.getId());
    stuff.setName(model.getName());
    stuff.setCost(model.getCost());
    return stuff;
  }

  public static StuffDto from(Stuff stuff) {
    StuffDto dto = new StuffDto();
    dto.setId(stuff.getId());
    dto.setName(stuff.getName());
    dto.setCost(stuff.getCost());
    return dto;
  }
}
