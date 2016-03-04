package com.apress.prospring3.ch8;

import com.apress.prospring3.ch8.domain.Contact;
import com.apress.prospring3.ch8.domain.ContactTelDetail;
import org.springframework.context.support.GenericXmlApplicationContext;
import com.apress.prospring3.ch8.dao.ContactDao;

import java.util.List;

public class JdbcContactDaoSample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:app-context-xml.xml");
        ctx.refresh();
        ContactDao contactDao = ctx.getBean("contactDao", ContactDao.class);
// Найти имя по идентификатору.
        System.out.println("First name for contact id 1 is: " + contactDao.findFirstNameById(1l));
        System.out.println("Last name for contact id 1 is: " + contactDao.findLastNameById(1l));


        List<Contact> contacts = contactDao.findAll();
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