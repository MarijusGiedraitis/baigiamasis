package database;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class NarysDao {

    public void addNarys(Narys narys) {
        Transaction trans = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trans = session.beginTransaction();
            session.save(narys);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if(trans != null) {
                trans.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteNarys(int narysId) {
        Transaction trans = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trans = session.beginTransaction();
            Narys narys = session.load(Narys.class, narysId);
            session.delete(narys);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if(trans != null) {
                trans.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateNarys(Narys narys) {
        Transaction trans = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            trans = session.beginTransaction();
            session.update(narys);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if(trans != null) {
                trans.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<Narys> getAllNariai() {
        List<Narys> nariai = new ArrayList<Narys>();
        Transaction trans = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            trans = session.beginTransaction();
            nariai = session.createQuery("FROM Narys ").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return nariai;
    }

    public List<Narys> getNarysByName(String name) {
        List<Narys> nariai = new ArrayList<Narys>();
        Transaction trans = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trans = session.beginTransaction();
            Query query = session.createQuery("FROM Narys WHERE name = :name");
            query.setParameter("name", name);
            nariai = query.list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return nariai;
    }

    public Narys getNarysById(int narysId) {
        Narys narys = null;//?
        Transaction trans = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trans = session.beginTransaction();
            Query query = session.createQuery("FROM Narys WHERE id = :id");
            query.setInteger("id", narysId);
            narys = (Narys) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return narys;
    }

}
