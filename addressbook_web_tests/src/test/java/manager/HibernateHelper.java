package manager;

import manager.hbm.ContactRecord;
import manager.hbm.GroupRecord;
import model.ContactData;
import model.GroupData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HibernateHelper extends HelperBase {

    private SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManager manager){

        super(manager);

        sessionFactory = new Configuration()
                //.addAnnotatedClass(Book.class)
                .addAnnotatedClass(GroupRecord.class)
                .addAnnotatedClass(ContactRecord.class)
//                .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook")
                .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=convertToNull&useSSL=false")
                .setProperty(AvailableSettings.USER, "root")
                .setProperty(AvailableSettings.PASS, "")
                // Create a new SessionFactory
                .buildSessionFactory();
    }

//    static List<GroupData> convertList(List<GroupRecord> records) {
//        List<GroupData> result = new ArrayList<>();
//        for (var record : records) {
//            result.add(convert(record));
//        }
//        return result;
//    }

    static List<GroupData> convertList(List<GroupRecord> records) {
        return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());
    }

    private static GroupData convert(GroupRecord record) {
        return new GroupData("" + record.id, record.name, record.header, record.footer);
    }

    private static GroupRecord convert(GroupData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new GroupRecord(Integer.parseInt(id), data.name(), data.header(), data.footer());
    }


    public List<GroupData> getGroupList() {
        return convertList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord", GroupRecord.class).list();
        }));
    }

    public long getGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from GroupRecord", Long.class).getSingleResult();
        });
    }

    public void createGroup(GroupData groupData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(groupData));
            session.getTransaction().commit();
        });
    }


    // Контакты

//    static List<ContactData> convertListContacts(List<ContactRecord> records) {
//        List<ContactData> result = new ArrayList<>();
//        for (var record : records) {
//            result.add(convertContact(record)
//            );
//        }
//        return result;
//    }

    static List<ContactData> convertListContacts(List<ContactRecord> records) {
        return records.stream().map(HibernateHelper::convertContact).collect(Collectors.toList());
    }

    private static ContactData convertContact(ContactRecord record) {
        return new ContactData()
                .withIdNames(
                        "" + record.id,
                        record.firstname,
                        record.lastname
                )
                .withHome(record.home)
                .withMobile(record.mobile)
                .withWork(record.work);
    }

    private static ContactRecord convertContact(ContactData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new ContactRecord(
                        Integer.parseInt(id),
                        data.firstname(),
                        data.lastname()
                );
    }

    public List<ContactData> getContactList() {
        return convertListContacts(sessionFactory.fromSession(session -> {
            return session.createQuery("from ContactRecord", ContactRecord.class).list();
        }));
    }


    public long getContactCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from ContactRecord", Long.class).getSingleResult();
        });
    }

    public void createContact(ContactData contactData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convertContact(contactData));
            session.getTransaction().commit();
        });
    }

    public List<ContactData> getContactsInGroup(GroupData group) {
        return sessionFactory.fromSession(session -> {
            return convertListContacts(session.get(GroupRecord.class, group.id()).contacts);
        });
    }
}
