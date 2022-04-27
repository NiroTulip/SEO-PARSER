package ru.seoParser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class YandexParser extends AbstractParser{

    YandexParser(String keyword, Integer searchDepth){
        super(keyword, searchDepth);
        this.engine = "YANDEX";
        this.engineURL = "https://yandex.ru/";
        this.resultsOnPageXPATH = "//*[contains (@class,'OrganicTitle-Link organic__url')]";
        this.nextResultPageXPATH = "//*[contains (@class,'link_target_serp') and @aria-label='Страница ";
    }

    @Override
    protected WebElement getInput(ChromeDriver driver){
        return driver.findElement(By.id("text"));
    }

    @Override
    protected String extractTitle(WebElement result){
        return result.findElement(By.tagName("span")).getText();
    }
};




