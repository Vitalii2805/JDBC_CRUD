package com.jdbc.controller;

import com.jdbc.model.User;
import com.jdbc.repository.IUserRepository;
import com.jdbc.repository.UserRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserController extends HttpServlet {
    private static final String LIST_USER = "/listUser.jsp";
    private static final String INSERT_OR_EDIT = "/userEdit.jsp";
    private IUserRepository userRepository;

    public UserController() {
        userRepository = new UserRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String forward = "";
        if (action.equalsIgnoreCase("delete")) {
            int id = Integer.parseInt(req.getParameter("userId"));
            userRepository.deleteUser(id);
            forward = LIST_USER;
            req.setAttribute("users", userRepository.getUsers());
        } else if (action.equalsIgnoreCase("list")) {
            forward = LIST_USER;
            req.setAttribute("users", userRepository.getUsers());
        } else if (action.equalsIgnoreCase("edit")) {
            int id = Integer.parseInt(req.getParameter("userId"));
            req.setAttribute("user", userRepository.getUserById(id));
            forward = INSERT_OR_EDIT;
        } else
            forward = INSERT_OR_EDIT;

        RequestDispatcher view = req.getRequestDispatcher(forward);
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setFirstName(req.getParameter("firstName"));
        user.setLastName(req.getParameter("lastName"));
        user.setEmail(req.getParameter("email"));

        try {
            Date date = new SimpleDateFormat("mm/dd/yyyy").parse(req.getParameter("dob"));
            user.setDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String id = req.getParameter("userid");
        if (id == null || id.isEmpty()) {
            userRepository.addUser(user);
        } else
            user.setId(Integer.parseInt(id));
        userRepository.upDateUser(user);

        RequestDispatcher rd = req.getRequestDispatcher(LIST_USER);
        req.setAttribute("users", userRepository.getUsers());
        rd.forward(req, resp);
    }
}


