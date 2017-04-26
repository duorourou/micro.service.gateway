package duorourou.micro.services.demo.gateway.encoder;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GatewayEncoderTest {

    GatewayEncoder encoder;

    @Before
    public void setUp() throws Exception {
        encoder = new GatewayEncoder();
    }

    @Test
    public void shouldEncodeStringAsItselfWhenEncodeString() throws Exception {
        String encodingResult = encoder.encode("abc");
        assertThat(encodingResult).isEqualTo("abc");
    }

}