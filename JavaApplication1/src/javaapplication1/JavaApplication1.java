/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import entity.Gruppyi;
import entity.Studentyi;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.NewHibernateUtil;

/**
 *
 * @author 20517
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SessionFactory sf=NewHibernateUtil.getSessionFactory();
        Session s=sf.openSession();
        
        Transaction t1=s.beginTransaction();
        List <Studentyi> q = s.createQuery("from Studentyi s").list();
        
        for(Studentyi u: q)
        {
            System.out.println(u.getImya()+' '+ u.getAdres()+' '+u.getTel());
            
            u.setStatusDate(new Date());
            s.saveOrUpdate(u);   
        }
        s.flush();
        t1.commit();
        
       Transaction t2=s.beginTransaction();
        Query grQuery=s.createQuery("from Gruppyi g where g.nazvanie=:name");
        grQuery.setParameter("name", "It-1");
        Gruppyi gr=(Gruppyi) grQuery.list().get(0);
        
        Studentyi d= new Studentyi(11111, gr, "Egor", "Tolkach","Egorovich", "Chashniky", "M 2-3-22", "+375333479430", "created" ,new Date());
        s.persist(d);
        t2.commit();
        s.close();
        sf.close();
        
        
    }
    
}
