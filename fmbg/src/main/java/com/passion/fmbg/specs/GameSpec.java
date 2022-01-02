package com.passion.fmbg.specs;

import com.passion.fmbg.entities.Games;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@And({
    @Spec(path = "min_players", spec = Like.class),
    @Spec(path = "max_players", spec = Like.class),
    @Spec(path = "min_playtime", spec = Like.class),
    @Spec(path = "max_playtime", spec = Like.class),
    @Spec(path = "min_age", spec = Like.class),
    @Spec(path = "average_learning_complexity", spec = Like.class)
})

public interface GameSpec extends Specification<Games> {
}
