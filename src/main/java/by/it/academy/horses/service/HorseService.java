package by.it.academy.horses.service;

import by.it.academy.horses.service.dto.HorseDto;

import java.util.List;

public interface HorseService {

    List<HorseDto> findAll();

    HorseDto findById(Long horseId);

    void save(HorseDto horseDto);

    void deleteById(Long horseId);

}
