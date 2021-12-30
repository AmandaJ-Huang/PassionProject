package com.passion.fmbg.repos;

import com.passion.fmbg.entities.Boardgames;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardgamesRepository extends CrudRepository<Boardgames, Long> {
}
