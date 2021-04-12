package imperative;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> people = List.of(
                new Person("John", Person.Gender.MALE),
                new Person("Maria", Person.Gender.FEMALE),
                new Person("Mark", Person.Gender.MALE),
                new Person("Dona", Person.Gender.FEMALE)
        );

        // Imperative approach
        System.out.println("Imperative approach");
        List<Person> females = new ArrayList<>();

        for (Person person: people){
            if (Person.Gender.FEMALE.equals(person.gender)){
                females.add(person);
            }
        }

        for (Person female: females){
            System.out.println(female);
        }

        // Declarative approach
        System.out.println("Declarative approach");
        people.stream()
                .filter(person -> Person.Gender.FEMALE.equals(person.gender))
                //.collect(Collectors.toList())
                .forEach(System.out::println);
    }

    static class Person{
        private final String name;
        private final Gender gender;

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", gender=" + gender +
                    '}';
        }

        public Person(String name, Gender gender) {
            this.name = name;
            this.gender = gender;
        }

        enum Gender{
            MALE, FEMALE
        }
    }
}
