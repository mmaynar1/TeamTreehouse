package com.teamtreehouse.contactmgr;

import com.teamtreehouse.contactmgr.model.Contact;
import com.teamtreehouse.contactmgr.model.Contact.ContactBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class Application
{
    //Hold a reusable reference to a SessionFactory (since we need only one)
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory()
    {
        //Create a StandardServiceRegistry
        final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public static void main(String[] args)
    {
        Contact contact = new ContactBuilder("Mitch", "Maynard")
                .withEmail("mmaynar1@gmail.com")
                .withPhone(8675309L)
                .build();
        int id = save(contact);

        System.out.println("Before update");
        fetchAllContacts().forEach(System.out::println);

        Contact c = findContactByid(id);
        c.setFirstName("Bob");
        update(c);

        System.out.println("After update");
        fetchAllContacts().forEach(System.out::println);
    }

    private static Contact findContactByid(int id)
    {
        Session session = sessionFactory.openSession();
        Contact contact = session.get( Contact.class, id );
        session.close();
        return contact;
    }

    private static void update(Contact contact)
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(contact);
        session.getTransaction().commit();
        session.close();
    }

    private static List<Contact> fetchAllContacts()
    {
        Session session = sessionFactory.openSession();

        // DEPRECATED: Create Criteria
        // Criteria criteria = session.createCriteria(Contact.class);

        // DEPRECATED: Get a list of Contact objects according to the Criteria object
        // List<Contact> contacts = criteria.list();

        // UPDATED: Create CriteriaBuilder
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // UPDATED: Create CriteriaQuery
        CriteriaQuery<Contact> criteria = builder.createQuery(Contact.class);

        // UPDATED: Specify criteria root
        criteria.from(Contact.class);

        // UPDATED: Execute query
        List<Contact> contacts = session.createQuery(criteria).getResultList();

        // Close the session
        session.close();

        return contacts;
    }

    private static int save(Contact contact)
    {
        //Open a session
        Session session = sessionFactory.openSession();

        //Begin a transaction
        session.beginTransaction();

        //Use the session to save the contact
        int id = (int)session.save(contact);

        //Commit the transaction
        session.getTransaction().commit();

        //Close the session
        session.close();
        return id;
    }
}
