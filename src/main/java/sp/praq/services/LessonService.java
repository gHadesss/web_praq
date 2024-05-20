package sp.praq.services;

import sp.praq.models.*;
import sp.praq.DAO.LessonDAO;

import java.time.LocalDateTime;
import java.util.*;

public class LessonService extends M2MCommonService<Lesson, LessonDAO> {
    public LessonService() { super(new LessonDAO()); }

    public Lesson findByObj(Group group, LocalDateTime class_dt, Integer room, Double duration) throws Exception {
        return dao.findByObj(group, class_dt, room, duration);
    }

    public void deleteByObj(Group group, LocalDateTime class_dt, Integer room, Double duration) throws Exception {
        dao.deleteByObj(group, class_dt, room, duration);
    }
}
