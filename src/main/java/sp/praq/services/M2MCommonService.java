package sp.praq.services;

import sp.praq.models.*;
import sp.praq.DAO.M2MCommonDAO;
import java.util.*;

public abstract class M2MCommonService<T, DAO extends M2MCommonDAO<T>> {
    protected DAO dao;

    public M2MCommonService(DAO dao) { this.dao = dao; }

    public List<T> findAll() { return dao.findAll(); }

    public void update(T obj) { dao.update(obj); }

    public void delete(T obj) { dao.delete(obj); }

}
