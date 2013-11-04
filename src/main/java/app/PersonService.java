package app;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonService {

   @Autowired
   private DataSource dataSource;

   @Autowired
   private PlatformTransactionManager transactionManager;

   @Autowired
   private PersonRepository repository;

   public Person create(Person person) {
      return repository.save(person);
   }

   public List<Person> findAll() {
      List<Person> people = new ArrayList<Person>();
      for (Person p : repository.findAll()) {
         people.add(p);
      }
      return people;
   }

   public List<Person> findByLastName(String lastName) {
      List<Person> people = repository.findByLastName(lastName);
      return people;
   }

   public void deleteAll() {
      repository.deleteAllInBatch();
   }

   public long count() {
      return repository.count();
   }

}
