package by.it.academy.horses.controller;

import by.it.academy.horses.accessingdatajpa.Horse;
import by.it.academy.horses.accessingdatajpa.HorseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GreetingController {
   @Autowired
    private HorseRepository horseRepository;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("horses", horseRepository.findAll());
        return "index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        Horse  horse = horseRepository.findById(id).get();
        model.addAttribute("horse", horse);

        return "show";
    }

    @GetMapping("/horse/new")
    public String newPerson(@ModelAttribute("horse")Horse horse) {
        return "horse/new";
    }

   /*@PostMapping()
    public String create(@ModelAttribute("person")) {
        *//*if (bindingResult.hasErrors())
            return "horse/new";*//*

        *//*personDAO.save(person);*//*
        return "redirect:/horse";
    }*/

   /*   @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.show(id));
        return "horse/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "horse/edit";

        personDAO.update(id, person);
        return "redirect:/horse";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/horse";
    }
}*/
}