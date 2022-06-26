package by.it.academy.horses.service.mappers;

import by.it.academy.horses.repository.Horse;
import by.it.academy.horses.service.dto.HorseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class HorseMapper {
    /*@Mapping(target = "id",source = "id")*/
    public abstract HorseDto toHorseDto(Horse horse);
}
