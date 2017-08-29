/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtechos.simple.phonebook.repo;

import com.jtechos.simple.phonebook.model.Person;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author johnson3yo
 */
@Repository
public interface ContactRepository {
    
  Person saveContact(Person p,MultipartFile file) throws Exception;
  List<Person>getPersons();
  List<Person>findContactsByPhone(String phone);
  byte[]getContactPhoto(String id);
  
}
