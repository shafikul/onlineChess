package com;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author fmsis
 */
@WebServlet(name = "ChessBoardServlet", urlPatterns = {"/ChessBoardServlet"})
public class ChessBoardServlet extends HttpServlet {
    public PlayerCanMove PC = new PlayerCanMove();
    private boolean flag = true;
    int sx,sy,px = 0,py = 0;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ChessBoardServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChessBoardServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            
        }
    }  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        PrintWriter out = response.getWriter();
        String  res = "";
        
        if(flag) 
        {
            String s1 = request.getParameter("Crow");
            String s2 =  request.getParameter("Ccol");
            String s3 = request.getParameter("Srow");
            String s4 =  request.getParameter("Scol");
            sx = Integer.parseInt(s1) + 1;
            sy = Integer.parseInt(s2) + 1;
            px = Integer.parseInt(s3) + 1;
            py = Integer.parseInt(s4) + 1;
            res = PC.CanMove(sx, sy, px, py);
            if(res.isEmpty())res = "SUECSS";
            else if(res.length()== 4)
            {
                flag = false;

            }
        }
        else
        {
            String str = request.getParameter("piece").toString().trim();
            int state = Integer.parseInt(str);
            if(state == 4)state = 5;
            else if(state == 1) state = 4;
            res = PC.changeImage(state);
            if(res.isEmpty())res = "SUECSS";
            flag = true;            
        }
        out.println(res);
        
    }    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}