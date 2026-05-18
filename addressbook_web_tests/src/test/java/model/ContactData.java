package model;

public record ContactData (String id, String firstname, String middlename, String lastname, String nickname, String title, String company, String address, String photo){

    public ContactData(){
        this("", "", "", "", "", "", "", "", "");
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
                this.address,
                this.photo
        );
    }

    public ContactData withId(String id) {
        return new ContactData(
                id,
                this.firstname,
                this.middlename,
                this.lastname,
                this.nickname,
                this.title,
                this.company,
                this.title,
                this.photo);
    }
    public ContactData withPhoto(String photo) {
        return new ContactData(
                this.id,
                this.firstname,
                this.middlename,
                this.lastname,
                this.nickname,
                this.title,
                this.company,
                this.title,
                photo);
    }

}
