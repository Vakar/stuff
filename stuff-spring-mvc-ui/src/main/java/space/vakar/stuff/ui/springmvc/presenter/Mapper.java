package space.vakar.stuff.ui.springmvc.presenter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.vakar.stuff.persistence.model.Stuff;
import space.vakar.stuff.ui.springmvc.model.StuffDto;

public class Mapper {

  private static final Logger LOGGER = LoggerFactory.getLogger(Mapper.class);

  private Mapper() {}

  public static Stuff from(StuffDto model) {
    Stuff stuff = new Stuff();
    stuff.setId(model.getId());
    stuff.setName(model.getName());
    stuff.setBrand(model.getBrand());
    stuff.setDescription(model.getDescription());
    stuff.setCost(model.getCost());
    try {
      stuff.setPicture(model.getPicture().getBytes());
    } catch (IOException e) {
      LOGGER.error("Can't get stuff picture data!", e);
      throw new IllegalArgumentException("Can't get stuff picture data!");
    }
    return stuff;
  }

  public static StuffDto from(Stuff stuff) {
    StuffDto dto = new StuffDto();
    dto.setId(stuff.getId());
    dto.setName(stuff.getName());
    dto.setBrand(stuff.getBrand());
    dto.setDescription(stuff.getDescription());
    dto.setCost(stuff.getCost());
    dto.setPicture(null);
    return dto;
  }

  public static List<StuffDto> from(List<Stuff> stuffList) {
    return stuffList.stream().map(Mapper::from).collect(Collectors.toList());
  }
}
