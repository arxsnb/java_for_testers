package ru.stqa.mantis.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Paths;
import java.time.Duration;

public class HelperBase {
    protected final ApplicationManager manager;

    public HelperBase(ApplicationManager manager) {
        this.manager = manager;
    }

    protected void click(By locator) {
        manager.driver().findElement(locator).click();
    }

    protected void type(By locator, String text) {
        if (text == null) {
            return;  // Не отправляем null
        }
        click(locator);
        manager.driver().findElement(locator).clear();
        manager.driver().findElement(locator).sendKeys(text);
    }

    protected void attach(By locator, String file) {
        manager.driver().findElement(locator).sendKeys(Paths.get(file).toAbsolutePath().toString());
    }

    public void refreshPage() {
        manager.driver().navigate().refresh();
        waitForPageLoad();
    }

    protected void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(manager.driver(), Duration.ofSeconds(10));
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
    }

    protected boolean isElementPresent(By locator) {
        return manager.driver().findElements(locator).size() > 0;
    }

}
