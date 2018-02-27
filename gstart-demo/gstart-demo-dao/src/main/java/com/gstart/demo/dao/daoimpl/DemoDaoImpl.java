package com.gstart.demo.dao.daoimpl;

import com.gstart.demo.dao.dao.DemoDao;
import com.gstart.demo.dao.pojo.Demo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DemoDaoImpl implements DemoDao {
    public void saveDemo(Demo demo) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session session = sf.openSession();
        Transaction tr = session.beginTransaction();
        session.save(demo);
        tr.commit();
        session.close();
    }

    public static void main(String[] args) {
        DemoDao dao = new DemoDaoImpl();
        Demo demo = new Demo();
        demo.setMainId("aaa");
        demo.setName("测试");
        demo.setPassword("123");
        dao.saveDemo(demo);
    }
}
