//package com.insy2s.gestionbackend.mockdata;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.Statement;
//
//@Component
//public class FakeData implements CommandLineRunner {
//
//    private final DataSource dataSource;
//
//    public FakeData(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        try (Connection connection = dataSource.getConnection()) {
//            Statement statement = connection.createStatement();
//
//            String sqlStatement =
//                    "INSERT INTO public.skill (skill_name) VALUES\n" +
//                            "    ('Python'),\n" +
//                            "    ('JavaScript'),\n" +
//                            "    ('C++'),\n" +
//                            "    ('C#'),\n" +
//                            "    ('Ruby'),\n" +
//                            "    ('Swift'),\n" +
//                            "    ('TypeScript'),\n" +
//                            "    ('Go'),\n" +
//                            "    ('Rust'),\n" +
//                            "    ('PHP'),\n" +
//                            "    ('Kotlin'),\n" +
//                            "    ('Scala'),\n" +
//                            "    ('Haskell'),\n" +
//                            "    ('Perl'),\n" +
//                            "    ('Objective-C'),\n" +
//                            "    ('Lua'),\n" +
//                            "    ('Dart'),\n" +
//                            "    ('F#'),\n" +
//                            "    ('Groovy'),\n" +
//                            "    ('R'),\n" +
//                            "   \t('Java');\n" +
//                            "\n" +
//                            "INSERT INTO public.team (id_team, is_week_even, \"name\", project_end_date, project_start_date, url_backlog, url_repository) VALUES\n" +
//                            "    (1, true, 'Team Alpha', '2023-04-01 00:00:00', '2023-06-01 00:00:00', 'https://example.com/backlog/team_alpha', 'https://github.com/team_alpha'),\n" +
//                            "    (2, false, 'Team Beta', '2023-05-02 00:00:00', '2023-07-01 00:00:00', 'https://example.com/backlog/team_beta', 'https://github.com/team_beta'),\n" +
//                            "    (3, true, 'Team Gamma', '2023-06-03 00:00:00', '2023-08-01 00:00:00', 'https://example.com/backlog/team_gamma', 'https://github.com/team_gamma'),\n" +
//                            "    (4, false, 'Team Delta', '2023-07-04 00:00:00', '2023-06-01 00:00:00', 'https://example.com/backlog/team_delta', 'https://github.com/team_delta'),\n" +
//                            "    (5, true, 'Team Epsilon', '2023-08-05 00:00:00', '2023-07-01 00:00:00', 'https://example.com/backlog/team_epsilon', 'https://github.com/team_epsilon'),\n" +
//                            "    (6, false, 'Team Zeta', '2023-09-06 00:00:00', '2023-08-01 00:00:00', 'https://example.com/backlog/team_zeta', 'https://github.com/team_zeta'),\n" +
//                            "    (7, true, 'Team Eta', '2023-04-07 00:00:00', '2023-06-01 00:00:00', 'https://example.com/backlog/team_eta', 'https://github.com/team_eta'),\n" +
//                            "    (8, false, 'Team Theta', '2023-05-08 00:00:00', '2023-07-01 00:00:00', 'https://example.com/backlog/team_theta', 'https://github.com/team_theta'),\n" +
//                            "    (9, true, 'Team Iota', '2023-06-09 00:00:00', '2023-08-01 00:00:00', 'https://example.com/backlog/team_iota', 'https://github.com/team_iota'),\n" +
//                            "    (10, false, 'Team Kappa', '2023-07-10 00:00:00', '2023-06-01 00:00:00', 'https://example.com/backlog/team_kappa', 'https://github.com/team_kappa'),\n" +
//                            "    (11, true, 'Team Lambda', '2023-08-11 00:00:00', '2023-07-01 00:00:00', 'https://example.com/backlog/team_lambda', 'https://github.com/team_lambda'),\n" +
//                            "    (12, false, 'Team Mu', '2023-09-12 00:00:00', '2023-08-01 00:00:00', 'https://example.com/backlog/team_mu', 'https://github.com/team_mu'),\n" +
//                            "    (13, true, 'Team Nu', '2023-04-13 00:00:00', '2023-10-01 00:00:00', 'https://example.com/backlog/team_nu', 'https://github.com/team_nu'),\n" +
//                            "    (14, false, 'Team Xi', '2023-05-14 00:00:00', '2023-07-01 00:00:00', 'https://example.com/backlog/team_xi', 'https://github.com/team_xi'),\n" +
//                            "    (15, true, 'Team Omicron', '2023-06-15 00:00:00', '2023-08-01 00:00:00', 'https://example.com/backlog/team_omicron', 'https://github.com/team_omicron'),\n" +
//                            "    (16, false, 'Team Pi', '2023-07-16 00:00:00', '2023-06-01 00:00:00', 'https://example.com/backlog/team_pi', 'https://github.com/team_pi'),\n" +
//                            "    (17, true, 'Team Rho', '2023-08-17 00:00:00', '2023-07-01 00:00:00', 'https://example.com/backlog/team_rho', 'https://github.com/team_rho'),\n" +
//                            "    (18, false, 'Team Sigma', '2023-09-18 00:00:00', '2023-08-01 00:00:00', 'https://example.com/backlog/team_sigma', 'https://github.com/team_sigma'),\n" +
//                            "    (19, true, 'Team Tau', '2023-04-19 00:00:00', '2023-09-01 00:00:00', 'https://example.com/backlog/team_tau', 'https://github.com/team_tau'),\n" +
//                            "    (20, false, 'Team Upsilon', '2023-05-20 00:00:00', '2023-01-01 00:00:00', 'https://example.com/backlog/team_upsilon', 'https://github.com/team_upsilon');\n" +
//                            "\n" +
//                            "INSERT INTO public.intern (id_intern, email, firstname, hired_at, hired_by, is_deleted, lastname, phone_number, presence_type, trainings, url_cv) VALUES\n" +
//                            "    (1, 'email1@gmail.com', 'John', NULL, NULL, false, 'Doe', '1020304958', 'HYBRIDE', NULL, 'generic_path/cv1'),\n" +
//                            "    (2, 'email2@gmail.com', 'Alice', NULL, NULL, false, 'Smith', NULL, 'DISTANCIEL', NULL, 'generic_path/cv2'),\n" +
//                            "    (3, 'email3@gmail.com', 'Bob', NULL, NULL, true, 'Johnson', '2029384756', 'PRESENTIEL', NULL, NULL),\n" +
//                            "    (4, 'email4@gmail.com', 'Emily', '2023-07-29 12:34:56', 'HR123', false, 'Brown', NULL, 'HYBRIDE', NULL, 'generic_path/cv4'),\n" +
//                            "    (5, 'email5@gmail.com', 'Michael', NULL, NULL, false, 'Miller', '9876543210', 'DISTANCIEL', NULL, NULL),\n" +
//                            "    (6, 'email6@gmail.com', 'Sophia', NULL, NULL, true, 'Davis', '8901234567', 'PRESENTIEL', NULL, 'generic_path/cv6'),\n" +
//                            "    (7, 'email7@gmail.com', 'William', '2023-07-29 09:15:30', 'HR456', false, 'Taylor', '7778889999', 'HYBRIDE', NULL, 'generic_path/cv7'),\n" +
//                            "    (8, 'email8@gmail.com', 'Emma', NULL, NULL, false, 'Anderson', NULL, 'DISTANCIEL', NULL, NULL),\n" +
//                            "    (9, 'email9@gmail.com', 'James', NULL, NULL, true, 'Wilson', '5554443333', 'PRESENTIEL', NULL, 'generic_path/cv9'),\n" +
//                            "    (10, 'email10@gmail.com', 'Olivia', '2023-07-29 16:28:45', 'HR789', false, 'Thomas', '1112223333', 'HYBRIDE', NULL, 'generic_path/cv10'),\n" +
//                            "    (11, 'email11@gmail.com', 'Daniel', NULL, NULL, false, 'Martinez', NULL, 'DISTANCIEL', NULL, NULL),\n" +
//                            "    (12, 'email12@gmail.com', 'Ava', NULL, NULL, true, 'Robinson', '4445556666', 'PRESENTIEL', NULL, 'generic_path/cv12'),\n" +
//                            "    (13, 'email13@gmail.com', 'David', '2023-07-29 14:59:26', 'HR987', false, 'Clark', '6667778888', 'HYBRIDE', NULL, 'generic_path/cv13'),\n" +
//                            "    (14, 'email14@gmail.com', 'Isabella', NULL, NULL, false, 'Rodriguez', NULL, 'DISTANCIEL', NULL, NULL),\n" +
//                            "    (15, 'email15@gmail.com', 'Joseph', NULL, NULL, true, 'Lee', '9990001111', 'PRESENTIEL', NULL, 'generic_path/cv15'),\n" +
//                            "    (16, 'email16@gmail.com', 'Sophie', '2023-07-29 10:20:30', 'HR654', false, 'Scott', '1234567890', 'HYBRIDE', NULL, 'generic_path/cv16'),\n" +
//                            "    (17, 'email17@gmail.com', 'Alexander', NULL, NULL, false, 'Perez', NULL, 'DISTANCIEL', NULL, NULL),\n" +
//                            "    (18, 'email18@gmail.com', 'Charlotte', NULL, NULL, true, 'Turner', '4441118888', 'PRESENTIEL', NULL, 'generic_path/cv18'),\n" +
//                            "    (19, 'email19@gmail.com', 'Matthew', '2023-07-29 08:15:45', 'HR321', false, 'Baker', '7774441111', 'HYBRIDE', NULL, 'generic_path/cv19'),\n" +
//                            "    (20, 'email20@gmail.com', 'Amelia', NULL, NULL, false, 'Bell', NULL, 'DISTANCIEL', NULL, NULL);\n" +
//                            "\n" +
//                            "-- Team Alpha skills\n" +
//                            "INSERT INTO public.team_skills (team_id_team, skills_skill_name) VALUES\n" +
//                            "    (1, 'Java'),\n" +
//                            "    (1, 'Python');\n" +
//                            "\n" +
//                            "-- Team Beta skills\n" +
//                            "INSERT INTO public.team_skills (team_id_team, skills_skill_name) VALUES\n" +
//                            "    (2, 'JavaScript'),\n" +
//                            "    (2, 'C++'),\n" +
//                            "    (2, 'Ruby');\n" +
//                            "\n" +
//                            "-- Team Gamma skills\n" +
//                            "INSERT INTO public.team_skills (team_id_team, skills_skill_name) VALUES\n" +
//                            "    (3, 'Swift');\n" +
//                            "\n" +
//                            "-- Team Delta skills\n" +
//                            "INSERT INTO public.team_skills (team_id_team, skills_skill_name) VALUES\n" +
//                            "    (4, 'Go'),\n" +
//                            "    (4, 'PHP');\n" +
//                            "\n" +
//                            "-- Team Epsilon skills\n" +
//                            "INSERT INTO public.team_skills (team_id_team, skills_skill_name) VALUES\n" +
//                            "    (5, 'Kotlin'),\n" +
//                            "    (5, 'Scala'),\n" +
//                            "    (5, 'Haskell');\n" +
//                            "\n" +
//                            "-- Team Zeta skills\n" +
//                            "INSERT INTO public.team_skills (team_id_team, skills_skill_name) VALUES\n" +
//                            "    (6, 'Perl');\n" +
//                            "\n" +
//                            "-- Team Eta skills\n" +
//                            "INSERT INTO public.team_skills (team_id_team, skills_skill_name) VALUES\n" +
//                            "    (7, 'Objective-C'),\n" +
//                            "    (7, 'Lua');\n" +
//                            "\n" +
//                            "-- Team Theta skills\n" +
//                            "INSERT INTO public.team_skills (team_id_team, skills_skill_name) VALUES\n" +
//                            "    (8, 'Dart'),\n" +
//                            "    (8, 'F#');\n" +
//                            "\n" +
//                            "-- Team Iota skills\n" +
//                            "INSERT INTO public.team_skills (team_id_team, skills_skill_name) VALUES\n" +
//                            "    (9, 'Groovy'),\n" +
//                            "    (9, 'Rust');\n" +
//                            "\n" +
//                            "-- Team Kappa skills\n" +
//                            "INSERT INTO public.team_skills (team_id_team, skills_skill_name) VALUES\n" +
//                            "    (10, 'R');\n" +
//                            "\n" +
//                            "-- Team Lambda skills\n" +
//                            "INSERT INTO public.team_skills (team_id_team, skills_skill_name) VALUES\n" +
//                            "    (11, 'C#'),\n" +
//                            "    (11, 'Python');\n" +
//                            "\n" +
//                            "-- Team Mu skills\n" +
//                            "INSERT INTO public.team_skills (team_id_team, skills_skill_name) VALUES\n" +
//                            "    (12, 'Ruby');\n" +
//                            "\n" +
//                            "-- Team Nu skills\n" +
//                            "INSERT INTO public.team_skills (team_id_team, skills_skill_name) VALUES\n" +
//                            "    (13, 'Java'),\n" +
//                            "    (13, 'Swift');\n" +
//                            "\n" +
//                            "-- Team Xi skills\n" +
//                            "INSERT INTO public.team_skills (team_id_team, skills_skill_name) VALUES\n" +
//                            "    (14, 'TypeScript');\n" +
//                            "\n" +
//                            "-- Team Omicron skills\n" +
//                            "INSERT INTO public.team_skills (team_id_team, skills_skill_name) VALUES\n" +
//                            "    (15, 'Go');\n" +
//                            "\n" +
//                            "-- Team Pi skills\n" +
//                            "INSERT INTO public.team_skills (team_id_team, skills_skill_name) VALUES\n" +
//                            "    (16, 'Rust'),\n" +
//                            "    (16, 'PHP');\n" +
//                            "\n" +
//                            "-- Team Rho skills\n" +
//                            "INSERT INTO public.team_skills (team_id_team, skills_skill_name) VALUES\n" +
//                            "    (17, 'Kotlin'),\n" +
//                            "    (17, 'Scala');\n" +
//                            "\n" +
//                            "-- Team Sigma skills\n" +
//                            "INSERT INTO public.team_skills (team_id_team, skills_skill_name) VALUES\n" +
//                            "    (18, 'Haskell'),\n" +
//                            "    (18, 'Perl');\n" +
//                            "\n" +
//                            "-- Team Tau skills\n" +
//                            "INSERT INTO public.team_skills (team_id_team, skills_skill_name) VALUES\n" +
//                            "    (19, 'Objective-C'),\n" +
//                            "    (19, 'Lua');\n" +
//                            "\n" +
//                            "-- Team Upsilon skills\n" +
//                            "INSERT INTO public.team_skills (team_id_team, skills_skill_name) VALUES\n" +
//                            "    (20, 'Dart'),\n" +
//                            "    (20, 'F#');\n" +
//                            "\n" +
//                            "-- Team Alpha\n" +
//                            "INSERT INTO public.intern_team (id_intern, id_team, end_date, start_date) VALUES\n" +
//                            "    (1, 1, '2023-09-28 16:33:56.889', '2023-07-28 17:32:42.926'),\n" +
//                            "    (2, 1, '2023-09-15 11:25:38.312', '2023-07-15 09:45:16.709'),\n" +
//                            "    (3, 1, '2023-10-05 09:10:20.515', '2023-08-05 08:15:42.927');\n" +
//                            "\n" +
//                            "-- Team Beta\n" +
//                            "INSERT INTO public.intern_team (id_intern, id_team, end_date, start_date) VALUES\n" +
//                            "    (4, 2, '2023-09-30 14:17:53.125', '2023-07-30 10:35:22.871'),\n" +
//                            "    (5, 2, '2023-10-20 16:29:40.108', '2023-08-20 09:55:47.713');\n" +
//                            "\n" +
//                            "-- Team Gamma\n" +
//                            "INSERT INTO public.intern_team (id_intern, id_team, end_date, start_date) VALUES\n" +
//                            "    (6, 3, '2023-10-15 13:10:35.320', '2023-08-15 11:22:16.829');\n" +
//                            "\n" +
//                            "-- Team Delta\n" +
//                            "INSERT INTO public.intern_team (id_intern, id_team, end_date, start_date) VALUES\n" +
//                            "    (7, 4, '2023-10-10 09:43:17.961', '2023-08-10 07:55:12.459'),\n" +
//                            "    (8, 4, '2023-09-25 17:50:32.602', '2023-07-25 16:00:48.917');\n" +
//                            "\n" +
//                            "-- Team Epsilon\n" +
//                            "INSERT INTO public.intern_team (id_intern, id_team, end_date, start_date) VALUES\n" +
//                            "    (9, 5, '2023-10-05 15:22:40.411', '2023-08-05 14:05:32.082'),\n" +
//                            "    (10, 5, '2023-09-15 12:05:59.768', '2023-07-15 10:20:18.115');\n" +
//                            "\n" +
//                            "-- Team Zeta\n" +
//                            "INSERT INTO public.intern_team (id_intern, id_team, end_date, start_date) VALUES\n" +
//                            "    (11, 6, '2023-10-18 08:30:45.902', '2023-08-18 07:10:56.612'),\n" +
//                            "    (12, 6, '2023-09-30 14:55:27.458', '2023-07-30 13:30:18.990');\n" +
//                            "\n" +
//                            "-- Team Eta\n" +
//                            "INSERT INTO public.intern_team (id_intern, id_team, end_date, start_date) VALUES\n" +
//                            "    (13, 7, '2023-09-20 11:25:38.311', '2023-07-20 09:45:16.709');\n" +
//                            "\n" +
//                            "-- Team Theta\n" +
//                            "INSERT INTO public.intern_team (id_intern, id_team, end_date, start_date) VALUES\n" +
//                            "    (14, 8, '2023-10-10 15:17:53.124', '2023-08-10 13:35:22.871'),\n" +
//                            "    (15, 8, '2023-10-05 16:29:40.108', '2023-08-05 15:55:47.713');\n" +
//                            "\n" +
//                            "-- Team Iota\n" +
//                            "INSERT INTO public.intern_team (id_intern, id_team, end_date, start_date) VALUES\n" +
//                            "    (16, 9, '2023-09-25 12:10:35.319', '2023-07-25 11:22:16.829'),\n" +
//                            "    (17, 9, '2023-10-20 10:15:12.624', '2023-08-20 09:25:46.824');\n" +
//                            "\n" +
//                            "-- Team Kappa\n" +
//                            "INSERT INTO public.intern_team (id_intern, id_team, end_date, start_date) VALUES\n" +
//                            "    (18, 10, '2023-10-15 15:23:17.961', '2023-08-15 13:35:12.459'),\n" +
//                            "    (19, 10, '2023-10-05 17:50:32.601', '2023-08-05 15:00:48.917'),\n" +
//                            "    (20, 10, '2023-09-28 10:55:20.628', '2023-07-28 09:50:42.926');\n" +
//                            "\n" +
//                            "-- Inserting mock data for the \"role\" table\n" +
//                            "INSERT INTO public.role (role_name) VALUES\n" +
//                            "('Admin'),\n" +
//                            "('Manager');\n" +
//                            "\n" +
//                            "-- Inserting mock data for the \"manager\" table\n" +
//                            "INSERT INTO public.manager (id_manager, email, lastname, firstname, password, salt, is_deleted, is_validated, phone_number, role_name)\n" +
//                            "VALUES\n" +
//                            "(1,'john@example.com', 'Doe', 'John', 'hashed_password_1', 'salt_1', false, true, '1234567890', 'Manager'),\n" +
//                            "(2,'alice@example.com', 'Smith', 'Alice', 'hashed_password_2', 'salt_2', false, true, '9876543210', 'Manager'),\n" +
//                            "(3,'bob@example.com', 'Johnson', 'Bob', 'hashed_password_3', 'salt_3', false, true, '5555555555', 'Manager'),\n" +
//                            "(4,'emily@example.com', 'Williams', 'Emily', 'hashed_password_4', 'salt_4', false, true, '4444444444', 'Manager'),\n" +
//                            "(5,'david@example.com', 'Brown', 'David', 'hashed_password_5', 'salt_5', false, true, '9999999999', 'Admin'),\n" +
//                            "(6,'emma@example.com', 'Lee', 'Emma', 'hashed_password_6', 'salt_6', false, true, '1111111111', 'Manager'),\n" +
//                            "(7,'michael@example.com', 'Miller', 'Michael', 'hashed_password_7', 'salt_7', false, true, '2222222222', 'Manager'),\n" +
//                            "(8,'olivia@example.com', 'Wilson', 'Olivia', 'hashed_password_8', 'salt_8', false, true, '3333333333', 'Admin'),\n" +
//                            "(9,'james@example.com', 'Davis', 'James', 'hashed_password_9', 'salt_9', false, true, '6666666666', 'Manager'),\n" +
//                            "(10,'sophia@example.com', 'Taylor', 'Sophia', 'hashed_password_10', 'salt_10', false, true, '7777777777', 'Manager'),\n" +
//                            "(11,'manager11@example.com', 'Last11', 'First11', 'hashed_password_11', 'salt_11', false, true, '9999999900', 'Manager'),\n" +
//                            "(12,'manager12@example.com', 'Last12', 'First12', 'hashed_password_12', 'salt_12', false, true, '8888888888', 'Manager'),\n" +
//                            "(13,'manager13@example.com', 'Last13', 'First13', 'hashed_password_13', 'salt_13', false, true, '4444444444', 'Manager'),\n" +
//                            "(14,'manager14@example.com', 'Last14', 'First14', 'hashed_password_14', 'salt_14', false, true, '7777777777', 'Manager'),\n" +
//                            "(15,'manager15@example.com', 'Last15', 'First15', 'hashed_password_15', 'salt_15', false, true, '5555555555', 'Manager');\n" +
//                            "\n" +
//                            "-- Inserting relations between managers and skills in the \"manager_skill\" table\n" +
//                            "INSERT INTO manager_skill (id_manager, skill_name, level)\n" +
//                            "VALUES\n" +
//                            "-- Manager 1 skills\n" +
//                            "(1, 'JavaScript', 2),\n" +
//                            "(1, 'Java', 1),\n" +
//                            "\n" +
//                            "-- Manager 2 skills\n" +
//                            "(2, 'C++', 2),\n" +
//                            "\n" +
//                            "-- Manager 3 skills\n" +
//                            "(3, 'JavaScript', 3),\n" +
//                            "(3, 'Ruby', 1),\n" +
//                            "(3, 'Java', 2),\n" +
//                            "\n" +
//                            "-- Manager 4 skills\n" +
//                            "(4, 'Python', 2),\n" +
//                            "(4, 'Go', 3),\n" +
//                            "(4, 'TypeScript', 1),\n" +
//                            "\n" +
//                            "-- Manager 5 skills\n" +
//                            "(5, 'Java', 3),\n" +
//                            "(5, 'Ruby', 1),\n" +
//                            "\n" +
//                            "-- Manager 6 skills\n" +
//                            "(6, 'Python', 1),\n" +
//                            "\n" +
//                            "-- Manager 7 skills\n" +
//                            "(7, 'Java', 2),\n" +
//                            "(7, 'C++', 1),\n" +
//                            "(7, 'JavaScript', 3),\n" +
//                            "\n" +
//                            "-- Manager 8 skills\n" +
//                            "(8, 'Python', 1),\n" +
//                            "(8, 'C#', 2),\n" +
//                            "\n" +
//                            "-- Manager 9 skills\n" +
//                            "(9, 'JavaScript', 2),\n" +
//                            "(9, 'TypeScript', 3),\n" +
//                            "\n" +
//                            "-- Manager 10 skills\n" +
//                            "(10, 'Java', 3),\n" +
//                            "\n" +
//                            "-- Manager 11 skills\n" +
//                            "(11, 'Python', 1),\n" +
//                            "(11, 'C++', 3),\n" +
//                            "\n" +
//                            "-- Manager 12 skills\n" +
//                            "(12, 'Java', 2),\n" +
//                            "(12, 'Go', 1),\n" +
//                            "(12, 'JavaScript', 3),\n" +
//                            "\n" +
//                            "-- Manager 13 skills\n" +
//                            "(13, 'Go', 3),\n" +
//                            "\n" +
//                            "-- Manager 14 skills\n" +
//                            "(14, 'Java', 2),\n" +
//                            "(14, 'C++', 1),\n" +
//                            "(14, 'TypeScript', 3),\n" +
//                            "\n" +
//                            "-- Manager 15 skills\n" +
//                            "(15, 'Python', 3),\n" +
//                            "(15, 'Swift', 1);\n" +
//                            "\n" +
//                            "-- Inserting mock data for team_managers table\n" +
//                            "INSERT INTO public.team_managers (id_team, id_manager)\n" +
//                            "VALUES\n" +
//                            "(1, 1),\n" +
//                            "(2, 1),\n" +
//                            "(3, 1),\n" +
//                            "(4, 2),\n" +
//                            "(4, 3),\n" +
//                            "(6, 6),\n" +
//                            "(7, 7),\n" +
//                            "(8, 8),\n" +
//                            "(9, 9),\n" +
//                            "(10, 2),\n" +
//                            "(11, 10),\n" +
//                            "(5, 2),\n" +
//                            "(8, 12),\n" +
//                            "(14, 14),\n" +
//                            "(15, 15);\n" +
//                            "\n" +
//                            "-- Insertion de relations entre les stagiaires et les compétences dans la table \"intern_skill\"\n" +
//                            "INSERT INTO intern_skill (id_intern, skill_name, level)\n" +
//                            "VALUES\n" +
//                            "-- Compétences du stagiaire 1\n" +
//                            "(1, 'JavaScript', 2),\n" +
//                            "(1, 'Java', 1),\n" +
//                            "\n" +
//                            "-- Compétences du stagiaire 2\n" +
//                            "(2, 'C++', 2),\n" +
//                            "\n" +
//                            "-- Compétences du stagiaire 3\n" +
//                            "(3, 'JavaScript', 3),\n" +
//                            "(3, 'Ruby', 1),\n" +
//                            "(3, 'Java', 2),\n" +
//                            "\n" +
//                            "-- Compétences du stagiaire 4\n" +
//                            "(4, 'Python', 2),\n" +
//                            "(4, 'Go', 3),\n" +
//                            "(4, 'TypeScript', 1),\n" +
//                            "\n" +
//                            "-- Compétences du stagiaire 5\n" +
//                            "(5, 'Java', 3),\n" +
//                            "(5, 'Ruby', 1),\n" +
//                            "\n" +
//                            "-- Compétences du stagiaire 6\n" +
//                            "(6, 'Python', 1),\n" +
//                            "\n" +
//                            "-- Compétences du stagiaire 7\n" +
//                            "(7, 'Java', 2),\n" +
//                            "(7, 'C++', 1),\n" +
//                            "(7, 'JavaScript', 3),\n" +
//                            "\n" +
//                            "-- Compétences du stagiaire 8\n" +
//                            "(8, 'Python', 1),\n" +
//                            "(8, 'C#', 2),\n" +
//                            "\n" +
//                            "-- Compétences du stagiaire 9\n" +
//                            "(9, 'JavaScript', 2),\n" +
//                            "(9, 'TypeScript', 3),\n" +
//                            "\n" +
//                            "-- Compétences du stagiaire 10\n" +
//                            "(10, 'Java', 3),\n" +
//                            "\n" +
//                            "-- Compétences du stagiaire 11\n" +
//                            "(11, 'Python', 1),\n" +
//                            "(11, 'C++', 3),\n" +
//                            "\n" +
//                            "-- Compétences du stagiaire 12\n" +
//                            "(12, 'Java', 2),\n" +
//                            "(12, 'Go', 1),\n" +
//                            "(12, 'JavaScript', 3),\n" +
//                            "\n" +
//                            "-- Compétences du stagiaire 13\n" +
//                            "(13, 'Go', 3),\n" +
//                            "\n" +
//                            "-- Compétences du stagiaire 14\n" +
//                            "(14, 'Java', 2),\n" +
//                            "(14, 'C++', 1),\n" +
//                            "(14, 'TypeScript', 3),\n" +
//                            "\n" +
//                            "-- Compétences du stagiaire 15\n" +
//                            "(15, 'Python', 3),\n" +
//                            "(15, 'Swift', 1);\n"
//                    ;
//
//
//            statement.executeUpdate(sqlStatement);
//        }
//    }
//}
