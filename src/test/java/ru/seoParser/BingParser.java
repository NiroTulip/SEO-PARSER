package ru.seoParser;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BingParser extends AbstractParser{

    BingParser(String keyword, Integer searchDepth){
        super(keyword, searchDepth);
        this.engine = "BING";
        this.engineURL = "https://www.bing.com/";
        this.resultsOnPageXPATH = "//div[@class='b_title']//a[contains(@h, 'ID=SERP') and text()]";
        this.nextResultPageXPATH = "//a[@class='b_widePag sb_bp' and text()='";
    }

    @Override
    protected WebElement getInput(ChromeDriver driver){
        return driver.findElement(By.id("sb_form_q"));
    }
};




