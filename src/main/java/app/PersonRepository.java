package app;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, UUID> {

   List<Person> findByLastName(String lastName);
}
