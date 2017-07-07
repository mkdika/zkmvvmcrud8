package com.mkdika.zkmvvmcrud8.service.template;

import java.io.Serializable;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Maikel Chandika <mkdika@gmail.com>
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class GenericServiceImpl implements GenericService {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean save(Object obj) {
        getSessionFactory().getCurrentSession().save(obj);
        return true;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean update(Object obj) {
        getSessionFactory().getCurrentSession().update(obj);
        return true;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean saveOrUpdate(Object obj) {
        getSessionFactory().getCurrentSession().saveOrUpdate(obj);
        return true;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean delete(Object obj) {
        getSessionFactory().getCurrentSession().delete(obj);
        return true;
    }

    @Override
    public List selectNativeQuery(String query) {
        return getSessionFactory().getCurrentSession().createSQLQuery(query).list();
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void executeNativeQuery(String query) {
        getSessionFactory().getCurrentSession().createSQLQuery(query).executeUpdate();
    }

    @Override
    public List findAll(Class type) throws Exception {
        return findAll(type, "");
    }

    @Override
    public List findAll(Class type, String order) throws Exception {
        if (type != null && !type.getSimpleName().isEmpty()) {
            StringBuilder sql = new StringBuilder();
            sql.append("from ");
            sql.append(type.getSimpleName());
            if (order != null && !order.isEmpty()) {
                sql.append(" order by ");
                sql.append(order);
            }
            List list = getSessionFactory().getCurrentSession().createQuery(sql.toString()).list();
            return list;
        } else {
            return null;
        }
    }

    @Override
    public Object findById(Class type, Serializable srlzbl) {
        return getSessionFactory().getCurrentSession().get(type, srlzbl);
    }
}
