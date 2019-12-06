package space.vakar.stuff.ui.springmvc.presenter;

import java.util.List;
import java.util.stream.Collectors;
import space.vakar.stuff.persistence.model.Stuff;
import space.vakar.stuff.ui.springmvc.model.StuffDto;

public class Mapper {

  private Mapper() {}

  public static Stuff from(StuffDto model) {
    Stuff stuff = new Stuff();
    stuff.setId(model.getId());
    stuff.setName(model.getName());
    stuff.setBrand(model.getBrand());
    stuff.setDescription(model.getDescription());
    stuff.setCost(model.getCost());
    return stuff;
  }

  public static StuffDto from(Stuff stuff) {
    StuffDto dto = new StuffDto();
    dto.setId(stuff.getId());
    dto.setName(stuff.getName());
    dto.setBrand(stuff.getBrand());
    dto.setDescription(stuff.getDescription());
    dto.setCost(stuff.getCost());
    return dto;
  }

  public static List<StuffDto> from(List<Stuff> stuffList) {
    return stuffList.stream().map(Mapper::from).collect(Collectors.toList());
  }
}