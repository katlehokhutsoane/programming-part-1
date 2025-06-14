import static org.junit.jupiter.api.Assertions.*;


//test application
import org.junit.Test;

public class LoginTest {

    @Test
    public void testCheckUserName_Valid(){
        assertTrue(Login.checkUserName("kat_"));//underscore and less than or equal to five characters

    }

    @Test
    public void testCheckUserName_Invalid(){
        assertFalse(Login.checkUserName("katleho"));//too long with no underscore
    }


    @Test
    public void testCheckPasswordComplexity_Valid(){
        assertTrue(Login.checkPasswordComplexity("Pass_123"));//meets all the conditions
    }


    @Test
    public void testCheckPasswordComplexity_Invalid(){
        assertFalse(Login.checkPasswordComplexity("password"));//too long with no underscore
    }


    @Test
    public void testCheckCellPhoneNumber_Valid(){
        assertFalse(Login.checkCellPhoneNumber("+27812345678"));//10 digits

    }


    @Test
    public void testCheckCellPhoneNumber_Invalid(){
        assertFalse(Login.checkCellPhoneNumber("12345"));//too long with no underscore
    }

    @Test
    public void testyRegisterUser_Valid(){
        Login login = new Login();
        String result = login.registerUser("kat_", "Pass_123", "+27718693002");
        assertTrue(result.contains("successfully"));
    }

    @Test
    public void testyRegisterUser_InvalidPassword(){
        Login login = new Login();
        String result = login.registerUser("kat_", "pass", "+27718693002");
        assertTrue(result.contains("Password is not correctly formatted"));
    }
}
