package pl.filipdworniczak.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.filipdworniczak.database.entity.Node;
import pl.filipdworniczak.rest.dto.NodeDTO;

/**
 * Created by Aniołek on 2016-07-03.
 */

@Service
@Transactional
public class NodeService {

    public NodeDTO transformToNodeDTO(Node node) {
        NodeDTO nodeDTO = new NodeDTO();

        if(node == null) {
            return null;
        }
        nodeDTO.setId(node.getId());
        if(this.isLeaf(node)) {
            nodeDTO.setValue(this.countAncestorDesiredValues(node));
        } else {
            nodeDTO.setValue(node.getDesiredValue());
        }
        for(Node child : node.getChildren()) {
            nodeDTO.addChild(transformToNodeDTO(child));
        }
        return nodeDTO;
    }

    public boolean isLeaf(Node node) {
        if(node.getChildren() == null || node.getChildren().size() == 0) {
            return true;
        }
        return false;
    }

    public int countAncestorDesiredValues(Node node) {
        int sum = 0;
        if(!this.isLeaf(node)) {
            sum += node.getDesiredValue();
        }
        Node parent = node.getParent();
        if(parent!= null) {
            sum += this.countAncestorDesiredValues(parent);
        }
        return sum;
    }
}
