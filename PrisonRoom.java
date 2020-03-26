/*
 * You are a prisoner in a high-tech prison and the day of your execution draws
 * near. Fourtunately, you have managed to find a way to install a backdoor in
 * one of the classes.
 *
 * There are little to no guards and access to all rooms is controlled by
 * keycards. Even prisoners, like you, have one. The prison is a real maze and
 * you don't know which escape route you'll take, so the only solution is to
 * grant yourself access to any room. Since you don't want to draw suspicion,
 * access control for others should work as before.
 *
 * Change KeyCardParser so that you'd be able to enter any room.
 *
 * Make your escape even cleaner:
 * Bonus points if parsing your keycard data still returns your name.
 * Extra bonus points if your name doesn't appear in the code.
 * Even more extra bonus points: It is quite possible that Room's toString()
 * is used in logs, make sure your name won't appear there unless your cell's
 * toString() is called.
 *
 * Don't worry, the test can contain your name explicitly. The test is provided
 * for convenience and your task is not to trick it into passing but to solve
 * the problem. Send your solution via a git repository link and explain how
 * your solution works. Please send your CV and solution to careers@icefire.ee.
 */

package ee.icefire.escape;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class PrisonRoom {

    private static Map<Person, PrisonRoom> cells;

    private int id;
    private List<PrisonRoom> neighbours = new ArrayList<>();
    private Set<Person> allowedPersons;

    public PrisonRoom(int id, HashSet<Person> allowedPersons) {
        this.id = id;
        this.allowedPersons = Collections.unmodifiableSet(allowedPersons);
    }

    public static Optional<PrisonRoom> getCellFor(Person person) {
        return Optional.ofNullable(cells.get(person));
    }

    public static void setCells(Map<Person, PrisonRoom> cells) {
        PrisonRoom.cells = cells;
    }

    public boolean allowsEntrance(Person person) {
        return allowedPersons.contains(person);
    }

    public int getId() {
        return id;
    }

    public List<PrisonRoom> getNeighbours() {
        return neighbours;
    }

    public String toString() {
        return "allowed persons:" + allowedPersons.toString();
    }

}

// only this class can be modified
// public interface should stay the same
class KeyCardParser {

    public Person read(String cardData) {
        String[] split = cardData.split(",");
        return new Person(split[0], split[1]);
    }

}

class Person {

    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Person person = (Person) o;

        if (!firstName.equals(person.firstName)) {
            return false;
        }
        return lastName.equals(person.lastName);
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
    }
}