package eventorganiser.dir;

import eventorganiser.dir.DBMethods.DBClasses.Event;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import eventorganiser.dir.DBMethods.DBClasses.User;

public class JUnit_Tests {

    private static User user;
    private static Event event;

    @BeforeAll
    public static void before() {
        user = new User();
        event = new Event();
    }

    // Get From user class
    @Test
    public void testGetter_getLastName() throws NoSuchFieldException, IllegalAccessException {

        //given
        final Field field = user.getClass().getDeclaredField("lastName");
        field.setAccessible(true);
        field.set(user, "Leite");

        //when
        final String result = user.getLastName();

        //then
        assertEquals("Leite", result, "Field wasn't retrieved properly");

    }


    //Set from User class

    @Test
    public void testSetter_setFirstName() throws NoSuchFieldException, IllegalAccessException  {

        //When
        user.setFirstName("Daniel");

        //then
        final Field field = user.getClass().getDeclaredField("firstName");
        field.setAccessible(true);
        assertEquals("Daniel", field.get(user),"Fields didn't match");


    }

    // Get From Event class
    @Test
    public void testGetter_getEventName() throws NoSuchFieldException, IllegalAccessException {

        //given
        final Field field = event.getClass().getDeclaredField("title");
        field.setAccessible(true);
        field.set(event, "Music Fest");

        //when
        final String result = event.getTitle();

        //then
        assertEquals("Music Fest", result, "Field wasn't retrieved properly");

    }


    //Set from Event class

    @Test
    public void testSetter_setEventLocCity() throws NoSuchFieldException, IllegalAccessException  {

        //When
        event.setEventLocCity("Cardiff");

        //then
        final Field field = event.getClass().getDeclaredField("eventLocCity");
        field.setAccessible(true);
        assertEquals("Cardiff", field.get(event),"Fields didn't match");


    }






}
