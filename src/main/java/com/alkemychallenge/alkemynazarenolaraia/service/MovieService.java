package com.alkemychallenge.alkemynazarenolaraia.service;

import com.alkemychallenge.alkemynazarenolaraia.dto.CharacterBasicDTO;
import com.alkemychallenge.alkemynazarenolaraia.dto.CharacterDTO;
import com.alkemychallenge.alkemynazarenolaraia.dto.MovieBasicDTO;
import com.alkemychallenge.alkemynazarenolaraia.dto.MovieDTO;
import com.alkemychallenge.alkemynazarenolaraia.entity.Movie;

import java.util.List;
import java.util.Set;

public interface MovieService {

    MovieDTO save (MovieDTO dto);


    List<MovieBasicDTO> getBasicALL();

    void delete(Long id);


    MovieDTO getDetailsById(Long id);


    void addCharacter(Long id, Long idCharacter);

    void removeCharacter(Long id, Long idCharacter);


    MovieDTO update (Long id, MovieDTO movieDTO);

    List<MovieDTO> getByFilters(String title, Long genre, String creationDate, String order);
}

