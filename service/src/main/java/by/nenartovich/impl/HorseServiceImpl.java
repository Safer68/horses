package by.nenartovich.impl;

import by.nenartovich.HorseRepository;
import by.nenartovich.HorseService;
import by.nenartovich.dto.HorseDto;
import by.nenartovich.entity.Horse;
import by.nenartovich.mappers.HorseMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Collections;
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

    @Override
    public Page<HorseDto> findPaginated(Pageable pageable) {
        List<HorseDto> horses = horseRepository.findAll().stream()
                .map(horseMapper::toHorseDto)
                .collect(Collectors.toList());
        List<HorseDto> list;
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        if (horses.size() < startItem){
            list = Collections.emptyList();
        }else {
            int toIndex = Math.min(startItem+pageSize,horses.size());
            list = horses.subList(startItem,toIndex);
        }
            return new PageImpl<HorseDto>(list, PageRequest.of(currentPage,pageSize),horses.size());
    }
}
