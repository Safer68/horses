package by.nenartovich;


import by.nenartovich.dto.HorseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    public String listHorse(Model model,
                            @RequestParam("page") Optional<Integer> page,
                            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<HorseDto> horsePage = horseService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("horsePage", horsePage);

        int totalPages = horsePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
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