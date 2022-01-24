package LoginWeb;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import LoginBean.loginBean;
import LoginDatabase.LoginDB;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		loginBean loginbean = new loginBean();
		loginbean.setUsername(username);
		loginbean.setPassword(password);
		
		LoginDB logindb = new LoginDB();
		
		
		if(logindb.validate(loginbean)) {
			if(logindb.tfaEnabled(loginbean)) {
				//otp
				HttpSession session = request.getSession();
				session.setAttribute("user", username);
				response.sendRedirect("/WebApp/tfaOtp");
			}
			else {
			HttpSession session = request.getSession();
			session.setAttribute("user", username);
			session.setAttribute("tfa",null);
			session.setAttribute("err",null);
			response.sendRedirect("loginSuccess.jsp");
			}
		}
		else {
			response.sendRedirect("login.jsp");
		}
		
	}

}
