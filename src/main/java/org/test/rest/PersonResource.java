package org.test.rest;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.test.helpers.PersonGenerator;
import org.test.model.Person;

import io.quarkus.infinispan.client.Remote;

@Path("/person")
public class PersonResource {
    
    // Since the io.quarkus.infinispan.client.Remote annotation apparently does not perform variable substitution,
    // it is necessary to set the cache name at compile time.
    public static final String CACHE_NAME = "people";

    //This is not really used in this example. It could be used to get the RemoteCache from name at runtime
    @Inject
    protected RemoteCacheManager remoteCacheManager;

    @Inject
    @Remote(PersonResource.CACHE_NAME)
    protected RemoteCache<String, Person> remoteCache;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getPeople() {
        List<Person> people = new LinkedList<Person>();

        for (Person p : this.remoteCache.values()){
            people.add(p);
        }

        return people;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Person addPerson(Person newPerson){
        System.out.println("Ok, at least we're running. Person: " + newPerson);
        System.out.println("Remote cache: " + this.remoteCache.getName());
        this.remoteCache.put(newPerson.getName() + " " + newPerson.getLastName(), newPerson);
        return newPerson;
    }


    @POST
    @Path("init")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Person> initPeopleCache(@QueryParam("numberOfPeople") Integer numberOfPeople,
                                        @QueryParam("dryRun") Boolean dryRun){
        List<Person> people;
        
        numberOfPeople = numberOfPeople == null ? 1000: numberOfPeople;

        try{
            people = PersonGenerator.generatePeople(numberOfPeople);
        }catch(Exception exc){
            throw new RuntimeException(exc);
        }

        if(dryRun == null || !dryRun){
            this.remoteCache.clear();

            for(Person p : people){
                this.remoteCache.put(p.getName() + " " + p.getLastName(), p);
            }
        }

        return people;
    }
}