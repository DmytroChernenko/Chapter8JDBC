package com.apress.prospring3.ch8.dao.jdbc.xml;

import com.apress.prospring3.ch8.dao.ContactDao;
import com.apress.prospring3.ch8.domain.Contact;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Dmytro on 03.03.16.
 */
public class JdbcContactDao implements ContactDao {


    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

    }

    public void afterPropertiesSet() throws Exception {
        if (dataSource == null) {
            throw new BeanCreationException("Must set dataSource on ContactDao");
        }
    }

    public List<Contact> findAll() {
        return null;
    }

    public List<Contact> findByFirstName(String firstName) {
        return null;
    }

    public void insert(Contact contact) {

    }

    public void update(Contact contact) {

    }

    public void delete(Long contactId) {

    }

    public String findFirstNameById(long id) {
        String firstName = jdbcTemplate.queryForObject(
                "select first_name from contact where id = ?",
                new Object[]{id}, String.class);
        return firstName;
    }

    public String findLastNameById(long id) {
        String sql = "select last_name from contact where id = :contactId";
        SqlParameterSource namedParameters =
                new MapSqlParameterSource("contactId", id);
        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, String.class);
    }

}
