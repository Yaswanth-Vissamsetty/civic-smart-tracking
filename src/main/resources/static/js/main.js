/* =========================================================
   Civic Smart Tracking System - JavaScript
   Interactive Utilities, Validation & UI Handlers
   ========================================================= */

document.addEventListener('DOMContentLoaded', function () {

    // --- Auto Dismiss Flash Alerts after 5 Seconds ---
    const alerts = document.querySelectorAll('.alert-dismissible');
    alerts.forEach(function (alert) {
        setTimeout(function () {
            const bsAlert = new bootstrap.Alert(alert);
            bsAlert.close();
        }, 5000);
    });

    // --- Search Filter for Admin Complaints Table ---
    const searchInput = document.getElementById('tableSearchInput');
    if (searchInput) {
        searchInput.addEventListener('keyup', function () {
            const query = this.value.toLowerCase();
            const tableRows = document.querySelectorAll('#complaintsTable tbody tr');

            tableRows.forEach(row => {
                const text = row.textContent.toLowerCase();
                if (text.includes(query)) {
                    row.style.display = '';
                } else {
                    row.style.display = 'none';
                }
            });
        });
    }

    // --- Quick Complaint Track Submission ---
    const quickTrackForm = document.getElementById('quickTrackForm');
    if (quickTrackForm) {
        quickTrackForm.addEventListener('submit', function (e) {
            const codeInput = document.getElementById('quickTrackCode');
            if (!codeInput || !codeInput.value.trim()) {
                e.preventDefault();
                alert('Please enter a valid Complaint ID (e.g. CIVIC-1001)');
            }
        });
    }

    // --- Admin Status Modal Autofill ---
    const updateModal = document.getElementById('updateStatusModal');
    if (updateModal) {
        updateModal.addEventListener('show.bs.modal', function (event) {
            const button = event.relatedTarget;
            const complaintId = button.getAttribute('data-id');
            const code = button.getAttribute('data-code');
            const currentStatus = button.getAttribute('data-status');
            const currentRemarks = button.getAttribute('data-remarks');

            document.getElementById('modalComplaintId').value = complaintId;
            document.getElementById('modalComplaintCodeDisplay').innerText = code;
            document.getElementById('modalStatusSelect').value = currentStatus;
            document.getElementById('modalRemarksInput').value = currentRemarks || '';
        });
    }

    // --- Admin Delete Confirmation Modal Autofill ---
    const deleteModal = document.getElementById('deleteModal');
    if (deleteModal) {
        deleteModal.addEventListener('show.bs.modal', function (event) {
            const button = event.relatedTarget;
            const complaintId = button.getAttribute('data-id');
            const code = button.getAttribute('data-code');

            document.getElementById('deleteModalComplaintId').value = complaintId;
            document.getElementById('deleteModalCodeDisplay').innerText = code;
        });
    }
});
