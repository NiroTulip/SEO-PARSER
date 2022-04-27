package ru.seoParser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public abstract class AbstractParser {
    String keyword;
    Integer searchDepth;
    String engine;
    String engineURL;
    String resultsOnPageXPATH;
    String nextResultPageXPATH;

    public AbstractParser(String keyword, Integer searchDepth){
        this.keyword = keyword;
        this.searchDepth = searchDepth;
    }

    protected abstract WebElement getInput(ChromeDriver driver);

    protected void waitLoad(ChromeDriver driver){
        WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds( 5 ))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(resultsOnPageXPATH)));
    }

    protected ChromeDriver initDriver() {
        ChromeDriver driver = new ChromeDriver(new ChromeDriverService.Builder().withSilent(true).build());
        driver.manage().window().maximize();
        System.out.println(engine + " PARSING");
        System.out.println();
        return driver;
    }

    public void doTest(){
        ChromeDriver driver = initDriver();
        driver.get(engineURL);
        WebElement input = getInput(driver);
        input.sendKeys(keyword);
        input.submit();
        waitLoad(driver);
        getResult(driver, 1);
        if (searchDepth > 1) paginationSearch(driver);
        driver.quit();
    }

    protected void getResult(ChromeDriver driver, Integer page){
        List<WebElement> searchResults = driver.findElements(By.xpath(resultsOnPageXPATH));
        showListElements(searchResults,page);
    }

    protected String extractTitle(WebElement result){
        return result.getText();
    }

    protected String extractHref(WebElement result){
        return result.getAttribute("href");
    }

    protected void showListElements(List<WebElement> weList, Integer page){
        System.out.println("Results from page " + page + ":");
        for (WebElement result : weList) {
            String linkTitle = extractTitle(result);
            String linkHref = extractHref(result);
            System.out.println(linkTitle + " | " + linkHref);
        }
        System.out.println();
    }

    protected void paginationSearch(ChromeDriver driver){
        for (int i = 2; i <= searchDepth; i++) {
            String nextPage = Integer.toString(i);
            String xPath = nextResultPageXPATH + nextPage + "']";
            WebElement nextPageClass = driver.findElement(By.xpath(xPath));
            String nextPageURL = nextPageClass.getAttribute("href");
            driver.get(nextPageURL);
            waitLoad(driver);
            getResult(driver, i);
        }
    }
}
