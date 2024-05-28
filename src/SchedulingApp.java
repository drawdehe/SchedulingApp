import java.util.Set;
import java.util.HashSet;

public class SchedulingApp {

    private Set<Person> persons;
    private Set<Meeting> meetings;

    public SchedulingApp() {
        this.persons = new HashSet<Person>();
        this.meetings = new HashSet<Meeting>();
    }

    // create a person with a name and unique email
    public String createPerson(String email, String name) {

        // check if email exists
        for (Person person : persons) {
            if (person.getEmail().equals(email)) {
                return "Person with email " + email + " already exists.";
            }
        }

        Person person = new Person(email, name);
        persons.add(person);
        return "Person added with email " + email + " and name " + name;
    }

    // get person by email
    public Person getPerson(String email) {
        for (Person person : persons) {
            if (person.getEmail().equals(email)) {
                return person;
            }
        }
        return null;
    }
    
    // create meetings involving one or more persons at a given time slot
    // a meeting can only start at the hour mark and only last exactly one hour
    public String createMeeting(Set<Person> persons, TimeSlot timeSlot) {

        // check if persons exist
        for (Person person : persons) {
            if (!this.persons.contains(person)) {
                return "Person " + person.getName() + " does not exist.";
            }
        }
    
        Meeting meeting = new Meeting(persons, timeSlot);
        meetings.add(meeting);
        return "Meeting created with persons " + persons + " at " + timeSlot;
    }

    // show the schedule, i.e., the upcoming meetings, for a given person
    public Set<Meeting> getSchedule(Person person) {
        Set<Meeting> schedule = new HashSet<Meeting>();
        for (Meeting meeting : meetings) {
            if (meeting.getPersons().contains(person)) {
                schedule.add(meeting);
            }
        }
        return schedule;
    }

    // suggest one or more available timeslots for meetings given a group of persons
    public Set<TimeSlot> suggestTimeSlots(Set<Person> persons, Set<TimeSlot> timeSlots) {
        Set<TimeSlot> availableTimeSlots = new HashSet<>(timeSlots);
        for (Meeting meeting : meetings) {
            for (Person person : meeting.getPersons()) {
                if (persons.contains(person)) {
                    availableTimeSlots.remove(meeting.getTimeSlot());
                    break;
                }
            }
        }
        return availableTimeSlots;
    }
}

