package com.passion.fmbg.services;

import com.passion.fmbg.entities.Games;
import com.passion.fmbg.repos.BoardgamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardgameService {

    private BoardgamesRepository bgrepo;

    @Autowired
    public BoardgameService(BoardgamesRepository bgrepo) {
        this.bgrepo = bgrepo;
    }

    public Games create(Games bg) {
        return bgrepo.save(bg);
    }

    public Games findById(Long id) {
        return bgrepo.findById(id).get();
    }

    public List<Games> findAll() {
        Iterable<Games> allBg = bgrepo.findAll();
        List<Games> bgList = new ArrayList<>();
        allBg.forEach(bgList::add);
        return bgList;
    }

    public Games update(Long id, Games newBgData) {
        Games bgInDatabase = this.findById(id);

        bgInDatabase = bgrepo.save(bgInDatabase);

        return bgInDatabase;
    }

    public Games deleteById(Long id) {
        Games bgToBeDeleted = this.findById(id);
        bgrepo.delete(bgToBeDeleted);
        return bgToBeDeleted;
    }
}
