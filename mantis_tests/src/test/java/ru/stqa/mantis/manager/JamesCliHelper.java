package ru.stqa.mantis.manager;


import org.openqa.selenium.os.ExternalProcess;

import java.time.Duration;

public class JamesCliHelper extends HelperBase{

    public JamesCliHelper(ApplicationManager manager) {
        super(manager);
    }

//    public void addUser (String email, String password) {
//        CommandLine cmd = new CommandLine(
//                "java", "-cp", "\"james-server-jpa-app.lib/*\"",
//                "org.apache.james.cli.ServerCmd",
//                "AddUser", email, password);
//        cmd.execute();
//        cmd.waitFor();
//
//
//    }



    public void addUser(String email, String password) {
        try {
            ExternalProcess.builder()
                    .command("java", "-cp", "\"james-server-jpa-app.lib/*\"",
                            "org.apache.james.cli.ServerCmd", "AddUser", email, password)
                    .directory(manager.property("james.workingDir"))
                    .copyOutputTo(System.err)
                    .start()
                    .waitFor(Duration.ofHours(1));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();  // восстанавливаем статус прерывания
            throw new RuntimeException("Process was interrupted", e);
        }
    }

}
