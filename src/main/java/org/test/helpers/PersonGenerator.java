package org.test.helpers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.test.model.Person;
import org.test.model.Pet;

import io.netty.util.internal.ThreadLocalRandom;

public class PersonGenerator {
    
    private static final List<String> GIVEN_NAMES = PersonGenerator.makeNamesList(PersonGenerator.class.getResourceAsStream("/given-names.txt"));
    private static final List<String> LAST_NAMES = PersonGenerator.makeNamesList(PersonGenerator.class.getResourceAsStream("/last-names.txt"));
    private static final List<String> PET_NAMES = PersonGenerator.makeNamesList(PersonGenerator.class.getResourceAsStream("/pet-names.txt"));
    private static final String [] SPECIES = {"dog","cat", "bird", "hamster", "snake"};

    private static final SimpleDateFormat DOB_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static List<String> makeNamesList(InputStream file){
        List<String> names;

        try(BufferedReader br = new BufferedReader(new InputStreamReader(file));){
            names = br.lines().toList();
        }catch(Throwable t){
            throw new RuntimeException(t);
        }

        return names;
    }

    public static List<Person> generatePeople(int numberOfPeople) throws Exception{
        LinkedList<Person> people = new LinkedList<>();
        int firstNameCounter, middleNameCounter, lastNameCounter;
        
        if(numberOfPeople >  PersonGenerator.GIVEN_NAMES.size() * PersonGenerator.GIVEN_NAMES.size() * PersonGenerator.LAST_NAMES.size() )
            throw new RuntimeException("Cannot generate " + numberOfPeople + " unique people");

        firstNameCounter = middleNameCounter = lastNameCounter = 0;
        for(int p = 0; p < numberOfPeople; p++){

            people.add(new Person(PersonGenerator.GIVEN_NAMES.get(firstNameCounter) + " " + PersonGenerator.GIVEN_NAMES.get(middleNameCounter),
                                    PersonGenerator.LAST_NAMES.get(lastNameCounter), 
                                    PersonGenerator.getRandomDoB(), 
                                    PersonGenerator.generatePets())
                    );

            if(firstNameCounter == PersonGenerator.GIVEN_NAMES.size() -1){
                firstNameCounter = 0;
                if(middleNameCounter == PersonGenerator.GIVEN_NAMES.size() -1){
                    middleNameCounter = 0;
                    lastNameCounter ++;
                }else{
                    middleNameCounter ++;
                }
            }else{
                firstNameCounter ++;
            }
        }
        return people;
    }

    private static Date getRandomDoB() throws ParseException {
        Date startDate, endDate, randomDate;

        startDate = PersonGenerator.DOB_DATE_FORMAT.parse("1900-01-01");
        endDate = PersonGenerator.DOB_DATE_FORMAT.parse("2100-12-31");

        randomDate = new Date(ThreadLocalRandom.current().nextLong(startDate.getTime(), endDate.getTime()));

        return randomDate;
    }

    public static List<Pet> generatePets() throws Exception{
        List<Pet> pets = new LinkedList<>();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int numberOfPets = random.nextInt(0, 10);
        Pet tmpPet;

        for(int p = 0; p < numberOfPets; p ++){
            tmpPet = new Pet();
            tmpPet.setSpecies(PersonGenerator.SPECIES[random.nextInt(0, PersonGenerator.SPECIES.length - 1)]);
            tmpPet.setName(PersonGenerator.PET_NAMES.get(random.nextInt(0, PersonGenerator.PET_NAMES.size() - 1)));
            tmpPet.setDob(PersonGenerator.getRandomDoB());
            pets.add(tmpPet);
        }
        return pets;
    }
    
}
