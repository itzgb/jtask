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
import test.GoogleAuthenticatorDemo;

/**
 * Servlet implementation class tfaCheck
 */
@WebServlet("/tfaCheck")
public class tfaCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public tfaCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String otp = request.getParameter("otp");
		LoginDB logindb = new LoginDB();
		HttpSession session = request.getSession(false); 
		GoogleAuthenticatorDemo gad = new GoogleAuthenticatorDemo();
		
		
		String code = gad.getTOTPCode(logindb.getsecretKey((String)session.getAttribute("user")));
		if(otp.equals(code)) {
			System.out.print("Match");
			session.setAttribute("tfa", "true");
			response.sendRedirect("loginSuccess.jsp");
		}
		else {
			System.out.print("Unmatched"+code);
			response.sendRedirect("login.jsp");
		}
		
		
	}

}
