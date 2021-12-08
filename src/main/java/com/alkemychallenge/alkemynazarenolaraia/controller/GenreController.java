package com.alkemychallenge.alkemynazarenolaraia.controller;


import com.alkemychallenge.alkemynazarenolaraia.dto.GenreDTO;
import com.alkemychallenge.alkemynazarenolaraia.repository.GenreRepository;
import com.alkemychallenge.alkemynazarenolaraia.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/genre")
public class GenreController {
    @Autowired
    private GenreService genreService;


    @PostMapping
    public ResponseEntity<GenreDTO> save(@RequestBody GenreDTO dto){

            GenreDTO genreSaved = genreService.save(dto);

            return ResponseEntity.status(HttpStatus.CREATED).body(genreSaved);
    }



}
