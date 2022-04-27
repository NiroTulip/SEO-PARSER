package ru.seoParser;

public class MainTest {
    private static String keyword = "SpaceX";
    private static int searchDepth = 2;

    public static void main(String[] args) {
       AbstractParser bp = new BingParser(keyword, searchDepth);
       bp.doTest();
       AbstractParser dgp = new DuckGoParser(keyword, searchDepth);
       dgp.doTest();
       AbstractParser gp = new GoogleParser(keyword, searchDepth);
       gp.doTest();
       AbstractParser yp = new YandexParser(keyword, searchDepth);
       yp.doTest();
       AbstractParser sp = new StartPageParser(keyword, searchDepth);
       sp.doTest();
    }
}
