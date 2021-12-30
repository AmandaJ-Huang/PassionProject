package com.passion.fmbg.repos;

import com.passion.fmbg.entities.Boardgames;
import org.springframework.data.repository.CrudRepository;

public interface BoardgamesRepository extends CrudRepository<Boardgames, Long> {
}
