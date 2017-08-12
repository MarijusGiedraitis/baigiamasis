package com.narys.klase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.narys.klase.UserForma;

import database.Narys;
import database.NarysDao;

public class Main {

    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");

		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = (Transaction) session.beginTransaction();
        
		/*int id, String name, String surname, String email, String birthDate, String sex,
    		String password*/
     
        NarysDao narysDao = new NarysDao();
        //Narys naujas = new Narys(7, "Paulius", "Stankunas", "pauliusstankunas@gmail.com",
        		//"1998.09.22", "Male", "pauliusstankunas");

        //narysDao.addNarys(naujas);
        //narysDao.updateNarys(naujas);
        //narysDao.deleteNarys(6);
        
        //int i = 0;
        //for (Narys narys : narysDao.getAllNariai()) {
        	//System.out.println(++i + " " + narys.getPavadinimas());
       //}
        //for (Filmas filmas : filmasDao.getFilmasByFilmasName("Matrica")) {
        	//System.out.println(++i + " " + filmas.getPavadinimas());
        //}

       //System.out.println(filmasDao.getFilmasById(2).getPavadinimas());

        //session.persist(filmas);
        //transaction.commit();
        //session.close();

        //System.out.println("Data inserted successfully");
        
        UserForma forma = new UserForma();
}
}
