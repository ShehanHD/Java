package streams;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class _Stream {
    public static void main(String[] args) {
        List<Person> people = List.of(
                new Person("John", Person.Gender.MALE),
                new Person("Maria", Person.Gender.FEMALE),
                new Person("Mark", Person.Gender.MALE),
                new Person("Dona", Person.Gender.FEMALE)
        );

        /*people.stream()
                .map(person -> person.gender)
                .collect(Collectors.toSet())
                .forEach(System.out::println);*/

        boolean onlyFemale = people.stream()
                .allMatch(person -> Person.Gender.FEMALE.equals(person.gender));

        System.out.println(onlyFemale);
    }

    static class Person{
        private final String name;
        private final Person.Gender gender;

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", gender=" + gender +
                    '}';
        }

        public Person(String name, Person.Gender gender) {
            this.name = name;
            this.gender = gender;
        }

        enum Gender{
            MALE, FEMALE
        }
    }
}
