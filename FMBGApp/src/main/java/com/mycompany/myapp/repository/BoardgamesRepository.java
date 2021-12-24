package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Boardgames;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Boardgames entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BoardgamesRepository extends JpaRepository<Boardgames, Long> {}
