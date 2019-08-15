package model.db;

import model.domain.Article;

import java.util.HashMap;

public interface ArticleDatabase {
    HashMap<String, Article> getArticles();
    Article getArticle(String articlenumber);
}
