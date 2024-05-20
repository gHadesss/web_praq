package sp.praq.services;

import sp.praq.models.*;
import sp.praq.DAO.StudentGroupDAO;


public class StudentGroupService extends M2MCommonService<StudentGroup, StudentGroupDAO> {
    public StudentGroupService() { super(new StudentGroupDAO()); }

    public StudentGroup findByObj(Student student, Group group) throws Exception {
        return dao.findByObj(student, group);
    }

    public void deleteByObj(Student student, Group group) throws Exception {
        dao.deleteByObj(student, group);
    }
}
