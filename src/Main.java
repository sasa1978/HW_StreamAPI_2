import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            //for (int i = 0; i < 10; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        //persons.stream().forEach(System.out::println);

        long underageCount = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println("Людей младше 18 лет: " + underageCount);

        List<String> recruit = persons.stream()
                .filter(person -> person.getAge() >= 18 && person.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("Людей призывного возраста: " + recruit.size());
        //recruit.stream().forEach(System.out::println);

        List<Person> higherEducation = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER &&
                        person.getAge() >= 18 && (person.getSex() == Sex.MAN ? person.getAge() <= 65 : person.getAge() <= 60))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println("Людей рабочего возраста с высшим образованием: " + higherEducation.size());
        //higherEducation.stream().forEach(System.out::println);
    }
}
