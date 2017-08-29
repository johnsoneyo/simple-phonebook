/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtechos.simple.phonebook;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jtechos.simple.phonebook.model.Person;
import com.jtechos.simple.phonebook.repo.ContactRepository;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author johnson3yo
 */
@Controller
public class ContactController {

    @Autowired
    ContactRepository crepo;

    @PostMapping("/saveContact")
    public @ResponseBody
    ResponseEntity<?> saveContact(@RequestPart(value = "person") String person, @RequestPart(value = "file") MultipartFile file) throws Exception {

        ObjectMapper m = new ObjectMapper();
        Person p = m.readValue(person, Person.class);
        p.setId(String.valueOf(System.currentTimeMillis()));

        try {
             Person pe = crepo.saveContact(p,file);
              return new ResponseEntity<Person>(pe, HttpStatus.OK);
        } catch (Exception ex) {
            Logger.getLogger(ContactController.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/getContacts")
    public @ResponseBody
    ResponseEntity getContacts() throws Exception {

        List<Person> clist = crepo.getPersons();
        return new ResponseEntity<List<Person>>(clist, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/getContactsByPhone")
    public @ResponseBody
    ResponseEntity getContactsByPhone(@RequestParam("phone") String phone) throws Exception {

        List<Person> clist = crepo.findContactsByPhone(phone);
        return new ResponseEntity<List<Person>>(clist, HttpStatus.OK);

    }
    
    @RequestMapping(value="/getContactPhoto",method = RequestMethod.GET,produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getPhoto(@RequestParam("contactid")String id){
     
        return   crepo.getContactPhoto(id);
        
    }

}
