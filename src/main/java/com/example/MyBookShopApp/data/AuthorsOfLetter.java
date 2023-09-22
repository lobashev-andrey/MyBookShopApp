package com.example.MyBookShopApp.data;

import java.util.List;

public class AuthorsOfLetter {

    private String letter;
    private List<String> authors;

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }
}
