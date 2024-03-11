package org.deepaksntiwari.models;

public class Relationship {
    private Person relatedPerson;
    private String relationshipType;

    public Relationship(Person relatedPerson, String relationshipType) {
        this.relatedPerson = relatedPerson;
        this.relationshipType = relationshipType;
    }

    public Person getRelatedPerson() {
        return relatedPerson;
    }

    public void setRelatedPerson(Person relatedPerson) {
        this.relatedPerson = relatedPerson;
    }

    public String getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(String relationshipType) {
        this.relationshipType = relationshipType;
    }
}
