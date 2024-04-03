package sp.praq.services;

import sp.praq.models.*;
import sp.praq.DAO.CompanyDAO;
import java.util.*;

public class CompanyService extends CommonService<Company, CompanyDAO> {
    public CompanyService() { super(new CompanyDAO()); }
}
