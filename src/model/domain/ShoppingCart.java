package model.domain;

import model.db.ArticleDatabase;
import model.db.ArticleDatabaseLocal;

import java.util.ArrayList;
import java.util.HashMap;

public class ShoppingCart {
    private HashMap<String, Integer> cart;
    private ArticleDatabase db = ArticleDatabaseLocal.getInstance();

    public ShoppingCart(){
        cart = new HashMap<>();
    }

    public HashMap<String, Integer> getShoppingCart() {
        return cart;
    }

    public void addToCart(String articlenumber){
        if(cart.containsKey(articlenumber)){
            if(cart.get(articlenumber) == null){
                cart.put(articlenumber, 1);
            }else{
                int count = cart.get(articlenumber);
                cart.put(articlenumber, count + 1);
            }
        }else{
            cart.put(articlenumber, 1);
        }
    }

    public void removeFromCart(String articlenumber){
        if(cart.containsKey(articlenumber)){
            if(cart.get(articlenumber) > 1){
                int count = cart.get(articlenumber);
                cart.put(articlenumber, count - 1);
            }else{
                cart.remove(articlenumber);
            }
        }
    }

    public ArrayList<String> getArticlenumbers(){
        ArrayList<String> returnvalue = new ArrayList<>();

        for(String s : cart.keySet()){
            returnvalue.add(s);
        }
        return returnvalue;
    }

    public double getTotalCart(){
        double total = 0;
        for(String s : cart.keySet()){
            total += db.getArticle(s).getPrice() * getShoppingCart().get(s);
        }
        return total;
    }

    public double getArticleTotal(String articlenumber){
        return db.getArticle(articlenumber).getPrice() * getShoppingCart().get(articlenumber);
    }

    public ArrayList<Article> getArticlesInCart(){
        ArrayList<Article> articles = new ArrayList<>();
        // go over all entries in the map
        for(String s : cart.keySet()){
            //if an entry has more than 1 entry
            if(cart.get(s) > 1){
                // add all those entries to the list
                for(int i = 0; i < cart.get(s); i++){
                    articles.add(db.getArticle(s));
                }
            }else{
                articles.add(db.getArticle(s));
            }
        }
        return articles;
    }
}
