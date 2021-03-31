package ua.kharkiv.syvolotskyi;

import org.junit.Assert;
import org.junit.Test;
import ua.kharkiv.syvolotskyi.utils.PasswordEncoder;

import static org.junit.Assert.*;

public class PasswordEncoderTest {

    @Test
    public void encode() {
        String result = "202CB962AC59075B964B07152D234B70";
        Assert.assertEquals(result, PasswordEncoder.encode("123"));
    }
}