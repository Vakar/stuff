package space.vakar.stuff.ui.springmvc.util;

import space.vakar.stuff.persistence.model.Stuff;
import space.vakar.stuff.ui.springmvc.model.StuffDto;

import java.util.List;
import java.util.stream.Collectors;

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

  public static List<StuffDto> from(List<Stuff> stuffList) {
    return stuffList.stream().map(Mapper::from).collect(Collectors.toList());
  }
}
