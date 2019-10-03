package com.example.quotesapplication.Insert;

public class Insert_ModelClass {

    String id;
    String category;
    String categoryId;
    String quotes;

    public Insert_ModelClass() {
    }

    public Insert_ModelClass(String id, String category, String categoryId, String quotes) {
        this.id = id;
        this.category = category;
        this.categoryId = categoryId;
        this.quotes = quotes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getQuotes() {
        return quotes;
    }

    public void setQuotes(String quotes) {
        this.quotes = quotes;
    }
}
