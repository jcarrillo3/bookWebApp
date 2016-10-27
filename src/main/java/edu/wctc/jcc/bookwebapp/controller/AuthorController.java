/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jcc.bookwebapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.wctc.jcc.bookwebapp.model.Author;
import edu.wctc.jcc.bookwebapp.model.AuthorDAO;
import edu.wctc.jcc.bookwebapp.model.AuthorDAOInterface;
import edu.wctc.jcc.bookwebapp.model.AuthorService;
import edu.wctc.jcc.bookwebapp.model.MySqlDbStrategy;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author jcarrillo
 */
@WebServlet(name = "AuthorController", urlPatterns = {"/authors"})
public class AuthorController extends HttpServlet {
    private final static String LIST_ACTION = "list";
    private final static String ADD_UPDATE_DELETE_ACTION = "AddUpdateDelete";
    private final static String SAVE_ADD = "add";
    private final static String SAVE_UPDATE = "update";
    private final static String AUTHORS_PAGE = "/authorsTable.jsp";
    private final static String ADD_UPDATE_PAGE = "/addUpdateAuthor.jsp";
    private final static String ADD_HEADER = "Add Author";
    private final static String UPDATE_HEADER = "Update Author";
    
    private final String INVALID_VALUE_MSG = "Invalid Value";

    private String driverClass;
    private String url;
    private String username;
    private String password;
    private String webMasterEmail;
    
    private String dbJndiName;
    
    @Inject
    private AuthorService authService;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
//        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        String destination = AUTHORS_PAGE;

        try {
            configDbConnection();
            switch (action) {
                case LIST_ACTION:
                    destination = AUTHORS_PAGE;
                   List<Author> authors = authService.getAuthors();
                    request.setAttribute("authorList", authors);
                    break;
                case ADD_UPDATE_DELETE_ACTION:
                    destination = ADD_UPDATE_PAGE;
                    String addOrUpdate = "";
                    String header = "";
                    String[] selected = request.getParameterValues("checkboxes");
                    //If no checkbox is selected we will add new Author
                    if (selected == null || selected.length == 0) {
                        addOrUpdate = "add";
                        header = ADD_HEADER;
                    } // We Update by verifying the update button was pressed
                    else if (!(request.getParameter("btnUpdate") == null)) {
                        addOrUpdate = "update";
                        header = UPDATE_HEADER;
                        String id = selected[0];
                        Author a = authService.getAuthorById(id);
                        request.setAttribute("id", id);
                        request.setAttribute("name", a.getAuthorName());
                        request.setAttribute("date", a.getDateAdded());
                    } // Leaves Delete as the last option
                    else {
                        destination = AUTHORS_PAGE;
                        for (String author : selected) {
                            authService.deleteAuthor(author);
                        }
                        refreshAuthors(request);
                    }
                    request.setAttribute("pageTitle", header);
                    request.setAttribute("addOrUpdate", addOrUpdate);
                    break;
                case SAVE_ADD:
                    destination = AUTHORS_PAGE;
                    //Check if cancel button was pressed
                    if (request.getParameter("cancel") != null) {
                        refreshAuthors(request);
                    } 
                    // else add new author 
                    else {
                        String authorName = request.getParameter("name");
                        List<Object> colValues = new ArrayList<>();
                        colValues.add(authorName);
                        colValues.add(new Date());
                        authService.createNewAuthor(generateColNames(), colValues);
                        refreshAuthors(request);
                    }
                    break;
                case SAVE_UPDATE:
                    destination = AUTHORS_PAGE;
                    // check if cancel button was pressed
                    if (request.getParameter("cancel") != null) {
                        refreshAuthors(request);
                    } 
                    // else update author
                    else {
                        String id = request.getParameter("id");
                        String name = request.getParameter("name");
                        String date = request.getParameter("date");
                        List<Object> values = new ArrayList<>();
                        values.add(name);
                        values.add(date);
                        authService.updateAuthorByKey(generateColNames(), values, "author_id", id);
                        refreshAuthors(request);
                    }
                    break;
            }
        } catch (Exception e) {
            request.setAttribute("errMsg", e.getCause().getMessage());
            e.printStackTrace();
        }

        RequestDispatcher view = request.getRequestDispatcher(destination);
        view.forward(request, response);
    }

    private void refreshAuthors(HttpServletRequest request)
            throws ClassNotFoundException, SQLException {

        List<Author> authors = authService.getAuthors();
        request.setAttribute("authorList", authors);
    }

    private List generateColNames() {
        List<String> colNames = new ArrayList<>();
        colNames.add("author_name");
        colNames.add("date_added");

        return colNames;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AuthorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AuthorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AuthorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AuthorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    public void init() throws ServletException {
        driverClass = getServletContext().getInitParameter("db.driver.class");
        url = getServletContext().getInitParameter("db.url");
        username = getServletContext().getInitParameter("db.username");
        password = getServletContext().getInitParameter("db.password");
        
        dbJndiName = getServletContext().getInitParameter("db.jndi.name");
    }

    private void configDbConnection() throws NamingException, SQLException {
        //authService.getDao().initDAO(driverClass, url, username, password);
        
        if (dbJndiName == null){
            authService.getDao().initDAO(driverClass, url, username, password);
        } else {
           Context ctx = new InitialContext();
           DataSource ds = (DataSource) ctx.lookup(dbJndiName);
           authService.getDao().initDAO(ds);   
        }
    }
}
