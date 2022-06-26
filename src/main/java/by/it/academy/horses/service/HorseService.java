package by.it.academy.horses.service;

import by.it.academy.horses.repository.Horse;
import by.it.academy.horses.service.dto.HorseDto;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
public interface HorseService {

    Iterable<Horse> findAll();
}
