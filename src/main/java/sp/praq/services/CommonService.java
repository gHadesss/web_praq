package sp.praq.services;

import sp.praq.models.*;
import sp.praq.DAO.CommonDAO;
import java.util.*;

public abstract class CommonService<T, DAO extends CommonDAO<T>> {
    protected DAO dao;

    public CommonService(DAO dao) { this.dao = dao; }

    public T findById(Integer id) { return dao.findById(id); }

    public List<T> findAll() { return dao.findAll(); }

    public void save(T obj) { dao.save(obj); }

    public void update(T obj) { dao.update(obj); }

    public void delete(T obj) { dao.delete(obj); }

    public void deleteById(Integer id) { dao.deleteById(id); }
}
