package space.vakar.stuff.springmvc.presenter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.vakar.stuff.persistence.model.Stuff;
import space.vakar.stuff.springmvc.model.StuffDto;

public class Mapper {

  private static final Logger LOGGER = LoggerFactory.getLogger(Mapper.class);
  private static final String DATE_PATTERN = "yyyy-MM-dd";

  private Mapper() {}

  public static Stuff from(StuffDto model) {
    Stuff stuff = new Stuff();
    stuff.setId(model.getId());
    stuff.setName(model.getName());
    stuff.setBrand(model.getBrand());
    stuff.setDescription(model.getDescription());
    stuff.setCost(model.getCost());
    try {
      String strDate = model.getCommissionDate();
      DateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
      Date date = dateFormat.parse(strDate);
      Calendar calendar = dateToCalendar(date);
      stuff.setCommissionDate(calendar);
      stuff.setPicture(model.getPicture().getBytes());
    } catch (IOException e) {
      LOGGER.error("Can't get stuff picture data!", e);
      throw new IllegalArgumentException("Can't get stuff picture data!");
    } catch (ParseException e){
      LOGGER.error("Can't parse date from string!", e);
      throw new IllegalArgumentException("Can't parse date from string!");
    }
    return stuff;
  }

  private static Calendar dateToCalendar(Date date){
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return calendar;
  }

  public static StuffDto from(Stuff stuff) {
    StuffDto dto = new StuffDto();
    dto.setId(stuff.getId());
    dto.setName(stuff.getName());
    dto.setBrand(stuff.getBrand());
    dto.setDescription(stuff.getDescription());
    dto.setCost(stuff.getCost());
    dto.setPicture(null);
    Date date = stuff.getCommissionDate().getTime();
    DateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
    String strDate = dateFormat.format(date);
    dto.setCommissionDate(strDate);
    return dto;
  }

  public static List<StuffDto> from(List<Stuff> stuffList) {
    return stuffList.stream().map(Mapper::from).collect(Collectors.toList());
  }
}
