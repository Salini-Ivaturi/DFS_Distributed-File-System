package testing;

import dfs.*;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class MessageHandlerTest {
	@Test
	public void testConstructor() {
		try {
			MessageHandler testMessageHandler = new MessageHandler("localhost");
			File testFile = new File("missing.jpg");
			testMessageHandler.SendFile(testFile, "localhost");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
