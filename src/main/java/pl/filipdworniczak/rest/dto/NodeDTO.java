package pl.filipdworniczak.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aniołek on 2016-07-02.
 */
public class NodeDTO {
    private long id;
    private Integer value;
    private List<NodeDTO> children;

    public NodeDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void addChild(NodeDTO child) {
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        this.children.add(child);
    }

    public List<NodeDTO> getChildren() {
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        return children;
    }
}
