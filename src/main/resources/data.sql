-- Seed Data for Civic Smart Tracking System

-- Default Admin Account
INSERT INTO users (id, name, email, password, mobile, role) 
SELECT 1, 'Municipal Admin', 'admin@civic.gov.in', 'admin123', '9876543210', 'ADMIN'
WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = 'admin@civic.gov.in');

-- Default Citizen Account
INSERT INTO users (id, name, email, password, mobile, role) 
SELECT 2, 'Rahul Sharma', 'rahul@gmail.com', 'citizen123', '9811223344', 'CITIZEN'
WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = 'rahul@gmail.com');

INSERT INTO users (id, name, email, password, mobile, role) 
SELECT 3, 'Priya Patel', 'priya@gmail.com', 'citizen123', '9822334455', 'CITIZEN'
WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = 'priya@gmail.com');

-- Sample Complaints
INSERT INTO complaints (id, complaint_code, citizen_name, email, mobile, category, description, location, date, status, remarks, user_id)
SELECT 1, 'CIVIC-1001', 'Rahul Sharma', 'rahul@gmail.com', '9811223344', 'Water Leakage', 'Main pipeline leaking near Sector 4 community center entrance.', 'Sector 4, Main Road', '2026-08-01', 'In Progress', 'Maintenance team dispatched to inspect pipeline.', 2
WHERE NOT EXISTS (SELECT 1 FROM complaints WHERE complaint_code = 'CIVIC-1001');

INSERT INTO complaints (id, complaint_code, citizen_name, email, mobile, category, description, location, date, status, remarks, user_id)
SELECT 2, 'CIVIC-1002', 'Rahul Sharma', 'rahul@gmail.com', '9811223344', 'Street Light Issue', 'Three consecutive street lights are out near Block B residential park.', 'Block B, Green Park', '2026-08-03', 'Submitted', 'Complaint registered and assigned to Electrical Department.', 2
WHERE NOT EXISTS (SELECT 1 FROM complaints WHERE complaint_code = 'CIVIC-1002');

INSERT INTO complaints (id, complaint_code, citizen_name, email, mobile, category, description, location, date, status, remarks, user_id)
SELECT 3, 'CIVIC-1003', 'Priya Patel', 'priya@gmail.com', '9822334455', 'Garbage Collection', 'Garbage bin overflowing for the past two days near Market Square.', 'Market Square, Lane 2', '2026-07-28', 'Resolved', 'Waste management team cleared the bin and disinfected area.', 3
WHERE NOT EXISTS (SELECT 1 FROM complaints WHERE complaint_code = 'CIVIC-1003');

INSERT INTO complaints (id, complaint_code, citizen_name, email, mobile, category, description, location, date, status, remarks, user_id)
SELECT 4, 'CIVIC-1004', 'Priya Patel', 'priya@gmail.com', '9822334455', 'Drainage Problem', 'Severe blockage in stormwater drain causing waterlogging during rain.', 'Civil Lines, Near Gate 3', '2026-08-05', 'Submitted', 'Complaint queued for Sanitation Department.', 3
WHERE NOT EXISTS (SELECT 1 FROM complaints WHERE complaint_code = 'CIVIC-1004');
