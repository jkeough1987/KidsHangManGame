package com.theironyard;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by joshuakeough on 10/7/16.
 */
public interface WordRepository extends CrudRepository<Word, Integer> {
    Word findById(Integer id);
}
