package Okta;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.okta.sdk.authc.credentials.TokenClientCredentials;
import com.okta.sdk.client.Client;
import com.okta.sdk.client.Clients;

/**
 * Servlet implementation class viewOkta
 */
@WebServlet("/viewOkta")
public class viewOkta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewOkta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		Client client = Clients.builder()
			    .setOrgUrl("https://dev-71689441.okta.com")  // e.g. https://dev-123456.okta.com
			    .setClientCredentials(new TokenClientCredentials("00XatG_bP9dhzNCzS_ygtoTIOZaW7nIDOAu-XhMbHd"))
			    .build();
		
		ArrayList<oktaUser> userlist = new ArrayList<oktaUser>();
		// stream
		client.listUsers().stream()
		    .forEach(u -> {
		    	oktaUser usr = new oktaUser();
		    	usr.setFirstName(u.getProfile().getFirstName());
		    	usr.setLastName(u.getProfile().getLastName());
		    	usr.setEmail(u.getProfile().getEmail());
		    	userlist.add(usr);
		      
		    });
		
		session.setAttribute("userlist",userlist);
		response.sendRedirect("/WebAppOkta/viewOkta.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
