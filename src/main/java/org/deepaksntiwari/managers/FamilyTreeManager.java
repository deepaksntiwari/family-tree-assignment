package org.deepaksntiwari.managers;

import org.deepaksntiwari.models.Person;
import org.deepaksntiwari.models.Relationship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FamilyTreeManager {
    private Map<Person, List<Relationship>> relationships;

    public FamilyTreeManager() {
        this.relationships = new HashMap<>();
    }

    public void addPerson(Person person) {
        if (!relationships.containsKey(person)) {
            relationships.put(person, new ArrayList<>());
        }
    }

    public void addRelationship(String relationshipType) {
System.out.println("Added relationship");  
    }

    public void connect(String personName1, String relationship, String personName2) {
        Person person1 = findPersonByName(personName1);
        Person person2 = findPersonByName(personName2);

        if (person1 == null || person2 == null) {
            System.out.println("One or both persons not found.");
            return;
        }

        Relationship relation1 = new Relationship(person2, relationship);
        relationships.get(person1).add(relation1);

        if (isSymmetricRelationship(relationship)) {
            Relationship relation2 = new Relationship(person1, getReverseRelationshipType(relationship));
            relationships.get(person2).add(relation2);
        }
    }

    public int countSons(Person person) {
        return countRelatives(person, "son");
    }

    public int countDaughters(Person person) {
        return countRelatives(person, "daughter");
    }

    public int countWives(Person person) {
        return countRelatives(person, "wife");
    }

    public String fatherOf(Person person) {
        List<Relationship> personRelationships = relationships.get(person);
        for (Relationship relation : personRelationships) {
            if (relation.getRelationshipType().equals("son")) {
                return relation.getRelatedPerson().getName();
            }
        }
        return "Unknown";
    }

    private Person findPersonByName(String name) {
        for (Person person : relationships.keySet()) {
            if (person.getName().equalsIgnoreCase(name)) {
                return person;
            }
        }
        return null;
    }

    private int countRelatives(Person person, String relationshipType) {
        int count = 0;
        List<Relationship> personRelationships=new ArrayList<>();
        for (Map.Entry<Person,List<Relationship>> entry : relationships.entrySet()) {
            if(entry.getKey().getName().equalsIgnoreCase(person.getName()))
                personRelationships = entry.getValue();
        }


        for (Relationship relation : personRelationships) {
            if (relation.getRelationshipType().equals(relationshipType)) {
                count++;
            }
        }
        return count;
    }

    private boolean isSymmetricRelationship(String relationshipType) {
        return relationshipType.equals("father") || relationshipType.equals("mother") ||
                relationshipType.equals("son") || relationshipType.equals("daughter") ||
                relationshipType.equals("husband") || relationshipType.equals("wife");
    }

    private String getReverseRelationshipType(String relationshipType) {
        switch (relationshipType) {
            case "father":
                return "son";
            case "mother":
                return "daughter";
            case "son":
                return "father";
            case "daughter":
                return "mother";
            case "husband":
                return "wife";
            case "wife":
                return "husband";
            default:
                return null;
        }
    }
}
