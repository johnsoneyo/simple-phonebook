package com.jtechos.test;



import com.jtechos.simple.phonebook.model.Person;
import com.jtechos.simple.phonebook.repo.ContactRepository;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author johnson3yo
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactUnitTest {

    @Autowired
    ContactRepository crepo;

    @Test
    public void saveContact() {
      

    }
    
    
   
}
