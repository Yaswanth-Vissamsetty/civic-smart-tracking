package com.civic.smarttracking.controller;

import com.civic.smarttracking.service.ComplaintService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private ComplaintService complaintService;

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        model.addAttribute("loggedInUser", session.getAttribute("loggedInUser"));
        model.addAttribute("stats", complaintService.getSystemDashboardStats());
        return "index";
    }

    @GetMapping("/track")
    public String trackComplaintPublic(@RequestParam(name = "code", required = false) String code, 
                                       Model model, HttpSession session) {
        model.addAttribute("loggedInUser", session.getAttribute("loggedInUser"));
        if (code != null && !code.trim().isEmpty()) {
            try {
                model.addAttribute("complaint", complaintService.getComplaintByCode(code.trim()));
                model.addAttribute("searchCode", code.trim());
            } catch (Exception e) {
                model.addAttribute("errorMessage", e.getMessage());
                model.addAttribute("searchCode", code.trim());
            }
        }
        return "track-complaint";
    }
}
