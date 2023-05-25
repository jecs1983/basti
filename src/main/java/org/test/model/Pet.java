package org.test.model;

import java.util.Date;

import org.infinispan.protostream.annotations.ProtoDoc;
import org.infinispan.protostream.annotations.ProtoField;

@ProtoDoc("@Indexed")
public class Pet {

    
    private String species;
    private String name;
    private Date dob;


    @ProtoField(number = 1)
    @ProtoDoc("@Field(analyze = Analyze.YES, store = Store.YES)")
    public String getSpecies() {
        return species;
    }
    public void setSpecies(String species) {
        this.species = species;
    }

    @ProtoField(number = 2)
    @ProtoDoc("@Field(analyze = Analyze.YES, store = Store.YES)")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @ProtoField(number = 3)
    @ProtoDoc("@Field(analyze = Analyze.YES, store = Store.YES)")
    public Date getDob() {
        return dob;
    }
    public void setDob(Date dob) {
        this.dob = dob;
    }
    
}
