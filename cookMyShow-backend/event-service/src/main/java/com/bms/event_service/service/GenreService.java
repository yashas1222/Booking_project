package com.bms.event_service.service;

import com.bms.event_service.model.Genre;
import com.bms.event_service.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GenreService {
    @Autowired
    GenreRepository genreRepository;

    public ResponseEntity<String> addGenre(String genreName) {
        if (!genreRepository.existsByName(genreName)) {
            Genre genre = new Genre();
            genre.setName(genreName);
            genreRepository.save(genre);
            return new ResponseEntity<>("Success", HttpStatus.OK);

        }
        return new ResponseEntity<>("Genre already exists", HttpStatus.BAD_REQUEST);
    }

        public ResponseEntity<String> addGenreList(List<String> genreList) {
        Set<String> genreSet = new HashSet<>(genreList);
        for(String genreName : genreSet){
            if(!genreRepository.existsByName(genreName)){
                Genre genre = new Genre();
                genre.setName(genreName);
                genreRepository.save(genre);
            }
        }
        return new ResponseEntity<>("Saved Genres List", HttpStatus.OK);
    }

    public ResponseEntity<List<Genre>> getAllGenres() {
        List<Genre> genres = genreRepository.findAll();
        return  new ResponseEntity<>(genres,HttpStatus.OK);
    }

    public ResponseEntity<List<Genre>> getListOfGenre(List<String> genreIds) {
        List<Genre> genres =new ArrayList<>();
        for(String genreId : genreIds){
            Optional<Genre> genre = genreRepository.findById(genreId);
            genre.ifPresent(genres::add);
        }
            return new ResponseEntity<>(genres,HttpStatus.OK);
    }


    public ResponseEntity<Genre> getGenreById(String genreId) {
        Genre genre = genreRepository.findById(genreId).orElseThrow(()-> new RuntimeException("Genre not found!"));
    return new ResponseEntity<>(genre,HttpStatus.OK);
    }
}

