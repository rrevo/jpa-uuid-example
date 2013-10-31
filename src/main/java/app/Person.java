package app;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.TypeDef;

@Entity
@TypeDef(name = "uuid", defaultForType = UUID.class, typeClass = UuidType.class)
public class Person {

   @Id
   @GeneratedValue(generator = "uuid-gen")
   @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
   @Column(name = "id", columnDefinition = "uuid")
   private UUID id;

   private String firstName;
   private String lastName;

   protected Person() {
   }

   public Person(String firstName, String lastName) {
      this.firstName = firstName;
      this.lastName = lastName;
   }

   public UUID getId() {
      return id;
   }

   public String getFirstName() {
      return firstName;
   }

   public String getLastName() {
      return lastName;
   }

   @Override
   public String toString() {
      return String
            .format("Person[id=%s, firstName='%s', lastName='%s']", id, firstName, lastName);
   }

}
