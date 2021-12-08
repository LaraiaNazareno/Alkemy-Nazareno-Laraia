package com.alkemychallenge.alkemynazarenolaraia.mapper;

import com.alkemychallenge.alkemynazarenolaraia.dto.CharacterBasicDTO;
import com.alkemychallenge.alkemynazarenolaraia.dto.CharacterDTO;
import com.alkemychallenge.alkemynazarenolaraia.dto.MovieDTO;
import com.alkemychallenge.alkemynazarenolaraia.entity.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CharacterMapper {

    @Autowired
    private MovieMapper movieMapper;


    public Character characterDTO2Entity(CharacterDTO dto){
        Character characters = new Character();

        characters.setName(dto.getName());
        characters.setImage(dto.getImage());
        characters.setAge(dto.getAge());
        characters.setWeight(dto.getWeight());
        characters.setHistory(dto.getHistory());

        return characters;


    }
    public CharacterDTO characterEntity2DTO(Character entity, boolean loadMovies) {
        CharacterDTO dto = new CharacterDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setImage(entity.getImage());
        dto.setAge(entity.getAge());
        dto.setWeight(entity.getWeight());
        dto.setHistory(entity.getHistory());


        if (loadMovies) {
            List<MovieDTO> moviesDTO = this.movieMapper.movieEntityList2DTOList(entity.getMovies(), false);
            dto.setMovies(moviesDTO);
        }
        return dto;
    }

    public Set<Character> characterDTOList2EntityList(List<CharacterDTO> dtos) {
        Set<Character> entities = new HashSet<>();
        for (CharacterDTO dto : dtos) {
            entities.add(this.characterDTO2Entity(dto));
        }
        return entities;
    }

    public List<CharacterDTO> characterEntitySet2DTOList(Collection<Character> entities, boolean loadMovies) {
        List<CharacterDTO> dtos = new ArrayList<>();
        for (Character entity : entities) {
            dtos.add(this.characterEntity2DTO(entity, loadMovies));
        }
        return dtos;
    }


    public List<CharacterBasicDTO> characterEntitySet2BasicDTOList(Collection<Character> entities) {
        List<CharacterBasicDTO> dtos = new ArrayList<>();
        CharacterBasicDTO basicDTO;

        for (Character entity : entities) {
            basicDTO = new CharacterBasicDTO();
            basicDTO.setImage(entity.getImage());
            basicDTO.setName(entity.getName());
            dtos.add(basicDTO);
        }

        return dtos;
    }

    public void characterEntityRefreshValues(Character entity, CharacterDTO characterDTO) {
        entity.setName(characterDTO.getName());
        entity.setImage(characterDTO.getImage());
        entity.setAge(characterDTO.getAge());
        entity.setWeight(characterDTO.getWeight());
        entity.setHistory(characterDTO.getHistory());
    }


}
