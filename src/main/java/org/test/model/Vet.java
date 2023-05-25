package org.test.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.infinispan.protostream.annotations.ProtoDoc;
import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;

@ProtoDoc("@Indexed")
public class Vet extends Person{

   
    private List<Pet> patients;


    @ProtoFactory
    public Vet(String name, String lastName, Date dob, List<Pet> pets, List<Pet> patients) {
        super(name, lastName, dob, pets);
        this.patients = patients;
    }

    @ProtoField(number = 5, collectionImplementation = LinkedList.class)
    @ProtoDoc("@Field(analyze = Analyze.YES, store = Store.YES)")
    public List<Pet> getPatients() {
        return patients;
    }

    public void setPatients(List<Pet> patients) {
        this.patients = patients;
    }
}
