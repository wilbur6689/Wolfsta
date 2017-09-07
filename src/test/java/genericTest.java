import org.junit.*;

import java.security.*;
import java.util.*;

public class genericTest
{
    @Test
    public void dummy()
    {
        assert(true); // super happy case!
    }

    @Test
    public void tokenLengthTest()
    {

        byte[] data = new byte[15];
        new SecureRandom().nextBytes(data);
        String token = Base64.getEncoder().encodeToString(data);
        System.out.println(token);
        assert(token.length() <= 20);
    }

}
