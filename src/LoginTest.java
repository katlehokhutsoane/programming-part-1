import static org.junit.jupiter.api.Assertions.*;



import org.junit.Test;

public class LoginTest {

    @Test
    public void testCheckUserName_Valid(){
        assertTrue(Login.checkUserName("kat_"));//underscore and less than or equal to five characters

    }

    @Test
    public void testCheckUserName_Invalid(){
        assertFalse(Main.checkUserName("katleho"));//too long with no underscore
    }


    @Test
    public void testCheckPasswordComplexity_Valid(){
        assertTrue(Main.checkPasswordComplexity("Pass_123"));//meets all the conditions
    }


    @Test
    public void testCheckPasswordComplexity_Invalid(){
        assertFalse(Main.checkUserName("password"));//too long with no underscore
    }


    @Test
    public void testCheckCellPhoneNumber_Valid(){
        assertTrue(Main.checkUserName("0812345678"));//10 digits

    }


    @Test
    public void testCheckCellPhoneNumber_Invalid(){
        assertFalse(Main.checkUserName("12345"));//too long with no underscore
    }

    @Test
    public void testyRegisterUser_Valid(){
        assertTrue(Main.checkUserName("kat_"), "pass");//too long with no underscore
    }

    @Test
    public void testyRegisterUser_InvalidPassword(){
        assertFalse(Main.checkUserName("kat_"), "pass");//too long with no underscore
    }
}
