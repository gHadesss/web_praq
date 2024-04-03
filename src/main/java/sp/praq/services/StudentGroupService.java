package sp.praq.services;

import sp.praq.models.*;
import sp.praq.DAO.StudentGroupDAO;


public class StudentGroupService extends M2MCommonService<StudentGroup, StudentGroupDAO> {
    public StudentGroupService() { super(new StudentGroupDAO()); }
}
