package com.mkdika.zkmvvmcrud8.service;

import com.mkdika.zkmvvmcrud8.model.TbPerson;
import com.mkdika.zkmvvmcrud8.service.template.GenericServiceImpl;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Maikel Chandika <mkdika@gmail.com>
 */
@Service("appService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class AppServiceImpl extends GenericServiceImpl implements AppService {

    @Autowired
    private SessionFactory sessionFactory;
   
    public final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<TbPerson> getTbPersonByGender(Integer gender) {
        List<TbPerson> list = getCurrentSession().createQuery("FROM TbPerson a WHERE a.gender = :gender")
                .setParameter("gender", gender)
                .list();
        return list;
    }

    @Override
    public List<TbPerson> searchTbPerson(String key) {
        List<TbPerson> list = getCurrentSession().createQuery("FROM TbPerson a "
                + "WHERE LOWER(a.firstname) LIKE LOWER(:key) "
                + "OR LOWER(a.lastname) LIKE LOWER(:key) "
                + "OR LOWER(a.email) LIKE LOWER(:key) ORDER BY a.firstname")
                .setParameter("key", "%"+key+"%")
                .list();
        return list;
    }
}
