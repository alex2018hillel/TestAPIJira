import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import json.Assignee;
import json.Fields;
import json.Issue;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;

public class TestApiJira {
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
