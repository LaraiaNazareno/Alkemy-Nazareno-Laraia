package com.alkemychallenge.alkemynazarenolaraia.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieFilterDTO {

    private String title;
    private Long genre;
    private String creationDate;
    private String order;

    public MovieFilterDTO(String title, Long genre, String creationDate, String order) {
        this.title = title;
        this.genre = genre;
        this.creationDate = creationDate;
        this.order = order;
    }

    public boolean isASC() {
        return this.order.compareToIgnoreCase("ASC") == 0;
    }

    public boolean isDESC() {
        return this.order.compareToIgnoreCase("DESC") == 0;
    }




}
