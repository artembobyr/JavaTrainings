package Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;

public class CalculatorServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double sum = 0;
        String invalid = null;
        int first_number = 0;
        int second_number = 0;

        try {
            first_number = Integer.valueOf(req.getParameter("first_number"));
        } catch (NumberFormatException nfe) {
            invalid = "input number in first field";
        }

        try {
            second_number = Integer.valueOf(req.getParameter("second_number"));
        } catch (NumberFormatException nfe) {
            invalid = "input number in second field";
        }

        if (req.getParameter("+") != null) {
            sum = first_number + second_number;
        } else if (req.getParameter("-") != null) {
            sum = first_number - second_number;
        } else if (req.getParameter("*") != null) {
            sum = first_number * second_number;
        } else if (req.getParameter("/") != null) {
            if(second_number ==0){
                invalid = "Divide on zero";
            }
            sum = (double) first_number / second_number;
        }

        String sum1 = new DecimalFormat("#0.00").format(sum);
        req.setAttribute("invalid", invalid);
        if (req.getAttribute("invalid") == null) {
            req.setAttribute("sum", sum1);
        }
        req.getRequestDispatcher("calculator.jsp").forward(req, resp);
    }
}