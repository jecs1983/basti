package org.test.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.infinispan.protostream.annotations.ProtoDoc;
import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;

@ProtoDoc("@Indexed")
public class Person {
    
    
    private String name;
    private String lastName;
    private Date dob;
    private List<Pet> pets;

   
    @ProtoFactory
    public Person(String name, String lastName, Date dob, List<Pet> pets) {
        this.name = name;
        this.lastName = lastName;
        this.dob = dob;
        this.pets = pets;
    }

    @ProtoField(number = 1)
    @ProtoDoc("@Field(analyze = Analyze.YES, store = Store.YES)")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    @ProtoField(number = 2)
    @ProtoDoc("@Field(analyze = Analyze.YES, store = Store.YES)")
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @ProtoField(number = 3)
    @ProtoDoc("@Field(analyze = Analyze.YES, store = Store.YES)")
    public Date getDob() {
        return dob;
    }
    public void setDob(Date dob) {
        this.dob = dob;
    }

    @ProtoField(number = 4, collectionImplementation = LinkedList.class)
    @ProtoDoc("@Field(analyze = Analyze.YES, store = Store.YES)")
    public List<Pet> getPets() {
        return pets;
    }
    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

}
