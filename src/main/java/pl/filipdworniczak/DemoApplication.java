package pl.filipdworniczak;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.filipdworniczak.database.entity.Node;
import pl.filipdworniczak.database.repository.NodeRepository;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(final NodeRepository repository) {
		return new CommandLineRunner() {
			@Override
			public void run(String... strings) throws Exception {

				Node node1 = new Node(null, 5);
				Node node2 = new Node(node1, 4);
				Node node3 = new Node(node1, 2);
				Node node4 = new Node(node3, 4);
				Node node5 = new Node(node3, 8);
				Node node6 = new Node(node4, 12);
				//repository.save(node1);
				repository.save(node1);
				repository.save(node2);
				repository.save(node3);
				repository.save(node4);
				repository.save(node5);
				repository.save(node6);
			}
		};
	}
}
