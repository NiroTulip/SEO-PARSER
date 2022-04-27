package ru.seoParser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class DuckGoParser extends AbstractParser{
    Integer processedSnippets = 0;

    DuckGoParser(String keyword, Integer searchDepth){
        super(keyword, searchDepth);
        this.engine = "DUCK_DUCK_GO";
        this.engineURL = "https://duckduckgo.com/";
        this.resultsOnPageXPATH = "//a[@data-testid='result-title-a']";
        this.nextResultPageXPATH = "";
    }

    @Override
    protected void showListElements(List<WebElement> weList, Integer page){
        System.out.println("Results from page " + page + ":");
        for (int i = processedSnippets; i<weList.size(); i++) {
            WebElement result = weList.get(i);
            String linkTitle = extractTitle(result);;
            String linkHref = extractHref(result);
            System.out.println(linkTitle + " | " + linkHref);
        }
        System.out.println();
    }

    @Override
    protected void getResult(ChromeDriver driver, Integer page){
     List<WebElement> searchResults = driver.findElements(By.xpath(resultsOnPageXPATH));
     showListElements(searchResults,page);
     processedSnippets = searchResults.size();
    }

    @Override
    protected WebElement getInput(ChromeDriver driver){
        return driver.findElement(By.name("q"));
    }

    @Override
    protected void paginationSearch(ChromeDriver driver){
        for (int i = 2; i <= searchDepth; i++) {
            WebElement nextButton = driver.findElement(By.xpath("//a[contains(@class,'result--more__btn')]"));
            nextButton.click();
            getResult(driver, i);
        }
    }
};




