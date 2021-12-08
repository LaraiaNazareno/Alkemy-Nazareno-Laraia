package com.alkemychallenge.alkemynazarenolaraia.service.ServiceImpl;


import com.alkemychallenge.alkemynazarenolaraia.dto.CharacterBasicDTO;
import com.alkemychallenge.alkemynazarenolaraia.dto.CharacterDTO;
import com.alkemychallenge.alkemynazarenolaraia.dto.CharacterFilterDTO;
import com.alkemychallenge.alkemynazarenolaraia.entity.Character;
import com.alkemychallenge.alkemynazarenolaraia.exeption.ParamNotFound;
import com.alkemychallenge.alkemynazarenolaraia.mapper.CharacterMapper;
import com.alkemychallenge.alkemynazarenolaraia.repository.CharacterRepository;
import com.alkemychallenge.alkemynazarenolaraia.repository.specification.CharacterSpecification;
import com.alkemychallenge.alkemynazarenolaraia.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CharacterServiceImpl implements CharacterService {
    @Autowired
    private CharacterMapper characterMapper;
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private CharacterSpecification characterSpecification;

    @Override
    public CharacterDTO save(CharacterDTO dto){

        Character entity = characterMapper.characterDTO2Entity(dto);
        Character entitySaved = characterRepository.save(entity);
        CharacterDTO result = characterMapper.characterEntity2DTO(entitySaved,false);
        return result;


    }


    @Override
    public List<CharacterBasicDTO> getBasicALL(){
        List<Character> entities = this.characterRepository.findAll();
        List<CharacterBasicDTO> characterBasicDTOS = this.characterMapper.characterEntitySet2BasicDTOList(entities);

        return characterBasicDTOS;
    }

    @Override
    public CharacterDTO update(Long id, CharacterDTO characterDTO) {
        Optional<Character> entity = this.characterRepository.findById(id);

        if (!entity.isPresent()) {
            throw new ParamNotFound("Id icono no valido");
        }
        this.characterMapper.characterEntityRefreshValues(entity.get(), characterDTO);
        Character entitySaved = this.characterRepository.save(entity.get());
        CharacterDTO result = this.characterMapper.characterEntity2DTO(entitySaved, false);
        return result;
    }

    @Override
    public CharacterDTO getDetailsById(Long id){
        Optional <Character> entity = this.characterRepository.findById(id);

        if (!entity.isPresent()){
            throw new ParamNotFound("Id icono no valido");
        }

        CharacterDTO characterDTO = this.characterMapper.characterEntity2DTO(entity.get(),true);
        return characterDTO;
    }

    @Override
    public void delete(Long id) {

        this.characterRepository.deleteById(id);
    }

    @Override
    public List<CharacterDTO> getByFilters(String name, Long age, Long weight,Set<Long> movies) {
        CharacterFilterDTO filtersDTO = new CharacterFilterDTO(name, age, weight, movies);

        List<Character> entities = this.characterRepository.findAll(
                this.characterSpecification.getByFilters(filtersDTO)
        );

        List<CharacterDTO> dtos = this.characterMapper.characterEntitySet2DTOList(entities, true);
        return dtos;
    }


    public Character getEntityById(Long id) {
        return this.characterRepository.getById(id);
    }


}
