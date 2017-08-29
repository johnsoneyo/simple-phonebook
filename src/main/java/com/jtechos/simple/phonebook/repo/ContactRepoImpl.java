/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtechos.simple.phonebook.repo;

import com.jtechos.simple.phonebook.model.Person;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author johnson3yo
 */
@Repository
public class ContactRepoImpl implements ContactRepository {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Person saveContact(Person p, MultipartFile f) throws Exception {

        String saveTermQuery = "insert into contact (id,fname,lname,address,phone,photo) values (?,?,?,?,?,?)";
        Object[] term = new Object[]{p.getId(), p.getFname(), p.getLname(), p.getAddress(), p.getPhone(), new SqlLobValue(f.getBytes())};
        int[] types = new int[]{Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.BLOB};
        try {
            int result = jdbc.update(saveTermQuery, term, types);
            return p;
        } catch (DataAccessException er) {
            throw new Exception(er);
        }

    }

    @Override
    public List<Person> getPersons() {

        String saveTermQuery = "select * from contact";

        List<Person> tls = new ArrayList();

        List<Map<String, Object>> rows = jdbc.queryForList(saveTermQuery);
        for (Map row : rows) {
            Person tm = new Person();
            tm.setId((String) row.get("id"));
            tm.setFname((String) row.get("fname"));
            tm.setLname((String) row.get("lname"));
            tm.setPhone((String) row.get("phone"));
            tm.setAddress((String) row.get("address"));
            tls.add(tm);
        }
        return tls;

    }

    @Override
    public List<Person> findContactsByPhone(String phone) {

        String saveTermQuery = "select * from contact where phone like ? ";
        String p = "%" + phone.trim() + "%";

        List<Person> tls = new ArrayList();

        List<Map<String, Object>> rows = jdbc.queryForList(saveTermQuery, new Object[]{p});
        for (Map row : rows) {
            Person tm = new Person();
            tm.setId(String.valueOf(row.get("id")));
            tm.setFname((String) row.get("fname"));
            tm.setLname((String) row.get("lname"));
            tm.setPhone((String) row.get("phone"));
            tm.setAddress((String) row.get("address"));
            tls.add(tm);
        }
        return tls;

    }

    @Override
    public byte[] getContactPhoto(String id) {
        String sql = "select photo from contact where id = ?";
        byte[] b = jdbc.queryForObject(sql, byte[].class, id);

        return b;
    }

}
