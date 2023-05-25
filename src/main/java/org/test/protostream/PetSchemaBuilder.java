package org.test.protostream;

import org.infinispan.protostream.GeneratedSchema;
import org.infinispan.protostream.annotations.AutoProtoSchemaBuilder;
import org.test.model.AnimalSpecies;
import org.test.model.Person;
import org.test.model.Pet;
import org.test.model.Vet;

@AutoProtoSchemaBuilder(
    includeClasses = {
        //AnimalSpecies.class,
        Person.class,
        Pet.class,
        Vet.class
    },
    schemaFileName = "pets.proto",
    schemaFilePath = "proto/",
    schemaPackageName = "pets"
)
public interface PetSchemaBuilder extends GeneratedSchema{

}