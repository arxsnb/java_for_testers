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
        String work
){

    public ContactData(){
        this("", "", "", "", "", "", "", "", "", "", "", "");
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
                this.work
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
                this.work
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
                this.address,
                this.photo,
                this.home,
                this.mobile,
                this.work
        );
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
                this.work
        );
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
                this.work
        );
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
                this.work
        );
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
                work
        );
    }

    public ContactData withSecondary(String secondary) {
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
                this.work
        );
    }

}
