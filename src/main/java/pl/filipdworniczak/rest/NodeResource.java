package pl.filipdworniczak.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.filipdworniczak.database.entity.Node;
import pl.filipdworniczak.database.repository.NodeRepository;
import pl.filipdworniczak.rest.dto.NodeDTO;
import pl.filipdworniczak.service.NodeService;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Aniołek on 2016-07-02.
 */

@RestController
@RequestMapping("/api")
public class NodeResource {

    @Inject
    private NodeRepository nodeRepository;

    @Inject
    private NodeService nodeService;

    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping(value = "/node",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NodeDTO> getRootNode() {
        Node node = nodeRepository.findOne(1L);
        NodeDTO nodeDTO = nodeService.transformToNodeDTO(node);
        return new ResponseEntity<>(nodeDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/node/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<NodeDTO> deleteNode(@PathVariable("id") long id) {
        Node node = nodeRepository.findOne(id);
        if(node != null) {
            nodeRepository.delete(id);
            entityManager.flush();
            entityManager.clear();
            System.out.println("Deleted node id: " + id);
        }

        Node rootNode = nodeRepository.findOne(1L);
        NodeDTO rootNodeDTO = nodeService.transformToNodeDTO(rootNode);
        return new ResponseEntity<>(rootNodeDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/node/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<NodeDTO> changeDesiredValue(@PathVariable("id") long id, @RequestParam(required = true, value = "value") int desiredValue) {
        Node node = nodeRepository.findOne(id);
        if(node != null) {
            node.setDesiredValue(desiredValue);
            entityManager.flush();
            entityManager.clear();
            System.out.println("Changed desiredValue id: " + id + ", new value: " + desiredValue);
        }

        Node rootNode = nodeRepository.findOne(1L);
        NodeDTO rootNodeDTO = nodeService.transformToNodeDTO(rootNode);
        return new ResponseEntity<>(rootNodeDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/node",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<NodeDTO> addNode(@RequestParam(required = true, value = "parentNodeId") long id, @RequestParam(required = true, value = "desiredValue") int desiredValue) {
        //Node node = nodeRepository.findOne(id);
        Node parentNode = nodeRepository.findOne(id);
        if(parentNode != null) {
            Node node = new Node(parentNode, desiredValue);
            nodeRepository.save(node);
            entityManager.flush();
            entityManager.clear();
            System.out.println("Saved node id: " + id + ",  desiredValue: " + desiredValue);
        }

        Node rootNode = nodeRepository.findOne(1L);
        NodeDTO rootNodeDTO = nodeService.transformToNodeDTO(rootNode);
        return new ResponseEntity<>(rootNodeDTO, HttpStatus.OK);
    }
}
