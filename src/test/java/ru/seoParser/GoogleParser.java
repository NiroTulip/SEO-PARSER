package ru.seoParser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class GoogleParser extends AbstractParser{

    public GoogleParser(String keyword, Integer searchDepth){
        super(keyword, searchDepth);
        this.engine = "GOOGLE";
        this.engineURL = "https://www.google.com/";
        this.resultsOnPageXPATH = "//h3[@class='LC20lb MBeuO DKV0Md']";
        this.nextResultPageXPATH = "//a[@class='fl' and @aria-label = 'Page ";
    }

    @Override
    protected WebElement getInput(ChromeDriver driver){
        return driver.findElement(By.name("q"));
    }

    @Override
    protected String extractHref(WebElement result){
        return result.findElement(By.xpath("..")).getAttribute("href");
    }
}




