package sp.praq.controllers;
import org.springframework.cglib.core.Local;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.PostExchange;
import sp.praq.models.*;
import sp.praq.services.*;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

@Controller
public class Controllers {
    CompanyService cmp_s = new CompanyService();
    CourseService crs_s = new CourseService();
    GroupService g_s = new GroupService();
    LessonService l_s = new LessonService();
    StudentService s_s = new StudentService();
    StudentGroupService sg_s = new StudentGroupService();
    TutorService t_s = new TutorService();

    @GetMapping(value = {"/", "/index"})
    public String home() {
        return "index";
    }

    @GetMapping(value = {"/error"})
    public String showError(@RequestParam(value = "error_msg", required = false) String msg, Model model) {
//        if (msg != null && msg.equals("wrong_interval")) {
//            model.addAttribute("error_msg", "Неправильно задан временной промежуток!");
//        }

        return "error";
    }

    @GetMapping(value = {"/companies"})
    public String listCompanies(Model model) {
        try {
            model.addAttribute("companies", cmp_s.findAll());
            return "companies";
        } catch (Exception e) {
            model.addAttribute("error_msg", e);
            return "error";
        }
    }

    @GetMapping(value = {"/company"})
    public String viewCompany(@RequestParam("id")Integer id, Model model) {
        try {
            Company c = cmp_s.findById(id);
            model.addAttribute("company", c);
            return "company";
        } catch (Exception e) {
            model.addAttribute("error_msg", e);
            return "error";
        }
    }

    @GetMapping("/add_company")
    public String showAddCompanyPage(Model model) {
        return "add_company";
    }

    @PostMapping("/save_company")
    public String saveCompany(@RequestParam("name") String companyName,
                              @RequestParam("city") String city,
                              @RequestParam("street") String street,
                              @RequestParam("house") String house, Model model) {
        try {
            Company comp = new Company(companyName, new Address(city, street, house));
            cmp_s.save(comp);
            return "redirect:/companies";
        } catch (Exception e) {
            model.addAttribute("error_msg", e);
            return "error";
        }
    }

    @GetMapping(value = {"/edit_company"})
    public String editCompany(@RequestParam("id") Integer id, Model model) {
        try {
            model.addAttribute("company", cmp_s.findById(id));
            return "edit_company";
        } catch (Exception e) {
            model.addAttribute("error_msg", e);
            return "error";
        }
    }

    @PostMapping("/update_company")
    public String updateCompany(@RequestParam(value = "id", required = true) Integer id,
                                @RequestParam(value = "title", required = true) String title,
                                @RequestParam(value = "city", required = true) String city,
                                @RequestParam(value = "street", required = true) String street,
                                @RequestParam(value = "house", required = true) String house, Model model) {
        try {
            Company cmp = cmp_s.findById(id);
            cmp.setTitle(title);
            cmp.setAddress(new Address(city, street, house));
            cmp_s.update(cmp);
            return "redirect:/companies";
        } catch (Exception e) {
            model.addAttribute("error_msg", e);
            return "error";
        }
    }

    @PostMapping(value = {"/delete_company"})
    public String deleteCompany(@RequestParam("id") Integer id, Model model) {
        try {
            cmp_s.deleteById(id);
            return "redirect:/companies";
        } catch (Exception e) {
            model.addAttribute("error_msg", e);
            return "error";
        }
    }

    @GetMapping(value = {"/courses"})
    public String listCourses(@RequestParam(value = "listBy", required = false) String list_mode, Model model) {
        try {
            if (list_mode == null || list_mode.equals("title")) model.addAttribute("courses", crs_s.listByTitle());
            else if (list_mode.equals("company")) model.addAttribute("courses", crs_s.listByCompany());
            else model.addAttribute("courses", crs_s.findAll());
            return "courses";
        } catch (Exception e) {
            model.addAttribute("error_msg", e);
            return "error";
        }
    }

