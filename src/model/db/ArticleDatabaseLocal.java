package model.db;

import model.domain.Article;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

public class ArticleDatabaseLocal implements ArticleDatabase {
    private static ArticleDatabaseLocal single_instance = null;
    private HashMap<String, Article> articles;

    private ArticleDatabaseLocal(){
        this.articles = load();
    }

    public static ArticleDatabaseLocal getInstance(){
        if(single_instance == null){
            single_instance = new ArticleDatabaseLocal();
        }
        return single_instance;
    }

    @Override
    public HashMap<String, Article> getArticles() {
        return articles;
    }

    @Override
    public Article getArticle(String articlenumber) {
        Article returnvalue = null;
        for(Article a : articles.values()){
            if(a.getArticlenumber().equals(articlenumber)){
                returnvalue = a;
            }
        }
        if(returnvalue == null){
            throw new IllegalArgumentException("not a valid articlenumber");
        }else{
            return returnvalue;
        }
    }

    private HashMap<String, Article> load(){
        HashMap<String, Article> articleMap = new HashMap<>();
        InputStream file = getClass().getClassLoader().getResourceAsStream("artikel.txt");
        articleMap.clear();

        try{
            Scanner scannerFile = new Scanner(file);
            while(scannerFile.hasNextLine()){
                Scanner scannerLijn = new Scanner(scannerFile.nextLine());
                scannerLijn.useDelimiter(",");
                String articlenumber = scannerLijn.next();
                String description = scannerLijn.next();
                String articlegroup = scannerLijn.next();
                double price = Double.parseDouble(scannerLijn.next());
                int stock = Integer.parseInt(scannerLijn.next());
                Article article = new Article(articlenumber, description, articlegroup, price, stock);
                articleMap.put(articlenumber, article);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return articleMap;
    }
}
