package com.digitalrepository.domain.schemaorg;

/**
 * Created by Piotrek on 10.05.2017.
 */
public class SchemaOrgPerson {

    private String givenName;
    private String additionalName;
    private String familyName;

    public SchemaOrgPerson() {
    }

    public SchemaOrgPerson(String givenName, String additionalName, String familyName) {
        this.givenName = givenName;
        this.additionalName = additionalName;
        this.familyName = familyName;
    }

    @Override
    public String toString() {
        return "{" +
            "\n\"givenName\":\"" + givenName + "\"" +
            ",\n\"additionalName\":\"" + additionalName + "\"" +
            ",\n\"familyName\":\"" + familyName + "\"" +
            "\n}";
    }

    public String getGivenName() { return givenName; }

    public String getAdditionalName() {
        return additionalName;
    }

    public String getFamilyName() {
        return familyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SchemaOrgPerson that = (SchemaOrgPerson) o;

        if (givenName != null ? !givenName.equals(that.givenName) : that.givenName != null) return false;
        if (additionalName != null ? !additionalName.equals(that.additionalName) : that.additionalName != null)
            return false;
        return familyName != null ? familyName.equals(that.familyName) : that.familyName == null;
    }
}