    @GetMapping(value = {"/course"})
    public String viewCourse(@RequestParam("id") Integer id, Model model) {
        try {
            Course c = crs_s.findById(id);
            model.addAttribute("course", c);
            return "course";
        } catch (Exception e) {
            model.addAttribute("error_msg", e);
            return "error";
        }
    }

    @GetMapping("/add_course")
    public String showAddCoursePage(Model model) {
        try {
            model.addAttribute("companies", cmp_s.findAll());
            return "add_course";
        } catch (Exception e) {
            model.addAttribute("error_msg", e);
            return "error";
        }
    }

    @PostMapping("/save_course")
    public String saveCourse(@RequestParam("title") String title,
                              @RequestParam("totalHours") Double total_hours,
                              @RequestParam("companyId") Integer companyId,
                              @RequestParam("description") String description, Model model) {
        try {
            Company comp = cmp_s.findById(companyId);
            Course course = new Course(title, total_hours, description);
            course.setCompany_id(comp);
            crs_s.save(course);

            return "redirect:/courses";
        } catch (Exception e) {
            model.addAttribute("error_msg", e);
            return "error";
        }
    }

    @GetMapping(value = {"/edit_course"})
    public String editCourse(@RequestParam("id") Integer id, Model model) {
        try {
            model.addAttribute("companies", cmp_s.findAll());
            model.addAttribute("course", crs_s.findById(id));
            return "edit_course";
        } catch (Exception e) {
            model.addAttribute("error_msg", e);
            return "error";
        }
    }

    @PostMapping(value = {"/update_course"})
    public String updateCourse(@RequestParam(value = "id", required = true) Integer id,
                               @RequestParam(value = "title", required = true) String title,
                               @RequestParam(value = "totalHours", required = true) Double totalHours,
                               @RequestParam(value = "companyId", required = true) Integer companyId,
                               @RequestParam(value = "description", required = true) String description, Model model) {
        try {
            Course crs = crs_s.findById(id);
            Company cmp = cmp_s.findById(companyId);

            crs.setTitle(title);
            crs.setTotal_hours(totalHours);
            crs.setDescription(description);
            crs.setCompany_id(cmp);
            crs_s.update(crs);

            return "redirect:/course?id=" + id.toString();
        } catch (Exception e) {
            model.addAttribute("error_msg", e);
            return "error";
        }
    }

    @PostMapping(value = {"/delete_course"})
    public String deleteCourse(@RequestParam("id") Integer id, Model model) {
        try {
            crs_s.deleteById(id);
            return "redirect:/courses";
        } catch (Exception e) {
            model.addAttribute("error_msg", e);
            return "error";
        }
    }

    @GetMapping(value = {"/tutors"})
    public String listTutors(@RequestParam(value = "listBy", required = false) String list_mode,
                             @RequestParam(value = "surname", required = false) String surname,
                             @RequestParam(value = "name", required = false) String name,
                             @RequestParam(value = "patronymic", required = false) String patronymic, Model model) {
        try {
            if (list_mode == null || list_mode.equals("name")) model.addAttribute("tutors", t_s.listByName(surname, name, patronymic));
            else if (list_mode.equals("company")) model.addAttribute("tutors", t_s.listByCompany(surname, name, patronymic));
            else model.addAttribute("tutors", t_s.findAll());

            model.addAttribute("surname", surname == null ? "" : surname);
            model.addAttribute("name", name == null ? "" : name);
            model.addAttribute("patronymic", patronymic == null ? "" : patronymic);

            if (list_mode == null) {
                model.addAttribute("list_mode", "name");
            } else {
                model.addAttribute("list_mode", list_mode);
            }

            return "tutors";
        } catch (Exception e) {
            model.addAttribute("error_msg", e);
            return "error";
        }
    }

    @GetMapping(value = {"/tutor"})
    public String viewTutor(@RequestParam("id") Integer id, Model model) {
        try {
            Tutor t = t_s.findById(id);
            model.addAttribute("tutor", t);
            return "tutor";
        } catch (Exception e) {
            model.addAttribute("error_msg", e);
            return "error";
        }
    }

