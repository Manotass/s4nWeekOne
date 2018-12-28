package s4n.week1.challenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class StreamsTest {

     Person person1;
     Person person2;
     Person person3;
     Person person4;
     Person person5;
     Streams streams;

    @BeforeEach
     void setup() {
        person1=new Person("Juan","Castilla","1","111111111",28,"M",1000000);
        person2=new Person("Andres",null,"2",null,30,"M",250000);
        person3=new Person("Jairo","Hermandez","3","222222222",32,"M",950000);
        person4=new Person("Juliana","Mejia","4","3333333333",19,"F",450000);
        person5=new Person("Andrea","Salamanca","5","333333333333",25,"F",985000);
        streams =new Streams();
        streams.addPerson(person1);
        streams.addPerson(person2);
        streams.addPerson(person3);
        streams.addPerson(person4);
        streams.addPerson(person5);
    }


        public StreamsTest(){

        }

    @Test
    void addPerson() {
        /*Test to proove Person objects are not null*/
        Stream.of(streams.getPersons()).forEach(p->{assertNotNull(p);});

        /*Test to proove that a person is been added correctly yo the persons list*/
        Person personTest=new Person("Person","ToAdd","6","00000000000",1,"F",0000000000);
        assertEquals(5, streams.getPersons().size());
        streams.addPerson(personTest);
        assertEquals(6, streams.getPersons().size());
        IntStream.range(1,9).forEach(i -> streams.addPerson(new Person("PersonMASSIVE","ToAdd","MASS"+i,"00000000000"+i,i,"F",0000000000+i)));
        assertEquals(14, streams.getPersons().size());
    }
    @Test
    void removePerson(){
        assertEquals(5, streams.getPersons().size());
        streams.removePerson(person1);//We verify that the remove is working properly
        assertEquals(4, streams.getPersons().size());
        IntStream.range(1,3).forEach(i -> streams.getPersons().remove(i));
        assertEquals(2, streams.getPersons().size());

    }


    @Test
    void findByName() {
        assertEquals("[Juan]", streams.findByName("Juan").stream().map(p->p.getName().get()).collect(Collectors.toList()).toString());
        assertEquals("[Andrea]", streams.findByName("Andrea").stream().map(p->p.getName().get()).collect(Collectors.toList()).toString());
        assertNotEquals("[No exist]", streams.findByName("No exist").stream().map(p->p.getName().get()).collect(Collectors.toList()).toString());
        assertEquals("[]",streams.findByName("No exist").stream().map(p->p.getName().get()).collect(Collectors.toList()).toString());
    }

    @Test
    void filterByLetter() {
        assertEquals("[Juan, Jairo, Juliana]", streams.filterByLetter("J").stream().map(p->p.getName().get()).collect(Collectors.toList()).toString());
        assertEquals("[Andres, Andrea]", streams.filterByLetter("Andre").stream().map(p->p.getName().get()).collect(Collectors.toList()).toString());
        assertNotEquals("[No exist]", streams.findByName("No exist").stream().map(p->p.getName().get()).collect(Collectors.toList()).toString());
    }

    @Test
    void averangeIncome() {
        assertEquals(727000, streams.averangeIncome());
    }

    @Test
    void maxIncomePerson() {
        assertEquals(1000000, streams.maxIncomePerson());
    }

    @Test
    void incomeStatics(){
        assertEquals(5, streams.incomeStatics().getCount());
        assertEquals(3635000, streams.incomeStatics().getSum());
        assertEquals(250000, streams.incomeStatics().getMin());
        assertEquals(727000, streams.incomeStatics().getAverage());
        assertEquals(1000000, streams.incomeStatics().getMax());
    }

    @Test
    void phraseStatics(){

            assertEquals("Juan, Andres, Jairo are older than 27", streams.phraseStatics(27));
            assertEquals("PERSONS NOT FOUNT", streams.phraseStatics(50));
    }

    @Test
    void ageByPerson(){
        Person personTest=new Person("Person","ToAdd","6","00000000000",1,"F",1000000);
        assertEquals("{Andres=30, Juan=28, Juliana=19, Jairo=32, Andrea=25}", streams.ageByPerson().toString());
        streams.addPerson(personTest);
        assertEquals("{Andres=30, Juan=28, Juliana=19, Person=1, Jairo=32, Andrea=25}", streams.ageByPerson().toString());
        streams.removePerson(person1);
        assertEquals("{Andres=30, Juliana=19, Person=1, Jairo=32, Andrea=25}", streams.ageByPerson().toString());
    }
    @Test
    void allPersonsDataAsString(){
        assertEquals("NAME: Juan LASTNAME: Castilla AGE: 28", streams.allPersonsDataAsString("Juan"));

        //Since lastname attribute is implemented with OPTIONAL API, in the case (like this one) that the lastname is null, the orElse changes it to "NOT AVAILABLE"
        assertEquals("NAME: Andres LASTNAME: NOT AVALAIBLE AGE: 30", streams.allPersonsDataAsString("Andres"));
    }

    @Test
    void totalIncome(){
        Person personTest=new Person("Person","ToAdd","6","00000000000",1,"F",1000000);
        assertEquals(3635000, streams.totalIncome());
        streams.addPerson(personTest);
        assertEquals(4635000, streams.totalIncome());
        streams.removePerson(person1);
        assertEquals(3635000, streams.totalIncome());
    }
}