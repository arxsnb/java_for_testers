package ru.stqa.mantis.manager;

import jakarta.mail.*;
import ru.stqa.mantis.model.MailMessage;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

public class MailHelper extends HelperBase{
    public MailHelper(ApplicationManager manager) {
        super(manager);
    }

//    public List<MailMessage> receive(String username, String password, Duration duration) {
//        try {
//            var session = Session.getInstance(new Properties());
//            Store store = session.getStore("pop3");
//            store.connect("localhost", username, password);
//            var inbox = store.getFolder("INBOX");
//            inbox.open(Folder.READ_ONLY);
//            var messages = inbox.getMessages();
//            var result = Arrays.stream(messages)
//                    .map(m -> {
//                        try {
//                            return new MailMessage()
//                                    .withFrom(m.getFrom()[0].toString())
//                                    .withContent((String) m.getContent());
//                        } catch (MessagingException | IOException e) {
//                            throw new RuntimeException(e);
//                        }
//                    })
//                    .toList();
//            inbox.close();
//            store.close();
//            return result;
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    public List<MailMessage> receive(String username, String password, Duration duration) throws InterruptedException {
//        var start = System.currentTimeMillis();
//        while (System.currentTimeMillis() < start + duration.toMillis()) {
//            try {
//                var session = Session.getInstance(new Properties());
//                Store store = session.getStore("pop3");
//                store.connect("localhost", username, password);
//                var inbox = store.getFolder("INBOX");
//                inbox.open(Folder.READ_ONLY);
//                var messages = inbox.getMessages();
//                var result = Arrays.stream(messages)
//                        .map(m -> {
//                            try {
//                                return new MailMessage()
//                                        .withFrom(m.getFrom()[0].toString())
//                                        .withContent((String) m.getContent());
//                            } catch (MessagingException | IOException e) {
//                                throw new RuntimeException(e);
//                            }
//                        })
//                        .toList();
//                inbox.close();
//                store.close();
//                if (result.size() > 0) {
//                    return result;
//                }
//            } catch (MessagingException e) {
//                throw new RuntimeException(e);
//            }
//            Thread.sleep(1000);
//        }
//        throw  new RuntimeException("No mail");
//    }

    public List<MailMessage> receive(String username, String password, Duration duration) {
        var start = System.currentTimeMillis();
        while (System.currentTimeMillis() < start + duration.toMillis()) {
            try {
                var inbox = getInbox(username, password);
                inbox.open(Folder.READ_ONLY);
                var messages = inbox.getMessages();
                var result = Arrays.stream(messages)
                        .map(m -> {
                            try {
                                return new MailMessage()
                                        .withFrom(m.getFrom()[0].toString())
                                        .withContent((String) m.getContent());
                            } catch (MessagingException | IOException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .toList();
                inbox.close();
                inbox.getStore().close();
                if (result.size() > 0) {
                    return result;
                }
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Interrupted while waiting for email", e);
            }
        }
        throw new RuntimeException("No mail within " + duration.toSeconds() + " seconds");
    }

    private static Folder getInbox(String username, String password) {
        try {
            var session = Session.getInstance(new Properties());
            Store store = session.getStore("pop3");
            store.connect("localhost", username, password);
            var inbox = store.getFolder("INBOX");
            return inbox;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    // удаление мейлов из почты
    public void drain(String username, String password) {
        try {
            var inbox = getInbox(username,password);
            inbox.open(Folder.READ_WRITE);
            Arrays.stream(inbox.getMessages()).forEach(m -> {
                try {
                    m.setFlag(Flags.Flag.DELETED, true);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            });
            inbox.close();
            inbox.getStore().close();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }


    public String getConfirmationLink(List<MailMessage> messages) {
        if (messages == null || messages.isEmpty()) {
            throw new RuntimeException("No messages to extract link from");
        }

        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);

        if (matcher.find()) {
            return text.substring(matcher.start(), matcher.end());
        }

        throw new RuntimeException("Confirmation link not found in email");
    }

}
