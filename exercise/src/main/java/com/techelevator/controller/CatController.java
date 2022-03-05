package com.techelevator.controller;

import com.techelevator.dao.CatCardDao;
import com.techelevator.model.CatCard;
import com.techelevator.model.CatFact;
import com.techelevator.model.CatPic;
import com.techelevator.services.CatFactService;
import com.techelevator.services.CatPicService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class CatController {

    private CatCardDao catCardDao;
    private CatFactService catFactService;
    private CatPicService catPicService;

    public CatController(CatCardDao catCardDao, CatFactService catFactService, CatPicService catPicService) {
        this.catCardDao = catCardDao;
        this.catFactService = catFactService;
        this.catPicService = catPicService;
    }

    @RequestMapping(path = "/api/cards", method = RequestMethod.GET)
    public List<CatCard> getCatCards() {
        return this.catCardDao.list();
    }

    @RequestMapping(path = "/api/cards/{id}", method = RequestMethod.GET)
    public CatCard getCatCard(@PathVariable int id) {
        return this.catCardDao.get(id);
    }

    @RequestMapping(path = "/api/cards/random", method = RequestMethod.GET)
    public CatCard getRandomCatCard() {
        CatCard randomCatCard = new CatCard();
        randomCatCard.setCatFact(catFactService.getFact().getText());
        randomCatCard.setImgUrl(catPicService.getPic().getFile());

        return randomCatCard;

    }

    @RequestMapping(path = "/api/cards", method = RequestMethod.POST)
    public boolean saveCatCard(@RequestBody CatCard newCatCard) {
        return this.catCardDao.save(newCatCard);
    }

    @RequestMapping(path = "/api/cards/{id}", method = RequestMethod.PUT)
    public boolean updateCatCard(@RequestBody CatCard catCard, @PathVariable int id) {
        return this.catCardDao.update(id, catCard);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/api/cards/{id}", method = RequestMethod.DELETE)
    public void deleteCatCard(@PathVariable int id) {
        this.catCardDao.delete(id);
    }

}
