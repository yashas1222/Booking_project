package com.bms.event_service.controller;


import com.bms.event_service.model.Genre;
import com.bms.event_service.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("genre")
public class GenreController {

    @Autowired
    GenreService genreService;

    @PostMapping("/add")
    public ResponseEntity<String> addGenre(@RequestBody String genreName){
        return genreService.addGenre(genreName);
    }

    @PostMapping("/add/bulk")
    public ResponseEntity<String> addGenreList(@RequestBody List<String> genreList){
        return genreService.addGenreList(genreList);
    }

    @GetMapping("/get/{genreId}")
    public ResponseEntity<Genre> getGenreById(@PathVariable String genreId){
        return genreService.getGenreById(genreId);
    }

    @GetMapping("/getAllGenres")
    public ResponseEntity<List<Genre>> getAllGenres(){
        return genreService.getAllGenres();
    }

    @PostMapping("/getListOfGenre")
    public ResponseEntity<List<Genre>> getListOfGenre(@RequestBody List<String> genreIds){
       return genreService.getListOfGenre(genreIds);
    }
}
