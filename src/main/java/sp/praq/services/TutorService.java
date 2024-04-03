package sp.praq.services;

import sp.praq.models.*;
import sp.praq.DAO.TutorDAO;

import java.time.LocalDateTime;
import java.util.*;

public class TutorService extends CommonService<Tutor, TutorDAO> {
    public TutorService() { super(new TutorDAO()); }

    public List<Tutor> listByCompany() { return dao.listByCompany(); }

    public List<Tutor> search(String surname, String name, String patronymic) {
        return dao.search(surname, name, patronymic);
    }

    public List<Lesson> getSchedule(Tutor tutor, LocalDateTime start, LocalDateTime end) {
        return dao.getSchedule(tutor, start, end);
    }
}
