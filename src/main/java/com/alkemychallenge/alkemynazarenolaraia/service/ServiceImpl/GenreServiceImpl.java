package com.alkemychallenge.alkemynazarenolaraia.service.ServiceImpl;


import com.alkemychallenge.alkemynazarenolaraia.dto.GenreDTO;
import com.alkemychallenge.alkemynazarenolaraia.entity.Genre;
import com.alkemychallenge.alkemynazarenolaraia.mapper.GenreMapper;
import com.alkemychallenge.alkemynazarenolaraia.repository.GenreRepository;
import com.alkemychallenge.alkemynazarenolaraia.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl implements GenreService {
    @Autowired
    private GenreMapper genreMapper;
    @Autowired
    private GenreRepository genreRepository;

    @Override
    public GenreDTO save(GenreDTO dto){
        Genre entity = genreMapper.genreDTO2Entity(dto);
        Genre entitySaved = genreRepository.save(entity);
        GenreDTO result = genreMapper.genreEntity2DTO(entitySaved);
        return result;


    }



}
