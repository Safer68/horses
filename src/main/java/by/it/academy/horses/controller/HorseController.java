package by.it.academy.horses.controller;

import by.it.academy.horses.repository.Horse;
import by.it.academy.horses.repository.HorseRepository;
import by.it.academy.horses.service.HorseService;
import by.it.academy.horses.service.dto.HorseDto;
import by.it.academy.horses.service.mappers.HorseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HorseController {
    @Autowired
    private HorseRepository horseRepository;

    private final HorseService horseService;

    private final HorseMapper horseMapper;


    public HorseController(HorseService horseService, HorseMapper horseMapper) {
        this.horseService = horseService;
        this.horseMapper = horseMapper;
    }

    @GetMapping()
    public String index(Model model) {
        List<HorseDto> horses = ((List<Horse>) horseService.findAll()).stream()
                .map(horseMapper::toHorseDto)
                .collect(Collectors.toList());


        model.addAttribute("horses", horses);
        return "index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        Horse horse = horseRepository.findById(id).orElse(null);
        /*HorseDto horseDto = HorseMapper.INSTANCE.toDTO(horse);
        System.out.println(horseDto);*/
        model.addAttribute("horse", horse);
        return "show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("horse") Horse horse) {
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("horse") @Valid Horse horse,
                         BindingResult bindingResult) {
        System.out.println(bindingResult.hasErrors());
        if (bindingResult.hasErrors())
            return "new";
        horseRepository.save(horse);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        Horse horse = horseRepository.findById(id).orElse(null);
        model.addAttribute("horse", horse);
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("horse") Horse horse) {
        horseRepository.save(horse);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        Horse horse = horseRepository.findById(id).orElse(null);
        horseRepository.delete(horse);
        return "redirect:/";
    }
}