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

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private ComplaintService complaintService;

    // --- ADMIN LOGIN & LOGOUT ---
    @GetMapping("/login")
    public String adminLoginForm(HttpSession session) {
        if (session.getAttribute("adminUser") != null) {
            return "redirect:/admin/dashboard";
        }
        return "admin-login";
    }

    @PostMapping("/login")
    public String processAdminLogin(@RequestParam("email") String email,
                                    @RequestParam("password") String password,
                                    HttpSession session,
                                    RedirectAttributes redirectAttributes) {
        try {
            User admin = userService.loginAdmin(email, password);
            session.setAttribute("adminUser", admin);
            session.setAttribute("userRole", "ADMIN");
            redirectAttributes.addFlashAttribute("successMessage", "Welcome, Administrator!");
            return "redirect:/admin/dashboard";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/admin/login";
        }
    }

    @GetMapping("/logout")
    public String adminLogout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.removeAttribute("adminUser");
        session.removeAttribute("userRole");
        redirectAttributes.addFlashAttribute("successMessage", "Admin logged out successfully.");
        return "redirect:/admin/login";
    }

    // --- ADMIN DASHBOARD ---
    @GetMapping("/dashboard")
    public String adminDashboard(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        User admin = (User) session.getAttribute("adminUser");
        if (admin == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Access Denied. Admin login required.");
            return "redirect:/admin/login";
        }

        model.addAttribute("admin", admin);
        model.addAttribute("stats", complaintService.getSystemDashboardStats());
        model.addAttribute("recentComplaints", complaintService.getAllComplaints());
        return "admin-dashboard";
    }

    // --- VIEW ALL COMPLAINTS & SEARCH ---
    @GetMapping("/complaints")
    public String viewAllComplaints(@RequestParam(name = "keyword", required = false) String keyword,
                                    HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        User admin = (User) session.getAttribute("adminUser");
        if (admin == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Admin login required.");
            return "redirect:/admin/login";
        }

        List<Complaint> complaintList;
        if (keyword != null && !keyword.trim().isEmpty()) {
            complaintList = complaintService.searchComplaints(keyword);
            model.addAttribute("keyword", keyword.trim());
        } else {
            complaintList = complaintService.getAllComplaints();
        }

        model.addAttribute("admin", admin);
        model.addAttribute("complaints", complaintList);
        return "admin-complaints";
    }

    // --- UPDATE COMPLAINT STATUS ---
    @PostMapping("/update-status")
    public String updateComplaintStatus(@RequestParam("complaintId") Long complaintId,
                                       @RequestParam("status") String status,
                                       @RequestParam(name = "remarks", required = false) String remarks,
                                       HttpSession session,
                                       RedirectAttributes redirectAttributes) {
        User admin = (User) session.getAttribute("adminUser");
        if (admin == null) {
            return "redirect:/admin/login";
        }

        try {
            Complaint updated = complaintService.updateComplaintStatus(complaintId, status, remarks);
            redirectAttributes.addFlashAttribute("successMessage", 
                    "Status for complaint " + updated.getComplaintCode() + " updated to '" + status + "'.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to update status: " + e.getMessage());
        }
        return "redirect:/admin/complaints";
    }

    // --- DELETE COMPLAINT ---
    @PostMapping("/delete-complaint")
    public String deleteComplaint(@RequestParam("complaintId") Long complaintId,
                                  HttpSession session,
                                  RedirectAttributes redirectAttributes) {
        User admin = (User) session.getAttribute("adminUser");
        if (admin == null) {
            return "redirect:/admin/login";
        }

        try {
            complaintService.deleteComplaint(complaintId);
            redirectAttributes.addFlashAttribute("successMessage", "Complaint deleted successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete complaint: " + e.getMessage());
        }
        return "redirect:/admin/complaints";
    }

    // --- VIEW REGISTERED USERS ---
    @GetMapping("/users")
    public String viewRegisteredUsers(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        User admin = (User) session.getAttribute("adminUser");
        if (admin == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Admin login required.");
            return "redirect:/admin/login";
        }

        model.addAttribute("admin", admin);
        model.addAttribute("users", userService.getAllCitizens());
        return "admin-users";
    }
}
