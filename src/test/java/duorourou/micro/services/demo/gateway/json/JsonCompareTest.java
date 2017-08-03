package duorourou.micro.services.demo.gateway.json;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonCompareTest {

    @Test
    public void should_list_all_not_equal_key_when_compare_simple_json_string() throws Exception {

        String json1 = "{ \"a\" : 1, \"c\" : 2, \"b\" : \"33r\"}";
        String json2 = "{ \"a\" : 11, \"b\" : \"33r\", \"c\" : 2}";

        List<String> res = new JsonCompare().compare(json1, json2);

        assertThat(res).isNotEmpty().containsExactly("a");
    }

    @Test
    public void should_list_all_not_equal_key_when_compare_nested_json_string() throws Exception {

        String json1 = "{ \"0\" : 1, \"a\" : 1, \"c\" : { \"c1\" : 1 }, \"b\" : \"33r\", \"d\" : [ \"m\", \"2\"], " +
                "\"e.2\" :\"e2\", \"e\" : { \"2\" : \"e.2\"} }";
        String json2 = "{ \"a\" : 11, \"b\" : \"33r\", \"c\" : { \"c1\" : 2 }, \"d\" : [ \"m1\", \"2\"]}";

        List<String> res = new JsonCompare().compare(json1, json2);

        assertThat(res).isNotEmpty().contains("a", "c.c1", "e.2", "e", "0");
    }

}