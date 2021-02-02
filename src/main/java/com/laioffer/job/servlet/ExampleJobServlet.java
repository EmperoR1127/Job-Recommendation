package com.laioffer.job.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import com.laioffer.job.entity.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ExampleJobServlet", urlPatterns = {"/example_job"})
public class ExampleJobServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        ExampleCoordinates coordinates = new ExampleCoordinates(37.485130, -122.148316);
        ExampleJob job = new ExampleJob("Software Engineer", 123456, "Aug 1, 2020", false, coordinates);
        response.getWriter().print(mapper.writeValueAsString(job));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonRequest = new JSONObject(IOUtils.toString(request.getReader()));
        String title = jsonRequest.getString("title");
        int salary = jsonRequest.getInt("salary");
        String starting = jsonRequest.getString("starting");
        boolean remote = jsonRequest.getBoolean("remote");
        int latitude = jsonRequest.getInt("latitude");
        int longitude = jsonRequest.getInt("longitude");

        ExampleJob job = new ExampleJob(title, salary, starting, remote, new ExampleCoordinates(latitude, longitude));
        System.out.println(job);

        response.setContentType("application/json");
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", "ok");
        response.getWriter().print(jsonResponse);
    }
}
