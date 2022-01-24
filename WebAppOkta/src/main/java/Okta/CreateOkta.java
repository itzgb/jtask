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
import com.okta.sdk.resource.user.User;
import com.okta.sdk.resource.user.UserBuilder;
import com.okta.sdk.resource.user.UserList;

/**
 * Servlet implementation class CreateOkta
 */
@WebServlet("/createaccokta")
public class CreateOkta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateOkta() {
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
		
		//create a acc
		
		/*User user = UserBuilder.instance()
			    .setEmail("joe.coder@example.com")
			    .setFirstName("Joe")
			    .setLastName("Code")
			    .buildAndCreate(client);*/

		
		
		UserList users = client.listUsers();
		
		ArrayList<oktaUser> userlist = new ArrayList<oktaUser>();
		// stream
		client.listUsers().stream()
		    .forEach(u -> {
		    	oktaUser usr = new oktaUser();
		    	usr.setFirstName(u.getProfile().getFirstName());
		    	usr.setLastName(u.getProfile().getLastName());
		    	usr.setEmail(u.getProfile().getEmail());
		    	userlist.add(usr);
		      System.out.print(u+"\n");
		      System.out.print(u.getProfile().getFirstName()+"\n"+u.get("profile").getClass()+"\n");
		      
		    });
		
		// search by email
		UserList usersquery = client.listUsers("jo.coder@example.com", null, null, null, null);

		

		System.out.print("\nuserlist = "+userlist);
		System.out.print("\nUsersquery = "+usersquery);
		System.out.print("\nitems = "+usersquery.stream().count());
		session.setAttribute("userlist",userlist);
		response.sendRedirect("/WebAppOkta/createaccokta.jsp");
		//request.getRequestDispatcher("/WebAppOkta/createaccokta.jsp").forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		HttpSession session = request.getSession();
		
		Client client = Clients.builder()
			    .setOrgUrl("https://dev-71689441.okta.com")  // e.g. https://dev-123456.okta.com
			    .setClientCredentials(new TokenClientCredentials("00XatG_bP9dhzNCzS_ygtoTIOZaW7nIDOAu-XhMbHd"))
			    .build();
		//duplication
		
		UserList usersquery = client.listUsers(firstname, null, null, null, null);
		if(usersquery.stream().count()>0) {
			session.setAttribute("err","true");
			response.sendRedirect("/WebAppOkta/loginSuccess.jsp");
		}
		else {
		//create a acc
			session.setAttribute("err",null);
		User user = UserBuilder.instance()
			    .setEmail(email)
			    .setFirstName(firstname)
			    .setLastName(lastname)
			    .buildAndCreate(client);
		response.sendRedirect("/WebAppOkta/viewOkta");
		
		
		}
	}

}