    @GetMapping(value = {"/show_tutor_schedule"})
    public String viewTutorSchedule(@RequestParam("id") Integer id,
                                    @RequestParam("startDate") String startDate,
                                    @RequestParam("endDate") String endDate, Model model) {
        try {
            Tutor t = t_s.findById(id);
            LocalDateTime start = LocalDate.parse(startDate).atStartOfDay();
            LocalDateTime end = LocalDate.parse(endDate).atStartOfDay();
            if (start.isAfter(end)) {
                model.addAttribute("error_msg", "Неправильно задан временной промежуток!");
                return "error";
            }
            List<Lesson> ll = t_s.getSchedule(t, start, end);

            model.addAttribute("tutor", t);
            model.addAttribute("lessons", ll);
            model.addAttribute("startDate", startDate);
            model.addAttribute("endDate", endDate);
            return "show_tutor_schedule";
        } catch (Exception e) {
            model.addAttribute("error_msg", e);
            return "error";
        }
    }

    @GetMapping(value = {"/add_lesson"})
    public String showAddLessonPage(@RequestParam("tutorId") Integer tutorId,
                                    @RequestParam("startDate") String startDate,
                                    @RequestParam("endDate") String endDate, Model model) {
        try {
            List<Group> lg = new ArrayList<Group>();

            for (Group group : g_s.findAll()) {
                if (group.getTutor_id().getId() == tutorId) lg.add(group);
            }

            model.addAttribute("groups", lg);
            model.addAttribute("tutorId", tutorId);
            model.addAttribute("startDate", startDate);
            model.addAttribute("endDate", endDate);
            return "add_lesson";
        } catch (Exception e) {
            model.addAttribute("error_msg", e);
            return "error";
        }
    }

    @PostMapping(value = {"/save_lesson"})
    public String saveLesson(@RequestParam("groupId") Integer groupId,
                             @RequestParam("datetime") String datetime,
                             @RequestParam("room") Integer room,
                             @RequestParam("duration") Double duration,
                             @RequestParam("tutorId") Integer tutorId,
                             @RequestParam("startDate") String startDate,
                             @RequestParam("endDate") String endDate, Model model) {
        try {
            Group g = g_s.findById(groupId);
            LocalDateTime dt = LocalDateTime.parse(datetime);
            Lesson l = new Lesson(g, dt, room, duration);
            l_s.update(l);
            model.addAttribute("lessons", t_s.getSchedule(t_s.findById(tutorId), LocalDate.parse(startDate).atStartOfDay(), LocalDate.parse(endDate).atStartOfDay()));
            model.addAttribute("startDate", startDate);
            model.addAttribute("endDate", endDate);
            model.addAttribute("tutor", t_s.findById(tutorId));
            return "redirect:/show_tutor_schedule?startDate=" + startDate + "&endDate=" + endDate + "&id=" + tutorId;
        } catch (Exception e) {
            model.addAttribute("error_msg", e);
            return "error";
        }
    }

    @PostMapping(value = {"/delete_lesson"})
    public String deleteLesson(@RequestParam("groupId") Integer groupId,
                               @RequestParam("classDT") String classDT,
                               @RequestParam("classDuration") Double classDuration,
                               @RequestParam("roomNumber") Integer roomNumber,
                               @RequestParam("tutorId") Integer tutorId,
                               @RequestParam("startDate") String startDate,
                               @RequestParam("endDate") String endDate, Model model) {
        try {
            LocalDateTime dt = LocalDateTime.parse(classDT);
            Group g = g_s.findById(groupId);
            l_s.deleteByObj(g, dt, roomNumber, classDuration);
            LocalDateTime start = LocalDate.parse(startDate).atStartOfDay();
            LocalDateTime end = LocalDate.parse(endDate).atStartOfDay();
            Tutor t = t_s.findById(tutorId);
            model.addAttribute("lessons", t_s.getSchedule(t, start, end));
            model.addAttribute("startDate", startDate);
            model.addAttribute("endDate", endDate);
            model.addAttribute("tutor", t);
            return "redirect:/show_tutor_schedule?startDate=" + startDate + "&endDate=" + endDate + "&id=" + tutorId;
        } catch (Exception e) {
            model.addAttribute("error_msg", e);
            return "error";
        }
    }

