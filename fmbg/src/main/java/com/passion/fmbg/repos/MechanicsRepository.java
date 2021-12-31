package com.passion.fmbg.repos;

import com.passion.fmbg.entities.Mechanics;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MechanicsRepository extends CrudRepository<Mechanics, String> {
}
