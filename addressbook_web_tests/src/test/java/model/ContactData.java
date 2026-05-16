package model;

public record ContactData (String id, String firstname, String middlename, String lastname, String nickname, String title, String company, String address){

    public ContactData(){
        this("", "", "", "", "", "", "", "");
    }

    public ContactData withNames(String firstname, String lastname) {
        return new ContactData(
                this.id,
                firstname,
                this.middlename,
                lastname,
                this.nickname,
                this.title,
                this.company,
                this.address
        );
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstname, this.middlename, lastname, this.nickname, this.title, this.company, this.title);
    }

}
