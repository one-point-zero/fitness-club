package com.example.fitnessclub.Controller;

import com.example.fitnessclub.Entity.*;
import com.example.fitnessclub.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @RequestMapping("/bookings")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String index(Model model, @Param("keyword") String keyword) {
        List<BookingDTO> listBookings = bookingService.getAllBookings(keyword);
        model.addAttribute("bookings", listBookings);
        BookingId b_id = new BookingId();
        model.addAttribute("b_id", b_id);
        model.addAttribute("keyword", keyword);
        return "bookings";
    }

    @RequestMapping("/bookings/new")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String newBooking(Model model) {
        BookingId bookingId = new BookingId();
        model.addAttribute("booking", new Booking());
        return "new_booking";
    }

    @PostMapping(value = "/bookings/save")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String saveBooking(@ModelAttribute("booking") Booking booking) {
        try {
            bookingService.insertBooking(booking.getId().getClient(), booking.getId().getSession());
        }
        catch (Exception e) {
            return "redirect:/bookings";
        }
        return "redirect:/bookings";
    }

    @RequestMapping("/bookings/delete/{id1}/{id2}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteBooking(@ModelAttribute("booking") Booking booking, @PathVariable Long id1, @PathVariable Long id2) {
        bookingService.deleteBooking(id1, id2);
        return "redirect:/bookings";
    }

}
