package pages;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import json.JsonBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.JiraAPIActions;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class APITests {

    JsonBuilder jsonBuilder ;
    String cookie;
    String issueID = "QAAUT7-740";  //have to be exists

    @Test
    public void login() {
        //TODO - move login to base
        JiraAPIActions jiraAPIActions = new JiraAPIActions();
        Response response = jiraAPIActions.login(
                                PropertyReader.readValue("login")
                               ,PropertyReader.readValue("password"));
        cookie = response.then().extract().path("session.value");
        System.out.println(response.prettyPrint());
        Assert.assertTrue(response.statusCode() == 200);
    }

    @Test
    public void loginWrongPassword() {

        JiraAPIActions jiraAPIActions = new JiraAPIActions();
        Response response = jiraAPIActions.login(
                (PropertyReader.readValue("login"))
                ,"wrongpassword");
        Assert.assertTrue(response.prettyPrint().contains("Login failed"));
    }

    @Test
    public void loginWrongUserName() {
        JiraAPIActions jiraAPIActions = new JiraAPIActions();
        Response response = jiraAPIActions.login(
                "wronglogin"
                ,"wrongpassword");
        Assert.assertTrue(response.prettyPrint().contains("Login failed"));
    }

    @Test
    public void createIssue() {
        JiraAPIActions jiraAPIActions = new JiraAPIActions();
        Response response = jiraAPIActions.createIssue(
                "QAAUT7"
                ,"Summary test"
                ,"descr"
                ,"Bug");
        issueID = response.getBody().path("key");
        Assert.assertTrue(response.statusCode() == 201);
        //curl -D- -u Nuzhin_Ivan:test -X POST --data '{"fields":{"project":{"key": "QAAUT7"},"summary": "Summary test","description": "descr","issuetype": {"name": "Bug"}}}' -H "Content-Type: application/json"  http://jira.hillel.it:8080/rest/api/2/issue/
        //{"id":"48710","key":"QAAUT7-732","self":"http://jira.hillel.it:8080/rest/api/2/issue/48710"}
        deleteIssue();
        }

    @Test
    public void deleteIssue( ) {
        JiraAPIActions jiraAPIActions = new JiraAPIActions();
        Response response = jiraAPIActions.deleteIssue(issueID);
        Assert.assertTrue(response.statusCode() == 204);
        //curl -X DELETE -u Nuzhin_Ivan:test --header 'Accept: application/json' --url 'http://jira.hillel.it:8080/rest/api/2/issue/QAAUT7-729'
    }

    @Test
    public void updateIssue() {
        //TODO - add more params to update
        JiraAPIActions jiraAPIActions = new JiraAPIActions();
        Response response = jiraAPIActions.updateIssue(PropertyReader.readValue("login"),issueID);
        Response responseClearUP = jiraAPIActions.updateIssue("",issueID);
        System.out.println(responseClearUP.prettyPrint());

        Assert.assertTrue(response.statusCode() == 204);
        Assert.assertTrue(responseClearUP.statusCode() == 204);

        //curl -D- -u Nuzhin_Ivan:test -X PUT --data '{ "fields": { "assignee":{"name":"Nuzhin_Ivan"} }}' -H "Content-Type: application/json" http://jira.hillel.it:8080/rest/api/2/issue/QAAUT7-732
    }

    @Test
    public void addComment() {
        JiraAPIActions jiraAPIActions = new JiraAPIActions();
        Response response = jiraAPIActions.addComment("some text of comment", issueID);
        //TODO add clearup
        //System.out.println(response.prettyPrint());
        Assert.assertTrue(response.statusCode() == 204);

        //curl -D- -u Nuzhin_Ivan:test -X PUT -d '{"update": {"comment": [{"add": {"body": "Comment test"}}]}}' -H "Content-Type: application/json" http://jira.hillel.it:8080/rest/api/2/issue/QAAUT7-732
    }

    @Test
    public void deleteComment() {

    }

    @Test
    public void JQLSearchIssue() {
        JiraAPIActions jiraAPIActions = new JiraAPIActions();
        Response response = jiraAPIActions.JQLSerch("id=" + issueID);
        Assert.assertTrue(response.statusCode() == 200);
        Assert.assertTrue(response.prettyPrint().contains("\"total\": 1") );


        //not worked request!!! just as an example
        //curl -D- -u $usernameData:$passwordData -X GET -H "Content-Type: application/json" "http://jira.alm.mentorg.com:8080/rest/api/2/search?jql=project=HDS&maxResults=1000&fields=summary"
    }

    @Test
    public void getUser() {

        //curl  -u Nuzhin_Ivan:test   --header 'Accept: application/json'   --url 'http://jira.hillel.it:8080/rest/api/2/user?username=Nuzhin_Ivan&expand=groups,applicationRoles'

        //{"self":"http://jira.hillel.it:8080/rest/api/2/user?username=Nuzhin_Ivan","key":"nuzhin_ivan","name":"Nuzhin_Ivan","emailAddress":"nuzhin.ivan@gmail.com","avatarUrls":{"48x48":"https://www.gravatar.com/avatar/ee6c1b396807230ced8e053aca5e87c2?d=mm&s=48","24x24":"https://www.gravatar.com/avatar/ee6c1b396807230ced8e053aca5e87c2?d=mm&s=24","16x16":"https://www.gravatar.com/avatar/ee6c1b396807230ced8e053aca5e87c2?d=mm&s=16","32x32":"https://www.gravatar.com/avatar/ee6c1b396807230ced8e053aca5e87c2?d=mm&s=32"},"displayName":"Nuzhin_Ivan","active":true,"timeZone":"Europe/Kiev","locale":"en_US","groups":{"size":2,"items":[{"name":"jira-software-users","self":"http://jira.hillel.it:8080/rest/api/2/group?groupname=jira-software-users"},{"name":"webinar","self":"http://jira.hillel.it:8080/rest/api/2/group?groupname=webinar"}]},"applicationRoles":{"size":1,"items":[{"key":"jira-software","name":"JIRA Software"}]},"expand":"groups,applicationRoles"}

    }

    @Test
    public void getProject() {

        //get ALL projects
        //curl -u Nuzhin_Ivan:test   --header 'Accept: application/json'   --url 'http://jira.hillel.it:8080/rest/api/2/project'
    }

    @Test
    public void getGroups() {

    }

    @Test
    public void setPriority() {

    }


}