    @GetMapping("/add_tutor")
    public String showAddTutorPage(Model model) {
        try {
            model.addAttribute("companies", cmp_s.findAll());
            return "add_tutor";
        } catch (Exception e) {
            model.addAttribute("error_msg", e);
            return "error";
        }
    }

    @PostMapping("/save_tutor")
    public String saveTutor(@RequestParam("companyId") Integer companyId,
                            @RequestParam("surname") String surname,
                            @RequestParam("name") String name,
                            @RequestParam("patronymic") String patronymic,
                            @RequestParam("email") String email,
                            @RequestParam("phoneNumber") String phoneNumber,
                            @RequestParam("description") String description, Model model) {
        try {
            Company c = cmp_s.findById(companyId);
            Tutor t = new Tutor(surname, name, patronymic, description, email, phoneNumber);
            t.setCompany_id(c);
            t_s.save(t);

            return "redirect:/tutors";
        } catch (Exception e) {
            model.addAttribute("error_msg", e);
            return "error";
        }
    }

    @GetMapping(value = {"/edit_tutor"})
    public String editTutor(@RequestParam("id") Integer id, Model model) {
        try {
            model.addAttribute("companies", cmp_s.findAll());
            model.addAttribute("tutor", t_s.findById(id));
            return "edit_tutor";
        } catch (Exception e) {
            model.addAttribute("error_msg", e);
            return "error";
        }
    }

    @PostMapping(value = {"/update_tutor"})
    public String updateTutor(@RequestParam(value = "id", required = true) Integer id,
                              @RequestParam(value = "surname", required = true) String surname,
                              @RequestParam(value = "name", required = true) String name,
                              @RequestParam(value = "patronymic", required = true) String patronymic,
                              @RequestParam(value = "email", required = true) String email,
                              @RequestParam(value = "phoneNumber", required = true) String phoneNumber,
                              @RequestParam(value = "description", required = true) String description,
                              @RequestParam(value = "companyId", required = true) Integer companyId, Model model) {
        try {
            Tutor t = t_s.findById(id);
            Company cmp = cmp_s.findById(companyId);

            t.setSurname(surname);
            t.setName(name);
            t.setPatronymic(patronymic);
            t.setEmail(email);
            t.setPhone_number(phoneNumber);
            t.setDescription(description);
            t.setCompany_id(cmp);
            t_s.update(t);

            return "redirect:/tutor?id=" + id.toString();
        } catch (Exception e) {
            model.addAttribute("error_msg", e);
            return "error";
        }
    }

    @PostMapping(value = {"/delete_tutor"})
    public String deleteTutor(@RequestParam("id") Integer id, Model model) {
        try {
            t_s.deleteById(id);
            return "redirect:/tutors";
        } catch (Exception e) {
            model.addAttribute("error_msg", e);
            return "error";
        }
    }

    @GetMapping(value = {"/groups"})
    public String listGroups(Model model) {
        try {
            model.addAttribute("groups", g_s.findAll());
            return "groups";
        } catch (Exception e) {
            model.addAttribute("error_msg", e);
            return "error";
        }
    }

    @GetMapping(value = {"/group"})
    public String viewGroup(@RequestParam("id") Integer id, Model model) {
        try {
            Group g = g_s.findById(id);
            model.addAttribute("group", g);
            return "group";
        } catch (Exception e) {
            model.addAttribute("error_msg", e);
            return "error";
        }
    }

