-- JOB DATA
INSERT INTO jobs (title, description) VALUES
                                              ( 'Java Developer', 'Looking for an experienced Java developer familiar with Spring Boot and Microservices.'),
                                              ( 'Data Analyst', 'Analyze and visualize data trends using SQL, Power BI, and Python.'),
                                              ( 'Frontend Engineer', 'Build responsive UIs using React, HTML, CSS, and TypeScript.'),
                                              ( 'AI Engineer', 'Develop and fine-tune ML models for NLP-based resume parsing.');

-- SAMPLE RESUMES (optional if you already have resumes table)
INSERT INTO resumes(candidate_name, job_id, ai_score, ai_summary, file_path) VALUES
                                                                                     ( 'John Doe', 1, 82.5, 'Strong Java and Spring Boot experience with 5 years in microservices.', '/uploads/john_doe.pdf'),
                                                                                     ( 'Jane Smith', 2, 74.0, 'Good SQL and Power BI background, lacks deep Python experience.', '/uploads/jane_smith.pdf'),
                                                                                     ( 'Kevin Lee', 3, 91.0, 'Excellent React skills, deep frontend experience.', '/uploads/kevin_lee.pdf'),
                                                                                     ('Priya Patel', 4, 88.0, 'Solid NLP experience, familiar with TensorFlow and Hugging Face models.', '/uploads/priya_patel.pdf');

INSERT INTO users (username, password, role) VALUES
                                                     ('admin', '{noop}admin123', 'ADMIN'),
                                                     ('hr', '{noop}hr123', 'HR');

