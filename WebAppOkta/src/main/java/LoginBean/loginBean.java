package LoginBean;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;

import LoginWeb.TOTP;

public class loginBean {
	private String username;
	private String password;
	private String tfa;
	private String key;
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setUsername(String uname) {
		username = uname;
	}
	
	public void setPassword(String pass) {
		password = pass;
	}
	
	public String getKey() {
		return this.key;
	}
	public void setKey(String k) {
		key = k;
	}
	
	
	
	
	
	
	
	public static String getTOTPCode(String secretKey) {
	    
	    Base32 base32 = new Base32();
	    byte[] bytes = base32.decode(secretKey);
	    String hexKey = Hex.encodeHexString(bytes);
	    long time = (System.currentTimeMillis() / 1000) / 30;
	    String hexTime = Long.toHexString(time);
	    return TOTP.generateTOTP(hexKey, hexTime, "6");
	}
	
}	


	 


