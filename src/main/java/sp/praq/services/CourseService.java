package sp.praq.services;

import sp.praq.models.*;
import sp.praq.DAO.CourseDAO;
import java.util.List;

public class CourseService extends CommonService<Course, CourseDAO> {
    public CourseService() { super(new CourseDAO()); }

    public List<Course> listByCompany() { return dao.listByCompany(); }

    public List<Course> listByTitle() { return dao.listByTitle(); }
}
