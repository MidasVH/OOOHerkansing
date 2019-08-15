package model.domain;

public class Article {
    private String articlenumber, description, articlegroup;
    private double price;
    private int stock;

    public Article(String articlenumber, String description, String articlegroup, double price, int stock){
        setArticlenumber(articlenumber);
        setDescription(description);
        setArticlegroup(articlegroup);
        setPrice(price);
        setStock(stock);
    }

    public void decreaseStock(){
         setStock(getStock() - 1);
    }

    public void increaseStock(){
        setStock(getStock() + 1);
    }

    public void setArticlenumber(String articlenumber){
        if (articlenumber.trim().isEmpty() || Integer.parseInt(articlenumber) <= 0){
            throw new IllegalArgumentException("Not a valid articlenumber");
        }
        this.articlenumber = articlenumber;
    }

    public void setDescription(String description){
        if(description.trim().isEmpty()){
            throw new IllegalArgumentException("Not a valid description");
        }
        this.description = description;
    }

    public void setArticlegroup(String articlegroup){
        if (articlegroup.trim().isEmpty()){
            throw new IllegalArgumentException("Not a valid articlegroup");
        }
        this.articlegroup = articlegroup;
    }

    public void setPrice(double price){
        if (price <= 0){
            throw new IllegalArgumentException("Not a valid price");
        }
        this.price = price;
    }

    public void setStock(int stock){
        if (stock < 0 ){
            throw new IllegalArgumentException("Stock cannot go under 0");
        }

        this.stock = stock;
    }

    public String getArticlenumber(){
        return articlenumber;
    }

    public String getDescription(){
        return description;
    }

    public String getArticlegroup(){
        return articlegroup;
    }

    public double getPrice(){
        return price;
    }

    public int getStock(){
        return stock;
    }

}
