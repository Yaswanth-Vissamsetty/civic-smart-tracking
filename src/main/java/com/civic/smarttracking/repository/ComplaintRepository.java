package com.civic.smarttracking.repository;

import com.civic.smarttracking.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    Optional<Complaint> findByComplaintCode(String complaintCode);

    List<Complaint> findByEmailOrderByIdDesc(String email);

    List<Complaint> findAllByOrderByIdDesc();

    long countByStatus(String status);

    long countByEmail(String email);

    long countByEmailAndStatus(String email, String status);

    @Query("SELECT c FROM Complaint c WHERE " +
           "LOWER(c.complaintCode) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(c.citizenName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(c.email) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(c.category) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(c.location) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(c.status) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "ORDER BY c.id DESC")
    List<Complaint> searchComplaints(@Param("keyword") String keyword);
}
