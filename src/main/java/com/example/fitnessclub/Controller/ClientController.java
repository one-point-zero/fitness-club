package com.example.fitnessclub.Controller;

import com.example.fitnessclub.Entity.Client;
import com.example.fitnessclub.Service.ClientService;
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
public class ClientController {

    @Autowired
    private ClientService clientService;

    @RequestMapping("/clients")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String index(Model model, @Param("keyword") String keyword) {
        List<Client> listClients = clientService.getAllClients(keyword);
        model.addAttribute("clients", listClients);
        model.addAttribute("keyword", keyword);
        Integer maxAge = clientService.getMaxAge();
        model.addAttribute("maxAge", maxAge);
        Integer minAge = clientService.getMinAge();
        model.addAttribute("minAge", minAge);
        String popularMembershipType = clientService.mostPopularMembershipType();
        model.addAttribute("popularMembershipType", popularMembershipType);
        String infrequentMembershipType = clientService.leastPopularMembershipType();
        if (Objects.equals(popularMembershipType, infrequentMembershipType)) {
            model.addAttribute("infrequentMembershipType", "Отсутствует");
        }
        else {
            model.addAttribute("infrequentMembershipType", infrequentMembershipType);
        }
        return "clients";
    }


    @RequestMapping("/clients/new")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String newClient(Model model) {
        model.addAttribute("client", new Client());
        return "new_client";
    }

    @PostMapping(value = "/clients/save")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String saveClient(@ModelAttribute("client") Client client) {
        clientService.saveClient(client);
        return "redirect:/clients";
    }

    @RequestMapping("/clients/edit/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ModelAndView editClient(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit_client");
        Client client = clientService.getClientById(id);
        modelAndView.addObject("client", client);
        return modelAndView;
    }

    @RequestMapping("/clients/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteClient(@PathVariable Long id) {
        clientService.deleteRelated(id);
        clientService.deleteClient(id);
        return "redirect:/clients";
    }


}
