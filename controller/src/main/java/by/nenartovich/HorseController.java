package by.nenartovich;


import by.nenartovich.dto.HorseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HorseController {

    private static final String HORSES = "horses";
    private static final String HORSE = "horse";
    private static final String HORSE_LIST = "horse-list";
    private static final String HORSE_CREATE = "horse-create";
    private static final String REDIRECT = "redirect:/";
    private static final String ID = "id";
    private static final String HORSE_UPDATE = "horse-update";
    private final HorseService horseService;

    public HorseController(HorseService horseService) {
        this.horseService = horseService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute(HORSES, horseService.findAll());
        return HORSE_LIST;
    }

    @GetMapping("/horse-create")
    public String newPerson(@ModelAttribute(HORSE) HorseDto horseDto) {
        return HORSE_CREATE;
    }

    @PostMapping()
    public String create(@ModelAttribute(HORSE) @Valid HorseDto horseDto,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return HORSE_CREATE;
        horseService.save(horseDto);
        return REDIRECT;
    }

    @GetMapping("/{id}")
    public String edit(Model model, @PathVariable(ID) long id) {
        HorseDto horseDto = horseService.findById(id);
        model.addAttribute(HORSE, horseDto);
        return HORSE_UPDATE;
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute(HORSE) @Valid HorseDto horseDto,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return HORSE_UPDATE;
        horseService.save(horseDto);
        return REDIRECT;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable(ID) long id) {
        horseService.deleteById(id);
        return REDIRECT;
    }
}