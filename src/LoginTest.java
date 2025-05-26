import static org.junit.jupiter.api.Assertions.*;



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
        assertFalse(Login.checkCellPhoneNumber("0812345678"));//10 digits

    }


    @Test
    public void testCheckCellPhoneNumber_Invalid(){
        assertFalse(Login.checkCellPhoneNumber("12345"));//too long with no underscore
    }

    @Test
    public void testyRegisterUser_Valid(){
        assertTrue(Login.checkUserName("kat_"), "pass");//too long with no underscore
    }

    @Test
    public void testyRegisterUser_InvalidPassword(){
        assertTrue(Login.checkUserName("Kat_"), "pass");//too long with no underscore
    }
}
