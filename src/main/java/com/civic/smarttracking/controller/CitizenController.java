package com.civic.smarttracking.controller;

import com.civic.smarttracking.entity.Complaint;
import com.civic.smarttracking.entity.User;
import com.civic.smarttracking.service.ComplaintService;
import com.civic.smarttracking.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
public class CitizenController {

    @Autowired
    private UserService userService;

    @Autowired
    private ComplaintService complaintService;

    // --- REGISTER ---
    @GetMapping("/register")
    public String showRegisterForm(Model model, HttpSession session) {
        if (session.getAttribute("loggedInUser") != null) {
            return "redirect:/citizen/dashboard";
        }
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        try {
            userService.registerUser(user);
            redirectAttributes.addFlashAttribute("successMessage", "Registration Successful! You can now log in.");
            return "redirect:/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/register";
        }
    }

    // --- LOGIN & LOGOUT ---
    @GetMapping("/login")
    public String showLoginForm(Model model, HttpSession session) {
        if (session.getAttribute("loggedInUser") != null) {
            return "redirect:/citizen/dashboard";
        }
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("email") String email,
                               @RequestParam("password") String password,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {
        try {
            User user = userService.loginUser(email, password);
            session.setAttribute("loggedInUser", user);
            session.setAttribute("userRole", "CITIZEN");
            redirectAttributes.addFlashAttribute("successMessage", "Welcome back, " + user.getName() + "!");
            return "redirect:/citizen/dashboard";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("successMessage", "You have been logged out successfully.");
        return "redirect:/login";
    }

    // --- CITIZEN DASHBOARD ---
    @GetMapping("/citizen/dashboard")
    public String citizenDashboard(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please log in to access your dashboard.");
            return "redirect:/login";
        }
        
        // Refresh User Data from DB
        User currentUser = userService.getUserById(user.getId());
        session.setAttribute("loggedInUser", currentUser);

        List<Complaint> myComplaints = complaintService.getComplaintsByEmail(currentUser.getEmail());
        model.addAttribute("user", currentUser);
        model.addAttribute("complaints", myComplaints);
        model.addAttribute("stats", complaintService.getCitizenDashboardStats(currentUser.getEmail()));
        return "citizen-dashboard";
    }

    // --- REGISTER COMPLAINT ---
    @GetMapping("/citizen/register-complaint")
    public String showComplaintForm(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please log in to lodge a complaint.");
            return "redirect:/login";
        }
        
        User currentUser = userService.getUserById(user.getId());
        Complaint complaint = new Complaint();
        complaint.setCitizenName(currentUser.getName());
        complaint.setEmail(currentUser.getEmail());
        complaint.setMobile(currentUser.getMobile());
        complaint.setDate(LocalDate.now().toString());

        model.addAttribute("user", currentUser);
        model.addAttribute("complaint", complaint);
        return "register-complaint";
    }

    @PostMapping("/citizen/register-complaint")
    public String processComplaintRegistration(@ModelAttribute("complaint") Complaint complaint,
                                               HttpSession session,
                                               RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }

        try {
            User currentUser = userService.getUserById(user.getId());
            Complaint saved = complaintService.registerComplaint(complaint, currentUser);
            redirectAttributes.addFlashAttribute("successMessage", 
                    "Complaint submitted successfully! Your Complaint ID is: " + saved.getComplaintCode());
            return "redirect:/citizen/my-complaints";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error submitting complaint: " + e.getMessage());
            return "redirect:/citizen/register-complaint";
        }
    }

    // --- VIEW MY COMPLAINTS ---
    @GetMapping("/citizen/my-complaints")
    public String viewMyComplaints(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please log in to view your complaints.");
            return "redirect:/login";
        }

        User currentUser = userService.getUserById(user.getId());
        List<Complaint> myComplaints = complaintService.getComplaintsByEmail(currentUser.getEmail());
        model.addAttribute("user", currentUser);
        model.addAttribute("complaints", myComplaints);
        return "my-complaints";
    }

    // --- EDIT PROFILE ---
    @GetMapping("/citizen/edit-profile")
    public String showEditProfileForm(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please log in first.");
            return "redirect:/login";
        }

        User currentUser = userService.getUserById(user.getId());
        model.addAttribute("user", currentUser);
        return "edit-profile";
    }

    @PostMapping("/citizen/edit-profile")
    public String processEditProfile(@RequestParam("name") String name,
                                     @RequestParam("mobile") String mobile,
                                     @RequestParam(name = "password", required = false) String password,
                                     HttpSession session,
                                     RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }

        try {
            User updatedUser = userService.updateProfile(user.getId(), name, mobile, password);
            session.setAttribute("loggedInUser", updatedUser);
            redirectAttributes.addFlashAttribute("successMessage", "Profile updated successfully!");
            return "redirect:/citizen/dashboard";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error updating profile: " + e.getMessage());
            return "redirect:/citizen/edit-profile";
        }
    }
}
