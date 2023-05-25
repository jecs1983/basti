package org.test.model;

import org.infinispan.protostream.annotations.ProtoEnumValue;

public enum AnimalSpecies {
    @ProtoEnumValue(number = 1, name = "dog")
    dog,
    @ProtoEnumValue(number = 2, name = "cat")
    cat,
    @ProtoEnumValue(number = 3, name = "bird")
    bird,
    @ProtoEnumValue(number = 4, name = "hamster")
    hamster,
    @ProtoEnumValue(number = 5, name = "snake")
    snake;
    
}
