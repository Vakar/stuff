package space.vakar.stuff.persistence.api;

import space.vakar.stuff.persistence.model.Stuff;

import java.util.List;

public interface StuffRepositoryService extends RepositoryService<Stuff>{
    List<Stuff> findStuffByUserId(int id);
}
