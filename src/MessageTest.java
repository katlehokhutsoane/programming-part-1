import static org.junit.jupiter.api.Assertions.*;

// MessageTest.java ..
import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;

public class MessageTest {

    @Test
    public void testCheckMessageID_Valid() {
        assertTrue(Message.checkMessageID("Hi Mike, can you join us for dinner tonight?"));
    }

    @Test
    public void testCheckMessageID_Invalid() {
        assertFalse(Message.checkMessageID("This is a very long message that exceeds the fifty character limit and should fail validation"));
    }

    @Test
    public void testCheckRecipientCell_Valid() {
        assertTrue(Message.checkRecipientCell("+27718693002"));
    }

    @Test
    public void testCheckRecipientCell_Invalid() {
        assertFalse(Message.checkRecipientCell("08575975889"));
    }

    @Test
    public void testCreateMessageHash() {
        Message message = new Message();
        String hash = message.createMessageHash("1234567890", "Hi Mike");
        String expected = "12:1:HI:MIKE";
        assertEquals(expected, hash);
    }

    @Test
    public void testReturnTotalMessagesSent() {
        Message message = new Message();
        assertEquals(0, message.returnTotalMessagesSent());
    }
}