import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import json.Assignee;
import org.testng.annotations.Test;

public class PojoSimpleTest {

    @Test
    public void sampleTest() {
        ObjectMapper objectMapper = new ObjectMapper();

        Assignee assignee = new Assignee();
        assignee.setName("Alex");
        String result = "";


        try {
            result = objectMapper.writeValueAsString(assignee);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(result);

    }


}
