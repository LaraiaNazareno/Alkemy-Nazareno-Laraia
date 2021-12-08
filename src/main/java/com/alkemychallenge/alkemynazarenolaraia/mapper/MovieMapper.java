package com.alkemychallenge.alkemynazarenolaraia.mapper;

import com.alkemychallenge.alkemynazarenolaraia.dto.CharacterBasicDTO;
import com.alkemychallenge.alkemynazarenolaraia.dto.CharacterDTO;
import com.alkemychallenge.alkemynazarenolaraia.dto.MovieBasicDTO;
import com.alkemychallenge.alkemynazarenolaraia.dto.MovieDTO;
import com.alkemychallenge.alkemynazarenolaraia.entity.Character;
import com.alkemychallenge.alkemynazarenolaraia.entity.Genre;
import com.alkemychallenge.alkemynazarenolaraia.entity.Movie;
import com.alkemychallenge.alkemynazarenolaraia.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Component
public class MovieMapper {
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private CharacterMapper characterMapper;

    public Movie movieDTO2Entity(MovieDTO dto){
        Genre genre = genreRepository.findById(dto.getGenreid()).orElseThrow();

        Movie movie = new Movie();

        movie.setImage(dto.getImage());
        movie.setTitle(dto.getTitle());
        movie.setCreationDate(this.string2LocalDate(dto.getCreationDate()));
        movie.setRating(dto.getRating());
        movie.setGenre(genre);


        Set<Character> characters = characterMapper.characterDTOList2EntityList(dto.getCharacters());

        movie.setCharacters(characters);

        return movie;

    }
    private LocalDate string2LocalDate(String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(stringDate, formatter);
        return date;
    }

    public MovieDTO movieEntity2DTO (Movie entity, boolean loadCharacter){

        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(entity.getId());
        movieDTO.setGenreid(entity.getGenre().getId());
        movieDTO.setImage(entity.getImage());
        movieDTO.setTitle(entity.getTitle());
        movieDTO.setCreationDate(entity.getCreationDate().toString());
        movieDTO.setRating(entity.getRating());


        if(loadCharacter) {
            List<CharacterDTO> characterDTOS = characterMapper.characterEntitySet2DTOList(entity.getCharacters(), false);
            movieDTO.setCharacters(characterDTOS);
        }

        return movieDTO;



    }

    public List<MovieDTO> movieEntityList2DTOList(List<Movie> entities, boolean loadCharacter){
        List<MovieDTO> dtos = new ArrayList<>();
        for (Movie entity: entities){
            dtos.add(this.movieEntity2DTO(entity,loadCharacter));
        }
        return dtos;
    }

    public List<MovieBasicDTO> movieEntitySet2BasicDTOList(Collection<Movie> entities) {
        List<MovieBasicDTO> dtos = new ArrayList<>();
        MovieBasicDTO basicDTO;

        for (Movie entity : entities) {
            basicDTO = new MovieBasicDTO();
            basicDTO.setImage(entity.getImage());
            basicDTO.setTitle(entity.getTitle());
            basicDTO.setCreationDate(entity.getCreationDate().toString());
            dtos.add(basicDTO);
        }

        return dtos;
    }


    public void movieEntityRefreshValues(Movie entity, MovieDTO movieDTO) {
        Genre genre = this.genreRepository.getById(movieDTO.getGenreid());


        entity.setImage(movieDTO.getImage());
        entity.setTitle(movieDTO.getTitle());
        entity.setRating(movieDTO.getRating());
        entity.setCreationDate(this.string2LocalDate(movieDTO.getCreationDate()));
        entity.setGenre(genre);


    }

    public List<MovieDTO> movieEntitySet2DTOList(Collection<Movie> entities, boolean loadCharacter) {
        List<MovieDTO> dtos = new ArrayList<>();
        for (Movie entity : entities) {
            dtos.add(this.movieEntity2DTO(entity, loadCharacter));
        }
        return dtos;
    }







}
