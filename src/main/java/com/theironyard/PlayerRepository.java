package com.theironyard;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by joshuakeough on 10/7/16.
 */
public interface PlayerRepository extends CrudRepository<Player, Integer> {
    Player findByName(String name);
    Player findByPassword(String password);
}
