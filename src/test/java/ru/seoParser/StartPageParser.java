package ru.seoParser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class StartPageParser extends AbstractParser{

    StartPageParser(String keyword, Integer searchDepth){
        super(keyword, searchDepth);
        this.engine = "START_PAGE";
        this.engineURL = "https://www.startpage.com/";
        this.resultsOnPageXPATH = "//a[@class='w-gl__result-title result-link']";
        this.nextResultPageXPATH = "";
    }

    @Override
    protected WebElement getInput(ChromeDriver driver){
        return driver.findElement(By.id("q"));
    }

    @Override
    protected String extractTitle(WebElement result){
        return result.findElement(By.xpath("./h3")).getText();
    }

    @Override
    protected void paginationSearch(ChromeDriver driver){
        for (int i = 2; i <= searchDepth; i++) {
            String nextPage = Integer.toString(i);
            String xPath = "//form[@class='pagination__form']/button[@class='pagination__num' and text()='" + nextPage + "']";
            WebElement nextPageButton = driver.findElement(By.xpath(xPath));
            nextPageButton.submit();
            waitLoad(driver);
            getResult(driver, i);
        }
    }

};




