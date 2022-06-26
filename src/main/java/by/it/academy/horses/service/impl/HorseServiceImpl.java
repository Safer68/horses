package by.it.academy.horses.service.impl;

import by.it.academy.horses.repository.Horse;
import by.it.academy.horses.repository.HorseRepository;
import by.it.academy.horses.service.HorseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HorseServiceImpl implements HorseService {
    @Autowired
    private HorseRepository horseRepository;

    @Override
    public Iterable<Horse> findAll() {

        return horseRepository.findAll();
    }

    public static void main(String[] args) {
        System.out.println(new HorseServiceImpl().findAll());
    }
}
