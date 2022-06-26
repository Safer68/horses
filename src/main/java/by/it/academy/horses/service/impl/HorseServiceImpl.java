package by.it.academy.horses.service.impl;

import by.it.academy.horses.repository.Horse;
import by.it.academy.horses.repository.HorseRepository;
import by.it.academy.horses.service.HorseService;
import by.it.academy.horses.service.dto.HorseDto;
import by.it.academy.horses.service.mappers.HorseMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class HorseServiceImpl implements HorseService {

    private final HorseRepository horseRepository;
    private final HorseMapper horseMapper;

    public HorseServiceImpl(HorseRepository horseRepository, HorseMapper horseMapper) {
        this.horseRepository = horseRepository;
        this.horseMapper = horseMapper;
    }

    @Override
    public List<HorseDto> findAll() {
        return StreamSupport
                .stream(horseRepository.findAll().spliterator(), false)
                .map(horseMapper::toHorseDto)
                .collect(Collectors.toList());
    }

    @Override
    public HorseDto findById(Long horseId) {
        Horse horse = horseRepository.findById(horseId).orElse(null);
        return horseMapper.toHorseDto(horse);
    }

    @Override
    public void save(HorseDto horseDto) {
        horseRepository.save(horseMapper.toHorse(horseDto));
    }

    @Override
    public void deleteById(Long horseId) {
        horseRepository.deleteById(horseId);
    }
}
