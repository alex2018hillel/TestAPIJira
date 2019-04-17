//package json;
//public class Fields implements Pojo{
//    String name;
//    public Project project;
//    public String project;}
package json;

import com.fasterxml.jackson.annotation.JsonProperty;
import json.Project;

public class Fields implements Pojo {

    public Project project;
    public String summary;
    @JsonProperty("issuetype")
    public IssueType issueType;
    public json.Assignee assignee;

    public Fields() {
    }

    public Fields setProject(String projectId) {
        this.project = new Project(projectId);
        return this;
    }

    public Fields setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public Fields setIssueType(String issueType) {
        this.issueType = new IssueType(issueType);
        return this;
    }

    public Fields setAssignee(String assignee) {
        this.assignee = new Assignee(assignee);
        return this;
    }

}
