package LoginWeb;

import java.io.IOException;
import java.security.SecureRandom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base32;

import LoginBean.loginBean;
import LoginDatabase.LoginDB;
import test.GoogleAuthenticatorDemo;

/**
 * Servlet implementation class TfaEnable
 */
@WebServlet("/enable")
public class TfaEnable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TfaEnable() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		GoogleAuthenticatorDemo gad = new GoogleAuthenticatorDemo();
		String skey = gad.getRandomSecretKey();
	
	    loginBean loginbean = new loginBean();
	  
	    LoginDB logindb = new LoginDB();
	    logindb.insertTfa(loginbean.getUsername());
	    logindb.insertkey(skey,(String)session.getAttribute("user"));
	    
	    request.setAttribute("secretKey", skey);
	    request.getRequestDispatcher("tfaEnable.jsp").forward(request,response);
	    
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
