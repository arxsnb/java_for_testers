package model;

public record ContactData (String firstname, String middlename, String lastname, String nickname){

    public ContactData(){
        this("", "", "", "");
    }

    public ContactData withNames(String firstname, String middlename, String lastname) {
        return new ContactData(firstname,middlename, lastname, this.nickname);
    }
}
