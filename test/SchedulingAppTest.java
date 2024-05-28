import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashSet;
import java.util.Set;

public class SchedulingAppTest {

    @Test
    public void testCreatePerson() {
        SchedulingApp app = new SchedulingApp();

        String result = app.createPerson("email1@example.com", "Person One");
        assertEquals("Person added with email email1@example.com and name Person One", result);
    }

    @Test
    public void testCreatePersonDuplicate() {
        SchedulingApp app = new SchedulingApp();

        String result = app.createPerson("email1@example.com", "Person One");
        assertEquals("Person added with email email1@example.com and name Person One", result);

        result = app.createPerson("email1@example.com", "Person Duplicate");
        assertEquals("Person with email email1@example.com already exists.", result);
    }

    @Test
    public void testGetPersonExists() {
        SchedulingApp app = new SchedulingApp();

        app.createPerson("email1@example.com", "Person One");
        Person person = app.getPerson("email1@example.com");
        assertNotNull(person);
        assertEquals("email1@example.com", person.getEmail());
        assertEquals("Person One", person.getName());
    }

    @Test
    public void testGetPersonDoesNotExist() {
        SchedulingApp app = new SchedulingApp();

        Person nonExistentPerson = app.getPerson("nonexistent@example.com");
        assertNull(nonExistentPerson);
    }

    @Test
    public void testCreateMeetingPersonsExist() {
        SchedulingApp app = new SchedulingApp();
        TimeSlot timeSlot1 = new TimeSlot(2024, 5, 27, 10);

        app.createPerson("email1@example.com", "Person One");
        app.createPerson("email2@example.com", "Person Two");
        Person person1 = app.getPerson("email1@example.com");
        Person person2 = app.getPerson("email2@example.com");

        Set<Person> attendees = new HashSet<>();
        attendees.add(person1);
        attendees.add(person2);

        String result = app.createMeeting(attendees, timeSlot1);
        assertEquals("Meeting created with persons " + attendees + " at " + timeSlot1, result);
    }

    @Test
    public void testCreateMeetingPersonDoesNotExist() {
        SchedulingApp app = new SchedulingApp();
        TimeSlot timeSlot1 = new TimeSlot(2024, 5, 27, 10);


        Person person1 = new Person("email1@example.com", "Person One");
        Set<Person> attendees = new HashSet<>();
        attendees.add(person1);

        String result = app.createMeeting(attendees, timeSlot1);
        assertEquals("Person Person One does not exist.", result);
    }

    @Test
    public void testGetSchedule() {
        SchedulingApp app = new SchedulingApp();
        TimeSlot timeSlot1 = new TimeSlot(2024, 5, 27, 10);
        TimeSlot timeSlot2 = new TimeSlot(2024, 5, 27, 11);

        app.createPerson("email1@example.com", "Person One");
        app.createPerson("email2@example.com", "Person Two");
        Person person1 = app.getPerson("email1@example.com");
        Person person2 = app.getPerson("email2@example.com");

        Set<Person> attendees1 = new HashSet<>();
        attendees1.add(person1);

        Set<Person> attendees2 = new HashSet<>();
        attendees2.add(person1);
        attendees2.add(person2);

        app.createMeeting(attendees1, timeSlot1);
        app.createMeeting(attendees2, timeSlot2);

        Set<Meeting> schedule1 = app.getSchedule(person1);
        assertEquals(2, schedule1.size());

        Set<Meeting> schedule2 = app.getSchedule(person2);
        assertEquals(1, schedule2.size());
    }


    @Test
    public void testSuggestTimeSlots() {
        SchedulingApp app = new SchedulingApp();
        TimeSlot timeSlot1 = new TimeSlot(2024, 5, 27, 8);
        TimeSlot timeSlot2 = new TimeSlot(2024, 5, 27, 9);
        TimeSlot timeSlot3 = new TimeSlot(2024, 5, 27, 10);
        TimeSlot timeSlot4 = new TimeSlot(2024, 5, 27, 11);
        TimeSlot timeSlot5 = new TimeSlot(2024, 5, 27, 12);

        app.createPerson("email1@example.com", "Person One");
        app.createPerson("email2@example.com", "Person Two");
        Person person1 = app.getPerson("email1@example.com");
        Person person2 = app.getPerson("email2@example.com");

        Set<Person> attendees = new HashSet<>();
        attendees.add(person1);

        app.createMeeting(attendees, timeSlot1);
        app.createMeeting(attendees, timeSlot2);

        Set<TimeSlot> allSlots = new HashSet<>();
        allSlots.add(timeSlot1);
        allSlots.add(timeSlot2);
        allSlots.add(timeSlot3);
        allSlots.add(timeSlot4);
        allSlots.add(timeSlot5);

        Set<TimeSlot> suggestedSlots = app.suggestTimeSlots(attendees, allSlots);
        assertEquals(3, suggestedSlots.size());
        assertTrue(suggestedSlots.contains(timeSlot3));
        assertTrue(suggestedSlots.contains(timeSlot4));
        assertTrue(suggestedSlots.contains(timeSlot5));

        attendees.add(person2);
        app.createMeeting(attendees, timeSlot3);
        app.createMeeting(attendees, timeSlot4);
        app.createMeeting(attendees, timeSlot5);

        suggestedSlots = app.suggestTimeSlots(attendees, allSlots);
        assertEquals(0, suggestedSlots.size());
    }
}


