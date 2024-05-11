package sp.praq.services;

import sp.praq.models.*;
import sp.praq.DAO.GroupDAO;
import java.util.*;

public class GroupService extends CommonService<Group, GroupDAO> {
    public GroupService() { super(new GroupDAO()); }
}
