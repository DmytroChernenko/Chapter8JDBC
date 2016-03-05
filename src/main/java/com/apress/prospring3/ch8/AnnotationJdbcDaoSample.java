package com.apress.prospring3.ch8;

import com.apress.prospring3.ch8.dao.ContactDao;
import com.apress.prospring3.ch8.domain.Contact;
import com.apress.prospring3.ch8.domain.ContactTelDetail;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class AnnotationJdbcDaoSample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:app-context-annotation.xml");
        ctx.refresh();
        ContactDao contactDao = ctx.getBean("contactDao", ContactDao.class);

        System.out.println("------------------ Find All ------------------");

        List<Contact> contacts = contactDao.findAll();
        listContacts(contacts);

        System.out.println("------------ Find By First Name --------------");

        contacts = contactDao.findByFirstName("Clarence");
        listContacts(contacts);

        System.out.println("--------------- Update Contact ---------------");

        Contact contact;

        contact = new Contact();
        contact.setId(1l);
        contact.setFirstName("Clarence");
        contact.setLastName("Peter");
        contact.setBirthDate(
                new Date((new GregorianCalendar(1977, 10, 1)).getTime().getTime()));
        contactDao.update(contact);
        contacts = contactDao.findAll();
        listContacts(contacts);

    }

    private static void listContacts(List<Contact> contacts) {
        for (Contact contact : contacts) {
            System.out.println(contact);
            if (contact.getContactTelDetails() != null) {
                for (ContactTelDetail contactTelDetail : contact.getContactTelDetails()) {
                    System.out.println("---" + contactTelDetail);
                }
            }
            System.out.println();
        }
    }
}