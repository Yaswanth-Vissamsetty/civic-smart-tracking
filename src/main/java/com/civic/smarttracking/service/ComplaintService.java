package com.civic.smarttracking.service;

import com.civic.smarttracking.entity.Complaint;
import com.civic.smarttracking.entity.User;
import com.civic.smarttracking.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private UserService userService;

    public Complaint registerComplaint(Complaint complaint, User user) {
        // Auto-generate Complaint Code (e.g., CIVIC-1005)
        long count = complaintRepository.count() + 1001;
        String complaintCode = "CIVIC-" + count;
        
        // Ensure code uniqueness
        while (complaintRepository.findByComplaintCode(complaintCode).isPresent()) {
            count++;
            complaintCode = "CIVIC-" + count;
        }

        complaint.setComplaintCode(complaintCode);
        if (complaint.getDate() == null || complaint.getDate().isEmpty()) {
            complaint.setDate(LocalDate.now().toString());
        }
        complaint.setStatus("Submitted");
        complaint.setRemarks("Complaint registered successfully. Assigned to civic department.");
        complaint.setUser(user);

        return complaintRepository.save(complaint);
    }

    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAllByOrderByIdDesc();
    }

    public List<Complaint> getComplaintsByEmail(String email) {
        return complaintRepository.findByEmailOrderByIdDesc(email);
    }

    public Complaint getComplaintByCode(String complaintCode) {
        return complaintRepository.findByComplaintCode(complaintCode.trim().toUpperCase())
                .orElseThrow(() -> new RuntimeException("No complaint found with Code: " + complaintCode));
    }

    public Complaint getComplaintById(Long id) {
        return complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No complaint found with ID: " + id));
    }

    public List<Complaint> searchComplaints(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllComplaints();
        }
        return complaintRepository.searchComplaints(keyword.trim());
    }

    public Complaint updateComplaintStatus(Long id, String newStatus, String remarks) {
        Complaint complaint = getComplaintById(id);
        if (newStatus != null && !newStatus.trim().isEmpty()) {
            complaint.setStatus(newStatus);
        }
        if (remarks != null && !remarks.trim().isEmpty()) {
            complaint.setRemarks(remarks);
        } else {
            if ("In Progress".equalsIgnoreCase(newStatus)) {
                complaint.setRemarks("Field officer assigned. Resolution work in progress.");
            } else if ("Resolved".equalsIgnoreCase(newStatus)) {
                complaint.setRemarks("Issue resolved satisfactorily by municipal authorities.");
            }
        }
        return complaintRepository.save(complaint);
    }

    public void deleteComplaint(Long id) {
        if (!complaintRepository.existsById(id)) {
            throw new RuntimeException("Cannot delete. Complaint not found with ID: " + id);
        }
        complaintRepository.deleteById(id);
    }

    public Map<String, Object> getSystemDashboardStats() {
        Map<String, Object> stats = new HashMap<>();
        long total = complaintRepository.count();
        long submitted = complaintRepository.countByStatus("Submitted");
        long inProgress = complaintRepository.countByStatus("In Progress");
        long resolved = complaintRepository.countByStatus("Resolved");
        long pending = submitted + inProgress;
        long totalUsers = userService.getTotalCitizensCount();

        stats.put("totalComplaints", total);
        stats.put("submittedComplaints", submitted);
        stats.put("inProgressComplaints", inProgress);
        stats.put("pendingComplaints", pending);
        stats.put("resolvedComplaints", resolved);
        stats.put("totalCitizens", totalUsers);

        return stats;
    }

    public Map<String, Object> getCitizenDashboardStats(String email) {
        Map<String, Object> stats = new HashMap<>();
        long total = complaintRepository.findByEmailOrderByIdDesc(email).size();
        long submitted = complaintRepository.countByEmailAndStatus(email, "Submitted");
        long inProgress = complaintRepository.countByEmailAndStatus(email, "In Progress");
        long pending = submitted + inProgress;
        long resolved = complaintRepository.countByEmailAndStatus(email, "Resolved");

        stats.put("totalComplaints", total);
        stats.put("pendingComplaints", pending);
        stats.put("resolvedComplaints", resolved);

        return stats;
    }
}
