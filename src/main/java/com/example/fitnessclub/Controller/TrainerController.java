package com.example.fitnessclub.Controller;

import com.example.fitnessclub.Entity.Trainer;
import com.example.fitnessclub.Service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    @RequestMapping("/trainers")
    public String index(Model model, @Param("keyword") String keyword) {
        List<Trainer> listTrainers = trainerService.getAllTrainers(keyword);
        model.addAttribute("trainers", listTrainers);
        model.addAttribute("keyword", keyword);
        Float maxRating = trainerService.getMaxRating();
        model.addAttribute("maxRating", maxRating);
        Float minRating = trainerService.getMinRating();
        model.addAttribute("minRating", minRating);
        return "trainers";
    }


    @RequestMapping("/trainers/new")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String newTrainer(Model model) {
        model.addAttribute("trainer", new Trainer());
        return "new_trainer";
    }

    @PostMapping(value = "/trainers/save")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String saveTrainer(@ModelAttribute("trainer") Trainer trainer) {
        trainerService.saveTrainer(trainer);
        return "redirect:/trainers";
    }

    @RequestMapping("/trainers/edit/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ModelAndView editTrainer(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit_trainer");
        Trainer trainer = trainerService.getTrainerById(id);
        modelAndView.addObject("trainer", trainer);
        return modelAndView;
    }

    @RequestMapping("/trainers/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteTrainer(@PathVariable Long id) {
        trainerService.deleteTrainer(id);
        return "redirect:/trainers";
    }


}
