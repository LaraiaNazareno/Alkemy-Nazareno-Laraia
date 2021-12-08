package com.alkemychallenge.alkemynazarenolaraia.entity;

import javax.persistence.*;
import lombok.Getter;
import  lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
//@Table(name = "movie")
@Getter
@Setter

@SQLDelete(sql = "UPDATE movie SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")

public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String image;

    private String title;

    @Column(name = "creation_date")
    @DateTimeFormat(pattern = "yyyy/mm/dd")
    private LocalDate creationDate;

    private Long rating;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "genre_id")
    private Genre genre;



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
    private Set<Character> characters = new HashSet<>();

    private boolean deleted = Boolean.FALSE;

    public void addCharacter(Character character) {
        this.characters.add(character);
    }

    public void removeCharacter(Character character) {
        this.characters.remove(character);
    }


}
