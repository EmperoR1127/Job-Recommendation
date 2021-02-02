package com.laioffer.job.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laioffer.job.entity.ExampleBook;
import org.json.JSONObject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

@WebServlet(name = "ExampleBookServlet", urlPatterns = {"/example_book"})
public class ExampleBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        ExampleBook book = new ExampleBook("Harry Potter and the Sorcerer's Stone",
                "JK Rowling",
                "October 1, 1998",
                11.99,
                "USD",
                309,
                "Harry Potter",
                "en_US",
                "0590353403");
        response.getWriter().print(mapper.writeValueAsString(book));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonRequest = new JSONObject(IOUtils.toString(request.getReader()));
        String title = jsonRequest.getString("title");
        String author = jsonRequest.getString("author");
        String date = jsonRequest.getString("date");
        float price = jsonRequest.getFloat("price");
        String currency = jsonRequest.getString("currency");
        int pages = jsonRequest.getInt("pages");
        String series = jsonRequest.getString("series");
        String language = jsonRequest.getString("language");
        String isbn = jsonRequest.getString("isbn");

        System.out.println("Title is: " + title);
        System.out.println("Author is: " + author);
        System.out.println("Date is: " + date);
        System.out.println("Price is: " + price);
        System.out.println("Currency is: " + currency);
        System.out.println("Pages is: " + pages);
        System.out.println("Series is: " + series);
        System.out.println("Language is: " + language);
        System.out.println("ISBN is: " + isbn);

        response.setContentType("application/json");
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", "ok");
        response.getWriter().print(jsonResponse);
    }
}














