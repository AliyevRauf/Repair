package repair.model;

import java.util.Objects;

/**
 * Created by User on 6/29/2018.
 */
public class Problem {
    int id;
    String name;

    public Problem() {
    }

    public Problem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "value=" + id +
                ", label='" + name + '\'' +
                '}';
    }

}
