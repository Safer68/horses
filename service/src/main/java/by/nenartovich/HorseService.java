package by.nenartovich;


import by.nenartovich.dto.HorseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface HorseService {

    List<HorseDto> findAll();

    HorseDto findById(Long horseId);

    void save(HorseDto horseDto);

    void deleteById(Long horseId);
    Page<HorseDto> findPaginated(Pageable pageable);

}
