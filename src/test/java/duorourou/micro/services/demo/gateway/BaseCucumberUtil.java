package duorourou.micro.services.demo.gateway;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.List;

public class BaseCucumberUtil<T> {

    private TestRestTemplate template;
    private Class<T> clazz;

    private BaseCucumberUtil(TestRestTemplate template, Class<T> tClass) {
        this.template = template;
        this.clazz = tClass;
    }

    public static <T> BaseCucumberUtil buid(TestRestTemplate template, Class<T> tClass) {
        return new BaseCucumberUtil<>(template, tClass);
    }

    public ResponseEntity<T> get(String url) {
        return template.exchange(url, HttpMethod.GET, null, clazz);
    }

    public List<T> list() {
        return null;
    }

    public T post() {
        return null;
    }

    public List<T> postCollection() {

        return null;
    }

    public T put() {

        return null;
    }

    public List<T> putCollection() {

        return null;
    }

    public void delete(T toDelete) {

    }

    public void delete(Collection<T> toDelete) {

    }

}
