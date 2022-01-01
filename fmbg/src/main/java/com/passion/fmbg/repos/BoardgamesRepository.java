package com.passion.fmbg.repos;

import com.passion.fmbg.entities.Games;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardgamesRepository extends CrudRepository<Games, Long> {
}
