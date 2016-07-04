package pl.filipdworniczak.database.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Aniołek on 2016-07-02.
 */

@Entity
public class Node {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Integer desiredValue;

    @ManyToOne
    private Node parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Collection<Node> children;

    protected Node() {}

    public Node(Node parent, Integer desiredValue) {
        this.parent = parent;
        this.desiredValue = desiredValue;
    }

    public long getId() {
        return id;
    }

    public Integer getDesiredValue() {
        return desiredValue;
    }

    public void setDesiredValue(Integer desiredValue) {
        this.desiredValue = desiredValue;
    }

    public Node getParent() {
        return parent;
    }

    public Collection<Node> getChildren() {
        return children;
    }
}
