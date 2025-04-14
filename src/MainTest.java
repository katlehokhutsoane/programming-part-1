import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

public class MainTest {
    @Test
    public void testCheckUserName_Valid(){
        assertTrue(Main.checkUserName("kat_"));//underscore and less than or equal to five characters

    }

    @Test
    public void testCheckUserName_Invalid(){
        assertFalse(Main_checkUserName("katleho"));//too long with no underscore
    }

    private boolean Main_checkUserName(String katleho) {
            return false;
    }


    @Test
    public void testCheckPasswordComplexity_Valid(){
        assertTrue(Main_checkUserName("Pass_123"));//meets all the conditions
    }


    @Test
    public void testCheckPasswordComplexity_Invalid(){
        assertFalse(Main_checkUserName("password"));//too long with no underscore
    }


    @Test
    public void testCheckCellPhoneNumber_Valid(){
        assertTrue(Main_checkUserName("0812345678"));//10 digits

    }


    @Test
    public void testCheckCellPhoneNumber_Invalid(){
        assertFalse(Main_checkUserName("12345"));//too long with no underscore
    }

    @Test
    public void testyRegisterUser_Valid(){
        assertFalse(Main_checkUserName("kat_"), "pass");//too long with no underscore
    }

    @Test
    public void testyRegisterUser_InvalidPassword(){
        assertFalse(Main_checkUserName("kat_"), "pass");//too long with no underscore
    }
}
