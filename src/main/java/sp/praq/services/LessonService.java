package sp.praq.services;

import sp.praq.models.*;
import sp.praq.DAO.LessonDAO;
import java.util.*;

public class LessonService extends M2MCommonService<Lesson, LessonDAO> {
    public LessonService() { super(new LessonDAO()); }
}
