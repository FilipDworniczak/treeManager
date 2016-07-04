package pl.filipdworniczak.database.repository;

import org.springframework.data.repository.CrudRepository;
import pl.filipdworniczak.database.entity.Node;

/**
 * Created by Aniołek on 2016-07-02.
 */
public interface NodeRepository extends CrudRepository<Node, Long> {
}
