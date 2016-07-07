package pl.filipdworniczak.database.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by FilipDworniczak on 2016-07-02.
 */

@Entity
@Table(name="NODES")
public class Node {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "desired_value")
    private Integer desiredValue;

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
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
