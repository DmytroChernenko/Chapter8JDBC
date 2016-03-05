package com.apress.prospring3.ch8.dao.jdbc.annotation;

import javax.annotation.Resource;
import javax.sql.DataSource;

import com.apress.prospring3.ch8.domain.Contact;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import com.apress.prospring3.ch8.dao.ContactDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("contactDao")
public class JdbcContactDao implements ContactDao {

    private Log log = LogFactory.getLog(JdbcContactDao.class);
    private DataSource dataSource;
    private SelectAllContacts selectAllContacts;
    private SelectContactByFirstName selectContactByFirstName;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        selectAllContacts = new SelectAllContacts(dataSource);
        selectContactByFirstName = new SelectContactByFirstName(dataSource);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public List<Contact> findAll() {
        return selectAllContacts.execute();
    }

    public List<Contact> findAllWithDetail() {
        return null;
    }

    public List<Contact> findByFirstName(String firstName) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("first_name", firstName);
        return selectContactByFirstName.executeByNamedParam(paramMap);
    }

    public String findFirstNameById(Long id) {
        return null;
    }

    public String findLastNameById(Long id) {
        return null;
    }

    public void insert(Contact contact) {

    }

    public void update(Contact contact) {

    }

    public void delete(Long contactId) {

    }
}