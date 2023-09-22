package com.example.MyBookShopApp.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.*;

@Service
public class BookService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    public List<Book> getBookData(){
//        List<Book> books = jdbcTemplate.query("SELECT name, title, priceOld, price FROM books b JOIN authors a ON author_id = a.id", (ResultSet rs, int rowNum)->{
//            Book book = new Book();
//            book.setAuthor(rs.getString("name"));
//            book.setTitle(rs.getString("title"));
//            book.setPriceOld(rs.getString("priceOld"));
//            book.setPrice(rs.getString("price"));
//            return book;
//        });
//        return new ArrayList<Book>(books);
//    }

    public List<Book> getBookDataRecommended(){
        List<Book> books = jdbcTemplate.query("SELECT name, title, priceOld, price, " +
                                                  "CAST(SUBSTRING(priceOld, 2) as DECIMAL(10,2)) - CAST(SUBSTRING(price, 2) as DECIMAL(10,2)) discount " +
                                                  "FROM books b JOIN authors a ON author_id = a.id  " +
                                                  "ORDER BY discount DESC", (ResultSet rs, int rowNum)->{
            Book book = new Book();
            book.setAuthor(rs.getString("name"));
            book.setTitle(rs.getString("title"));
            book.setPriceOld(rs.getString("priceOld"));
            book.setPrice(rs.getString("price"));
            return book;
        });

        return new ArrayList<>(books);
    }




    public List<AuthorsOfLetter> getAuthorData(){

        List<String> authors = jdbcTemplate.query("SELECT name FROM authors ORDER BY name", (ResultSet rs, int rowNum) -> {
            String a = rs.getString("name");
            return a;
        });

        List<AuthorsOfLetter> authorsOfLetters = new ArrayList<>();
        String first = "я";

        for(String a : authors){
            if (!a.substring(0, 1).equals(first)) {
                first = a.substring(0, 1);
                AuthorsOfLetter aol = new AuthorsOfLetter();
                aol.setLetter(first);
                aol.setAuthors(new ArrayList<>(Arrays.asList(a)));
                authorsOfLetters.add(aol);
            } else {
                authorsOfLetters.get(authorsOfLetters.size() - 1).getAuthors().add(a);
            }
        }
        return authorsOfLetters;
        //ВОЗВРАЩАЕМ Map<String, List<String>> letterAuthor
//        List<String> authors = jdbcTemplate.query("SELECT name FROM authors ORDER BY name", (ResultSet rs, int rowNum) -> {
//            String a = rs.getString("name");
//            return a;
//        });
//
//        Map<String, List<String>> letterAuthor = new HashMap<>();
//        String first = "я";
//
//        for(String a : authors){
//            if (!a.substring(0, 1).equals(first)) {
//                first = a.substring(0, 1);
//                letterAuthor.put(first, new ArrayList<>(Arrays.asList(a)));
//            } else {
//                letterAuthor.get(first).add(a);
//            }
//        }
//
//        return letterAuthor;




//        ПРОСТОЙ ВЫВОД АВТОРОВ
//        List<String> authors = jdbcTemplate.query("SELECT name, FROM authors ORDER BY name", (ResultSet rs, int rowNum) -> {
//            String a = rs.getString("name");
//            return a;
//        });
//        return new ArrayList<>(authors);
    }
}
