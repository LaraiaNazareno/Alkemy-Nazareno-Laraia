package com.alkemychallenge.alkemynazarenolaraia.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MovieDTO {


    private Long id;
    private String image;
    private String title;
    private String creationDate;
    private Long rating;
    private Long genreid;

    private List<CharacterDTO> characters;



}
