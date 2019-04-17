/*package json;

public class Assignee {

    String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}*/

package json;

public class Assignee implements Pojo {
    public String name;

    public Assignee(String id) {
        this.name = id;
    }

}
