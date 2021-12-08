package com.alkemychallenge.alkemynazarenolaraia.mapper;

import com.alkemychallenge.alkemynazarenolaraia.dto.GenreDTO;
import com.alkemychallenge.alkemynazarenolaraia.entity.Genre;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper {

    public Genre genreDTO2Entity (GenreDTO dto){
        Genre genre = new Genre();

        genre.setName(dto.getName());
        genre.setImage(dto.getImage());

        return genre;


    }

    public GenreDTO genreEntity2DTO(Genre entity){
        GenreDTO dto = new GenreDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setImage(entity.getImage());

        return dto;


    }


}
