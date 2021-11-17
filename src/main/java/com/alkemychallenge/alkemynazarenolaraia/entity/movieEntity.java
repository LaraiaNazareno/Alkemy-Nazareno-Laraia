package com.alkemychallenge.alkemynazarenolaraia.entity;

import javax.persistence.*;
import lombok.Getter;
import  lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movie")
@Getter
@Setter

public class movieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private Long id;

    private String image;

    private String title;

    @Column(name = "creation_date")
    @DateTimeFormat(pattern = "yyyy/mm/dd")
    private LocalDate creationDate;

    private Long rating;



    @ManyToOne (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_id",insertable = false, updatable = false)
    private genreEntity genre;

    @Column(name = "genre_id", nullable = false)
    private Long genreId;


    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "movie_character",
            joinColumns = @JoinColumn (name = "movie_id"),
            inverseJoinColumns =@JoinColumn(name = "character_id")

    )
    private Set<characterEntity> characters = new HashSet<>();




}
