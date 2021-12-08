package com.alkemychallenge.alkemynazarenolaraia.service.ServiceImpl;

import com.alkemychallenge.alkemynazarenolaraia.dto.*;
import com.alkemychallenge.alkemynazarenolaraia.entity.Character;
import com.alkemychallenge.alkemynazarenolaraia.entity.Movie;
import com.alkemychallenge.alkemynazarenolaraia.exeption.ParamNotFound;
import com.alkemychallenge.alkemynazarenolaraia.mapper.MovieMapper;
import com.alkemychallenge.alkemynazarenolaraia.repository.CharacterRepository;
import com.alkemychallenge.alkemynazarenolaraia.repository.MovieRepository;
import com.alkemychallenge.alkemynazarenolaraia.repository.specification.MovieSpecification;
import com.alkemychallenge.alkemynazarenolaraia.service.CharacterService;
import com.alkemychallenge.alkemynazarenolaraia.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private CharacterService characterService;
    @Autowired
    private MovieSpecification movieSpecification;


    @Override
    public MovieDTO save (MovieDTO dto){
        Movie entity = movieMapper.movieDTO2Entity(dto);
        Movie entitySaved = movieRepository.save(entity);
        MovieDTO result = movieMapper.movieEntity2DTO(entitySaved, false);
        return  result;

    }

    @Override
    public void delete(Long id) {

        this.movieRepository.deleteById(id);
    }

    @Override
    public List<MovieBasicDTO> getBasicALL(){
        List<Movie> entities = this.movieRepository.findAll();
        List<MovieBasicDTO> movieBasicDTO = this.movieMapper.movieEntitySet2BasicDTOList(entities);

        return movieBasicDTO;
    }

    @Override
    public MovieDTO getDetailsById(Long id){
        Optional<Movie> entity = this.movieRepository.findById(id);

        if (!entity.isPresent()){
            throw new ParamNotFound("Id movie no valido");
        }

        MovieDTO movieDTO = this.movieMapper.movieEntity2DTO(entity.get(),true);
        return movieDTO;
    }

    public void addCharacter(Long id, Long idCharacter) {
        Movie entity = this.movieRepository.getById(id);
        entity.getCharacters().size();
        Character characterEntity = this.characterService.getEntityById(idCharacter);
        entity.addCharacter(characterEntity);
        this.movieRepository.save(entity);
    }

    public void removeCharacter(Long id, Long idCharacter) {
        Movie entity = this.movieRepository.getById(id);
        entity.getCharacters().size();
        Character paisEntity = this.characterService.getEntityById(idCharacter);
        entity.removeCharacter(paisEntity);
        this.movieRepository.save(entity);
    }

    @Override
    public MovieDTO update(Long id, MovieDTO movieDTO) {
        Optional<Movie> optionalEntity = this.movieRepository.findById(id);

        if (!optionalEntity.isPresent()) {
            throw new ParamNotFound("Id icono no valido");
        }
        this.movieMapper.movieEntityRefreshValues(optionalEntity.get(), movieDTO);
        Movie entitySaved = this.movieRepository.save(optionalEntity.get());
        MovieDTO result = this.movieMapper.movieEntity2DTO(entitySaved, false);
        return result;
    }


    @Override
    public List<MovieDTO> getByFilters(String title, Long genre, String creationDate, String order) {
        MovieFilterDTO filtersDTO = new MovieFilterDTO(title, genre, creationDate, order);

        List<Movie> entities = this.movieRepository.findAll(
                this.movieSpecification.getByFilters(filtersDTO)
        );

        List<MovieDTO> dtos = this.movieMapper.movieEntitySet2DTOList(entities, true);
        return dtos;
    }

}
