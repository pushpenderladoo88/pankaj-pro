package net.pankaj.controller;

import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.classic.Session;
 
import net.pankaj.model.Contact;
import net.pankaj.util.HibernateUtil;
 
public class ContactManager extends HibernateUtil {
 
    public Contact add(Contact contact){
        Session session =HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(contact);
        session.getTransaction().commit();
        return contact;
    }
    public Contact delete(Long id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Contact contact = (Contact) session.load(Contact.class, id);
        if(null != contact) {
            session.delete(contact);
        }
        session.getTransaction().commit();
        return contact;
    }
    
     public Contact update(Contact contact)
    {
      Session session=HibernateUtil.getSessionFactory().getCurrentSession();	
      session.beginTransaction().begin();
      Contact contact1=(Contact)session.get(Contact.class, contact.getId());
      if(contact1!=null)
      {
    	contact1.setId(contact.getId());
        contact1.setFirstName(contact.getFirstName());
        contact1.setLastName(contact.getLastName());
        contact1.setEmailId(contact.getEmailId());
        contact1.setCellNo(contact.getCellNo());
        contact1.setBirthDate(contact.getBirthDate());
        session.update(contact1);
      }
      session.beginTransaction().commit();
      return contact1;
      
    }
  
 
    public List<Contact> list() {
         
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Contact> contacts = null;
        try {
             
            contacts = (List<Contact>)session.createQuery("from Contact").list();
             
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        return contacts;
    }
}