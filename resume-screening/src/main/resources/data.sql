-- JOB DATA
INSERT INTO jobs (id, title, description) VALUES
                                              (1, 'Java Developer', 'Looking for an experienced Java developer familiar with Spring Boot and Microservices.'),
                                              (2, 'Data Analyst', 'Analyze and visualize data trends using SQL, Power BI, and Python.'),
                                              (3, 'Frontend Engineer', 'Build responsive UIs using React, HTML, CSS, and TypeScript.'),
                                              (4, 'AI Engineer', 'Develop and fine-tune ML models for NLP-based resume parsing.');

-- SAMPLE RESUMES (optional if you already have resumes table)
INSERT INTO resumes(id, candidate_name, job_id, ai_score, ai_summary, file_path) VALUES
                                                                                     (1, 'John Doe', 1, 82.5, 'Strong Java and Spring Boot experience with 5 years in microservices.', '/uploads/john_doe.pdf'),
                                                                                     (2, 'Jane Smith', 2, 74.0, 'Good SQL and Power BI background, lacks deep Python experience.', '/uploads/jane_smith.pdf'),
                                                                                     (3, 'Kevin Lee', 3, 91.0, 'Excellent React skills, deep frontend experience.', '/uploads/kevin_lee.pdf'),
                                                                                     (4, 'Priya Patel', 4, 88.0, 'Solid NLP experience, familiar with TensorFlow and Hugging Face models.', '/uploads/priya_patel.pdf');

INSERT INTO users (id, username, password, role) VALUES
                                                     (1, 'admin', '{noop}admin123', 'ADMIN'),
                                                     (2, 'hr', '{noop}hr123', 'HR');