    @GetMapping("/add_group")
    public String showAddGroupPage(Model model) {
        try {
            model.addAttribute("courses", crs_s.findAll());
            model.addAttribute("tutors", t_s.findAll());
            return "add_group";
        } catch (Exception e) {
            model.addAttribute("error_msg", e);
            return "error";
        }
    }

    @PostMapping("/save_group")
    public String saveGroup(@RequestParam("courseId") Integer courseId,
                            @RequestParam("tutorId") Integer tutorId, Model model) {
        try {
            Group g = new Group();
            Course crs = crs_s.findById(courseId);
            Tutor t = t_s.findById(tutorId);
            g.setCourse_id(crs);
            g.setTutor_id(t);
            g_s.save(g);

            return "redirect:/groups";
        } catch (Exception e) {
            model.addAttribute("error_msg", e);
            return "error";
        }
    }

    @GetMapping(value = {"/edit_group"})
    public String editGroup(@RequestParam("id") Integer id, Model model) {
        try {
            model.addAttribute("courses", crs_s.findAll());
            model.addAttribute("tutors", t_s.findAll());
            model.addAttribute("group", g_s.findById(id));
            return "edit_group";
        } catch (Exception e) {
            model.addAttribute("error_msg", e);
            return "error";
        }
    }

    @PostMapping(value = {"/update_group"})
    public String updateGroup(@RequestParam(value = "id", required = true) Integer id,
                               @RequestParam(value = "courseId", required = true) Integer courseId,
                               @RequestParam(value = "tutorId", required = true) Integer tutorId, Model model) {
        try {
            Course crs = crs_s.findById(courseId);
            Tutor t = t_s.findById(tutorId);

            Group g = g_s.findById(id);
            g.setCourse_id(crs);
            g.setTutor_id(t);
            g_s.update(g);

            return "redirect:/group?id=" + id.toString();
        } catch (Exception e) {
            model.addAttribute("error_msg", e);
            return "error";
        }
    }

    @PostMapping(value = {"/delete_group"})
    public String deleteGroup(@RequestParam("id") Integer id, Model model) {
        try {
            g_s.deleteById(id);
            return "redirect:/groups";
        } catch (Exception e) {
            model.addAttribute("error_msg", e);
            return "error";
        }
    }

    @GetMapping(value = {"/enlist_student"})
    public String enlistStudentToGroup(@RequestParam("id") Integer id, Model model) {
        try {
            model.addAttribute("group", g_s.findById(id));
            List<Student> ls = s_s.findAll();
            g_s.findById(id).getStudents_of_group().forEach(sg -> ls.remove(sg.getStudent_id()));
            model.addAttribute("students", ls);
            return "enlist_student";
        } catch (Exception e) {
            model.addAttribute("error_msg", e);
            return "error";
        }
    }

    @PostMapping(value = {"/save_sog"})
    public String saveGroupOfStudents(@RequestParam(value = "groupId", required = true) Integer groupId,
                                      @RequestParam(value = "studentId", required = true) Integer studentId, Model model) {
        try {
            Group g = g_s.findById(groupId);
            Student s = s_s.findById(studentId);

            StudentGroup sog = new StudentGroup(s, g);
            sg_s.update(sog);

            return "redirect:/group?id=" + groupId.toString();
        } catch (Exception e) {
            model.addAttribute("error_msg", e);
            return "error";
        }
    }

    @PostMapping(value = {"/expel_student"})
    public String expelStudent(@RequestParam(value = "groupId", required = true) Integer groupId,
                               @RequestParam(value = "studentId", required = true) Integer studentId, Model model) {
        try {
            Group g = g_s.findById(groupId);
            Student s = s_s.findById(studentId);
            sg_s.deleteByObj(s, g);

            return "redirect:/group?id=" + groupId.toString();
        } catch (Exception e) {
            model.addAttribute("error_msg", e);
            return "error";
        }
    }

