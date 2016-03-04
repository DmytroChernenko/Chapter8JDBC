package com.apress.prospring3.ch8;

import org.springframework.context.support.GenericXmlApplicationContext;
import com.apress.prospring3.ch8.dao.ContactDao;

public class JdbcContactDaoSample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:app-context-xml.xml");
        ctx.refresh();
        ContactDao contactDao = ctx.getBean("contactDao", ContactDao.class);
// Найти имя по идентификатору.
        System.out.println("First name for contact id 1 is: " + contactDao.findFirstNameById(1l));
    }
}