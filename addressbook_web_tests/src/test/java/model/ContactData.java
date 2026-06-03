package model;

public record ContactData (
        String id,
        String firstname,
        String middlename,
        String lastname,
        String nickname,
        String title,
        String company,
        String address,
        String photo,
        String home,
        String mobile,
        String work,
        String email,
        String email2,
        String email3){

    public ContactData(){
        this("", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
    }

    public ContactData withIdNames(String id, String firstname, String lastname) {
        return new ContactData(
                id,
                firstname,
                this.middlename,
                lastname,
                this.nickname,
                this.title,
                this.company,
                this.address,
                this.photo,
                this.home,
                this.mobile,
                this.work,
                this.email,
                this.email2,
                this.email3
                );
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
                this.photo,
                this.home,
                this.mobile,
                this.work,
                this.email,
                this.email2,
                this.email3);
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
                this.address,
                this.photo,
                this.home,
                this.mobile,
                this.work,
                this.email,
                this.email2,
                this.email3);
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
                this.address,
                photo,
                this.home,
                this.mobile,
                this.work,
                this.email,
                this.email2,
                this.email3);
    }


    public ContactData withHome(String home) {
        return new ContactData(
                this.id,
                this.firstname,
                this.middlename,
                this.lastname,
                this.nickname,
                this.title,
                this.company,
                this.address,
                this.photo,
                home,
                this.mobile,
                this.work,
                this.email,
                this.email2,
                this.email3);
    }

    public ContactData withMobile(String mobile) {
        return new ContactData(
                this.id,
                this.firstname,
                this.middlename,
                this.lastname,
                this.nickname,
                this.title,
                this.company,
                this.address,
                this.photo,
                this.home,
                mobile,
                this.work,
                this.email,
                this.email2,
                this.email3);
    }

    public ContactData withWork(String work) {
        return new ContactData(
                this.id,
                this.firstname,
                this.middlename,
                this.lastname,
                this.nickname,
                this.title,
                this.company,
                this.address,
                this.photo,
                this.home,
                this.mobile,
                work,
                this.email,
                this.email2,
                this.email3);
    }

    public ContactData withEmail(String email) {
        return new ContactData(
                this.id,
                this.firstname,
                this.middlename,
                this.lastname,
                this.nickname,
                this.title,
                this.company,
                this.address,
                this.photo,
                this.home,
                this.mobile,
                this.work,
                email,
                this.email2,
                this.email3);
    }

    public ContactData withEmail2(String email2) {
        return new ContactData(
                this.id,
                this.firstname,
                this.middlename,
                this.lastname,
                this.nickname,
                this.title,
                this.company,
                this.address,
                this.photo,
                this.home,
                this.mobile,
                this.work,
                this.email,
                email2,
                this.email3);
    }

    public ContactData withEmail3(String email3) {
        return new ContactData(
                this.id,
                this.firstname,
                this.middlename,
                this.lastname,
                this.nickname,
                this.title,
                this.company,
                this.address,
                this.photo,
                this.home,
                this.mobile,
                this.work,
                this.email,
                this.email2,
                email3);
    }

}
