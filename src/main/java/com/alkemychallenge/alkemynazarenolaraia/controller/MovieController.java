package com.alkemychallenge.alkemynazarenolaraia.controller;

import com.alkemychallenge.alkemynazarenolaraia.dto.CharacterBasicDTO;
import com.alkemychallenge.alkemynazarenolaraia.dto.CharacterDTO;
import com.alkemychallenge.alkemynazarenolaraia.dto.MovieBasicDTO;
import com.alkemychallenge.alkemynazarenolaraia.dto.MovieDTO;
import com.alkemychallenge.alkemynazarenolaraia.entity.Movie;
import com.alkemychallenge.alkemynazarenolaraia.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieBasicDTO>> getBasicAll() {
        List<MovieBasicDTO> movies = this.movieService.getBasicALL();
        return ResponseEntity.ok().body(movies);
    }

    @PostMapping
    public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO dto) {

        MovieDTO movieSaved = movieService.save(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(movieSaved);


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        this.movieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getDetailsById(@PathVariable Long id) {

        MovieDTO result = this.movieService.getDetailsById(id);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/{id}/characters/{idCharacter}")
    public ResponseEntity<Void> addCharacter(@PathVariable Long id, @PathVariable Long idCharacter) {
        this.movieService.addCharacter(id, idCharacter);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}/characters/{idCharacter}")
    public ResponseEntity<Void> removeCharacte(@PathVariable Long id, @PathVariable Long idCharacter) {
        this.movieService.removeCharacter(id, idCharacter);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> update(@PathVariable Long id, @RequestBody MovieDTO moviedto) {
        MovieDTO result = this.movieService.update(id, moviedto);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<MovieDTO>> getDetailsByFilters(

            @RequestParam (required = false) String title,
            @RequestParam (required = false) Long genre,
            @RequestParam (required = false) String creationDate,
            @RequestParam(required = false,defaultValue = "ASC") String order


    ){
        List<MovieDTO> movies = this.movieService.getByFilters(title,genre,creationDate,order);
        return ResponseEntity.ok(movies);
    }
}