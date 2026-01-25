/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UserDAO;
import model.UserDTO;

/**
 *
 * @author Acer
 */
public class LoginController extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "";

        try {
            // 1. Lấy dữ liệu từ form login.jsp
            String user = request.getParameter("textUsername");
            String pass = request.getParameter("textPassword");

            // 2. Validation (Kiểm tra rỗng) - Yêu cầu "bắt buộc phải nhận username/pass"
            // Ta kiểm tra nếu null hoặc rỗng thì báo lỗi ngay
            if (user == null || user.trim().isEmpty() || pass == null || pass.trim().isEmpty()) {
                request.setAttribute("ERROR_MESSAGE", "User ID and Password cannot be empty!");
                // Url vẫn là LOGIN_PAGE để quay lại form
            } else {
                // 3. Gọi DAO để kiểm tra trong Database
                UserDAO dao = new UserDAO();
                UserDTO loginUser = dao.login(user, pass);

                if (loginUser == null) {
                    // Đăng nhập sai (không tìm thấy hoặc sai pass)
                    request.setAttribute("ERROR_MESSAGE", "Incorrect User ID or Password!");
                } else {
                    // Đăng nhập đúng user/pass, giờ kiểm tra STATUS
                    // Trong SQL: Bit 0 là false, 1 là true.
                    // Trong Java DTO: status là boolean.

                    if (loginUser.isStatus() == false) {
                        // Status = 0 (false) -> Chuyển trang E403
                        url = "E403.jsp";
                        request.setAttribute("ERROR_DETAILS", "Your account is inactive (Status 0).");
                    } else {
                        // Status = 1 (true) -> Đăng nhập thành công

                        // QUAN TRỌNG: Tạo Session để hệ thống nhớ người dùng này
                        HttpSession session = request.getSession();
                        session.setAttribute("LOGIN_USER", loginUser);

                        // Chuyển hướng tới trang chức năng (ví dụ a.jsp)
                        url = "a.jsp";

                        // Có thể check Role ở đây để chuyển trang khác nếu cần
                        // if("AD".equals(loginUser.getRoleID())) url = "admin.jsp";
                    }
                }
            }

        } catch (Exception e) {
            log("Error at LoginController: " + e.toString());
        } finally {
            // Chuyển trang
            request.getRequestDispatcher(url).forward(request, response);
        }
    
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
        processRequest(request, response);
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
        processRequest(request, response);
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

}
