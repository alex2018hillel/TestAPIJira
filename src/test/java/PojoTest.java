import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import json.Fields;
import json.Issue;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;

public class PojoTest {

    @Test
    public void sampleTest() {
        ObjectMapper mapper = new ObjectMapper();

        Fields fields = new Fields();
        fields.setAssignee("Alex");
        fields.setIssueType("Test");
        fields.setProject("QA-7");
        fields.setSummary("Issue summary from the Automation Test");

        Issue issue = new Issue(fields);

        String jsonInString = "";
        try {
            jsonInString = mapper.writeValueAsString(issue);
            try {
                mapper.writeValue(new File("/Users/IdeaProjects/TestAPIJira/test.json"), issue);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(jsonInString);
    }

}


   /* @Test
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
*/