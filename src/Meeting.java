import java.util.Set;
import java.util.HashSet;

public class Meeting {

    private Set<Person> persons;
    private TimeSlot timeSlot;

    public Meeting(Set<Person> persons, TimeSlot timeSlot) {
        this.persons = new HashSet<>(persons);
        this.timeSlot = timeSlot;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    @Override
    public String toString() {
        return "Meeting with persons " + persons + " at time " + timeSlot;
    }
}