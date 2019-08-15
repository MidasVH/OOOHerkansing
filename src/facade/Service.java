package facade;

import model.db.ArticleDatabase;
import model.db.ArticleDatabaseLocal;
import model.domain.Article;
import model.domain.Observer;
import model.domain.ShoppingCart;
import model.domain.Subject;

import java.util.ArrayList;
import java.util.HashMap;

public class Service implements Subject {
    private ArticleDatabase articleDatabase;
    private ShoppingCart shoppingCart;
    private ArrayList<Observer> observers;

    public Service(){
        articleDatabase = ArticleDatabaseLocal.getInstance();
        shoppingCart = new ShoppingCart();
        observers = new ArrayList<Observer>();
    }

    public HashMap<String, Integer> getShoppingCart(){
        return shoppingCart.getShoppingCart();
    }

    public void addToCart(String articlenumber){
        shoppingCart.addToCart(articlenumber);
        articleDatabase.getArticle(articlenumber).decreaseStock();
        this.notifyObservers();
    }

    public void removeFromCart(String articlenumber){
        shoppingCart.removeFromCart(articlenumber);
        articleDatabase.getArticle(articlenumber).increaseStock();
        this.notifyObservers();
    }

    public ArrayList<Article> getArticlesInCart(){
        return shoppingCart.getArticlesInCart();
    }

    public double getTotalCart(){
        return shoppingCart.getTotalCart();
    }

    public double getArticleTotal(String articlenumber){
        return shoppingCart.getArticleTotal(articlenumber);
    }

    public ArrayList<String> getArticleNumbers(){
        return shoppingCart.getArticlenumbers();
    }

    public HashMap<String, Article> getArticles(){
        return articleDatabase.getArticles();
    }

    public Article getArticle(String articlenumber){
        return articleDatabase.getArticle(articlenumber);
    }

    @Override
    public void addObserver(Observer o){
        if(o == null){
            throw new IllegalArgumentException("not valid observer");
        }
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o){
        observers.remove(o);
    }

    public void notifyObservers(){
        for(Observer o : observers){
            o.update();
        }
    }
}
