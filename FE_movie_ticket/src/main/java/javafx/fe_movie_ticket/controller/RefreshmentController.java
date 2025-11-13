package javafx.fe_movie_ticket.controller;

import javafx.fe_movie_ticket.entity.Refreshment;
import javafx.fe_movie_ticket.entity.enumeration.Category;
import javafx.fe_movie_ticket.service.RefreshmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/refreshments")
@RequiredArgsConstructor
public class RefreshmentController {

    private final RefreshmentService refreshmentService;

    // --- List all refreshments ---
    @GetMapping("/list")
    public String listRefreshments(Model model, @RequestParam(required = false) String category) {
        if (category != null && !category.isEmpty()) {
            model.addAttribute("refreshments", refreshmentService.getByCategory(Category.valueOf(category)));
        } else {
            model.addAttribute("refreshments", refreshmentService.getAll());
        }
        return "refreshment_list"; // thymeleaf template
    }

    // --- Show create form ---
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("refreshment", new Refreshment());
        model.addAttribute("categories", Category.values());
        return "refreshment_create"; // thymeleaf template
    }

    // --- Create new refreshment ---
    @PostMapping("/create")
    public String createRefreshment(@ModelAttribute Refreshment refreshment) {
        refreshmentService.create(refreshment);
        return "redirect:/refreshments/list";
    }

    // --- Update refreshment ---
    @GetMapping("/edit")
    public String showEditForm(@RequestParam Long id, Model model) {
        Refreshment refreshment = refreshmentService.getById(id);
        model.addAttribute("refreshment", refreshment);
        model.addAttribute("categories", Category.values());
        return "refreshment_edit"; // thymeleaf template
    }

    @PostMapping("/edit")
    public String editRefreshment(@ModelAttribute Refreshment refreshment) {
        refreshmentService.update(refreshment.getId(), refreshment);
        return "redirect:/refreshments/list";
    }

    // --- Delete refreshment ---
    @PostMapping("/delete")
    public String deleteRefreshment(@RequestParam Long id) {
        refreshmentService.delete(id);
        return "redirect:/refreshments/list";
    }
}
