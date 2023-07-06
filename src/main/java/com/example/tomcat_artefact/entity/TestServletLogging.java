package com.example.tomcat_artefact.entity;

import java.io.IOException;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TestServletLogging extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(TestServletLogging.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        LOGGER.info("This is an info level log message");
        LOGGER.warning("This is a warning level log message");
        LOGGER.severe("This is a severe level log message");
    }
}
