package tsu;

public class Main {
    public static void main(String[] args) {
        String urlYandex = "https://ya.ru/";
        String urlGoogleTranslater = "https://translate.google.com/";

        DOMService domService = new DOMService();

        try {
            domService.navigateTo(urlGoogleTranslater);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Elements elements = domService.getPageElements();
        System.out.println(elements.toString());

    }
}