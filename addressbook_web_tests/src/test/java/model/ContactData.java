package model;

public record ContactData (String firstname, String middlename, String lastname, String nickname, String title, String company, String address){

    public ContactData(){
        this("", "", "", "", "", "", "");
    }

    public ContactData withNames(String firstname, String middlename, String lastname) {
        return new ContactData(firstname,middlename, lastname, this.nickname, this.title, this.company, this.title);
    }
}
