package controller;

import facade.Service;
import model.domain.Article;

import java.util.ArrayList;
import java.util.HashMap;

public class Controller {
    private static Controller single_instance = null;
    private final Service service;

    private Controller(){
        service = new Service();
    }

    public static Controller getInstance(){
        if(single_instance == null){
            synchronized (Controller.class){ // synchronized is for avoiding problems with multiple threads
                if (single_instance == null ){
                    single_instance = new Controller();
                }
            }
        }
        return single_instance;
    }

    public Service getService(){
        return this.service;
    }

    public HashMap<String, Integer> getShoppingCart(){
        return service.getShoppingCart();
    }

    public void addToCart(String articlenumber){
        service.addToCart(articlenumber);
    }

    public ArrayList<Article> getArticlesInCart(){
        return service.getArticlesInCart();
    }

    public void removeFromCart(String articlenumber){
        service.removeFromCart(articlenumber);
    }

    public double getTotalCart(){
        return service.getTotalCart();
    }

    public double getArticleTotal(String articlenumber){
        return service.getArticleTotal(articlenumber);
    }

    public HashMap<String, Article> getArticles(){
        return service.getArticles();
    }

    public Article getArticle(String articlenumber){
        return service.getArticle(articlenumber);
    }

    public ArrayList<String> getArticleNumbers(){
        return service.getArticleNumbers();
    }

}
