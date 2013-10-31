package app;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonService {

   @Autowired
   private PersonRepository repository;

   public List<UUID> create() {
      repository.save(new Person("Frodo", "Baggins"));
      repository.save(new Person("Samwise", "Gamgee"));
      repository.save(new Person("Peregrin", "Took"));
      repository.save(new Person("Meriadoc", "Brandybuck"));
      repository.save(new Person("Bilbo", "Baggins"));

      List<UUID> ids = new ArrayList<UUID>();
      for (Person p : repository.findAll()) {
         ids.add(p.getId());
      }
      return ids;
   }

   public List<Person> findAll() {
      List<Person> people = new ArrayList<Person>();
      for (Person p : repository.findAll()) {
         people.add(p);
      }
      return people;
   }

   public void deleteAll() {
      repository.deleteAll();
   }

}
