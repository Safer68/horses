package by.it.academy.horses.controller;

import by.it.academy.horses.repository.Horse;
import by.it.academy.horses.repository.HorseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HorseController {
    @Autowired
    private HorseRepository horseRepository;

    @GetMapping()
    public String index(Model model) {
        List<Horse> horseList = (List<Horse>) horseRepository.findAll();
        /*horseList.forEach(Horses::getId);*/
        model.addAttribute("horses", horseList);
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
    public String create(@ModelAttribute("horse") Horse horse) {
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
    public String update(@ModelAttribute("horse")  Horse horse) {
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