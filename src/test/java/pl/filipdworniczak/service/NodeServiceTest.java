package pl.filipdworniczak.service;

import org.junit.Assert;
import org.junit.Test;
import pl.filipdworniczak.TreeManagerTests;
import pl.filipdworniczak.database.entity.Node;
import pl.filipdworniczak.database.repository.NodeRepository;
import pl.filipdworniczak.rest.dto.NodeDTO;

import javax.inject.Inject;

/**
 * Created by FilipDworniczak on 2016-07-06.
 */

public class NodeServiceTest extends TreeManagerTests {

    @Inject
    NodeService nodeService;

    @Inject
    private NodeRepository nodeRepository;

    @Test
    public void testTransformToNodeDTO() {
        Node node = nodeRepository.findOne(1L);
        NodeDTO nodeDTO = nodeService.transformToNodeDTO(node);
        Assert.assertEquals(node.getId(), nodeDTO.getId());
        Assert.assertEquals((int)node.getDesiredValue(), nodeDTO.getValue());//works only for roots and branches, because dto leafs values are just sum of ancestor values
        Assert.assertEquals(node.getChildren().size(), nodeDTO.getChildren().size());
        Assert.assertEquals((int)node.getChildren().iterator().next().getDesiredValue(), nodeDTO.getChildren().get(0).getValue());
    }

    @Test
    public void testIsLeafSuccess() {
        Node node = nodeRepository.findOne(5L);
        Assert.assertTrue(nodeService.isLeaf(node));
    }

    @Test
    public void testIsLeafWhenIsNode() {
        Node node = nodeRepository.findOne(4L);
        Assert.assertFalse(nodeService.isLeaf(node));
    }

    @Test
    public void testCountAncestorDesiredValues() {
        Node node = nodeRepository.findOne(6L);
        Assert.assertEquals(10, nodeService.countAncestorDesiredValues(node));
    }
}