    @GetMapping(value = {"/students"})
    public String listStudents(@RequestParam(value = "surname", required = false) String surname,
                               @RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "patronymic", required = false) String patronymic, Model model) {
        try {
            if (surname == null && name == null && patronymic == null) {
                model.addAttribute("students", s_s.findAll());
            } else {
                model.addAttribute("students", s_s.search(surname, name, patronymic));
            }

            return "students";
        } catch (Exception e) {
            model.addAttribute("error_msg", e);
            return "error";
        }
    }

    @GetMapping(value = {"/student"})
    public String viewStudent(@RequestParam("id") Integer id, Model model) {
        try {
            Student s = s_s.findById(id);
            model.addAttribute("student", s);
            return "student";
        } catch (Exception e) {
            model.addAttribute("error_msg", e);
            return "error";
        }
    }

    @GetMapping(value = {"/show_student_schedule"})
    public String viewStudentSchedule(@RequestParam("id") Integer id,
                                      @RequestParam("startDate") String startDate,
                                      @RequestParam("endDate") String endDate, Model model) {
        try {
            Student s = s_s.findById(id);
            LocalDateTime start = LocalDate.parse(startDate).atStartOfDay();
            LocalDateTime end = LocalDate.parse(endDate).atStartOfDay();

            if (start.isAfter(end)) {
                model.addAttribute("error_msg", "Неправильно задан временной промежуток!");
                return "error";
            }

            List<Lesson> ll = s_s.getSchedule(s, start, end);

            model.addAttribute("student", s);
            model.addAttribute("lessons", ll);
            return "show_student_schedule";
        } catch (Exception e) {
            model.addAttribute("error_msg", e);
            return "error";
        }
    }

    @GetMapping("/add_student")
    public String showAddStudentPage(Model model) {
        try {
            return "add_student";
        } catch (Exception e) {
            model.addAttribute("error_msg", e);
            return "error";
        }
    }

    @PostMapping("/save_student")
    public String saveStudent(@RequestParam("surname") String surname,
                              @RequestParam("name") String name,
                              @RequestParam("patronymic") String patronymic,
                              @RequestParam("email") String email,
                              @RequestParam("phoneNumber") String phoneNumber, Model model) {
        try {
            Student s = new Student(surname, name, patronymic, email, phoneNumber);
            s_s.save(s);

            return "redirect:/students";
        } catch (Exception e) {
            model.addAttribute("error_msg", e);
            return "error";
        }
    }

    @GetMapping(value = {"/edit_student"})
    public String editStudent(@RequestParam("id") Integer id, Model model) {
        try {
            model.addAttribute("student", s_s.findById(id));
            return "edit_student";
        } catch (Exception e) {
            model.addAttribute("error_msg", e);
            return "error";
        }
    }

    @PostMapping(value = {"/update_student"})
    public String updateStudent(@RequestParam(value = "id", required = true) Integer id,
                                @RequestParam(value = "surname", required = true) String surname,
                                @RequestParam(value = "name", required = true) String name,
                                @RequestParam(value = "patronymic", required = true) String patronymic,
                                @RequestParam(value = "email", required = true) String email,
                                @RequestParam(value = "phoneNumber", required = true) String phoneNumber, Model model) {
        try {
            Student s = s_s.findById(id);

            s.setSurname(surname);
            s.setName(name);
            s.setPatronymic(patronymic);
            s.setEmail(email);
            s.setPhone_number(phoneNumber);
            s_s.update(s);

            return "redirect:/student?id=" + id.toString();
        } catch (Exception e) {
            model.addAttribute("error_msg", e);
            return "error";
        }
    }

    @PostMapping(value = {"/delete_student"})
    public String deleteStudent(@RequestParam("id") Integer id, Model model) {
        try {
            s_s.deleteById(id);
            return "redirect:/students";
        } catch (Exception e) {
            model.addAttribute("error_msg", e);
            return "error";
        }
    }
}
