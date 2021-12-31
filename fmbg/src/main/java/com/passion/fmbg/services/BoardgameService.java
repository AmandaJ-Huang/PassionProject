package com.passion.fmbg.services;

import com.passion.fmbg.entities.Boardgames;
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

    public Boardgames create(Boardgames bg) {
        return bgrepo.save(bg);
    }

    public Boardgames findById(Long id) {
        return bgrepo.findById(id).get();
    }

    public List<Boardgames> findAll() {
        Iterable<Boardgames> allBg = bgrepo.findAll();
        List<Boardgames> bgList = new ArrayList<>();
        allBg.forEach(bgList::add);
        return bgList;
    }

    public Boardgames update(Long id, Boardgames newBgData) {
        Boardgames bgInDatabase = this.findById(id);

        bgInDatabase = bgrepo.save(bgInDatabase);

        return bgInDatabase;
    }

    public Boardgames deleteById(Long id) {
        Boardgames bgToBeDeleted = this.findById(id);
        bgrepo.delete(bgToBeDeleted);
        return bgToBeDeleted;
    }
}
