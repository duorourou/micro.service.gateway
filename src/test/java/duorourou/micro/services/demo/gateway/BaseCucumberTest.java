package duorourou.micro.services.demo.gateway;


import cucumber.api.java8.En;
import lombok.Getter;
import lombok.Setter;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = GatewayApp.class, loader = SpringBootContextLoader.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(properties = "server.port=9999")
@Getter
@Setter
public class BaseCucumberTest implements En {
    private static final String BASE_URL = "http://127.0.0.1:9999";

    @Autowired
    private TestRestTemplate template;

    public <T> ResponseEntity<T> get(String url, Class<T> tClass) {
        return BaseCucumberUtil.buid(template, tClass).get(BASE_URL + url);
    }


}