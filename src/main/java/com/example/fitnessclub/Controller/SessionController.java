package com.example.fitnessclub.Controller;

import com.example.fitnessclub.Entity.Session;
import com.example.fitnessclub.Service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

@Controller
public class SessionController {

    @Autowired
    SessionService sessionService;

    @RequestMapping("/info")
    public String index(Model model, @Param("keyword") String keyword) {
        List<Session> listSessions = sessionService.getAllSessions(keyword);
        model.addAttribute("sessions", listSessions);
        model.addAttribute("keyword", keyword);
        String popularZone = sessionService.mostPopularZone();
        model.addAttribute("popularZone", popularZone);
        String infrequentZone = sessionService.leastPopularZone();
        if (Objects.equals(popularZone, infrequentZone)) {
            model.addAttribute("infrequentZone", "Отсутствует");
        }
        else {
            model.addAttribute("infrequentZone", infrequentZone);
        }
        String popularMembershipType = sessionService.mostPopularMembershipType();
        model.addAttribute("popularMembershipType", popularMembershipType);
        String infrequentMembershipType = sessionService.leastPopularMembershipType();
        if (Objects.equals(popularMembershipType, infrequentMembershipType)) {
            model.addAttribute("infrequentMembershipType", "Отсутствует");
        }
        else {
            model.addAttribute("infrequentMembershipType", infrequentMembershipType);
        }
        return "sessions";
    }


    @RequestMapping("/info/new")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String newSession(Model model) {
        model.addAttribute("session_info", new Session());
        return "new_session";
    }

    @PostMapping(value = "/info/save")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String saveSession(@ModelAttribute("session") Session session) {
        sessionService.saveSession(session);
        return "redirect:/info";
    }

    @RequestMapping("/info/edit/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ModelAndView editSession(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit_session");
        Session session = sessionService.getSessionById(id);
        modelAndView.addObject("session_info", session);
        return modelAndView;
    }

    @RequestMapping("/info/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteSessions(@PathVariable Long id) {
        sessionService.deleteRelated(id);
        sessionService.deleteSession(id);
        return "redirect:/info";
    }

}
