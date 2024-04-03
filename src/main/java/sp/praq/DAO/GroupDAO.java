package sp.praq.DAO;

import sp.praq.models.*;
import java.util.*;

public class GroupDAO extends CommonDAO<Group> {
    public GroupDAO() { super(Group.class); }

    public List<Student> listStudents(Group group) {
        List<Student> res = new ArrayList<Student>();
        group.getStudents_of_group().forEach(gs -> res.add(gs.getStudent_id()));
        return res;
    }
}
