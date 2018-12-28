package s4n.week1.challenge;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Streams {

    /**
     * List that constains persons object
     */
    List<Person> persons;

    /**
     * Constructor
     */
    public Streams(){
        persons =  new ArrayList<>();
    }

    /**
     * Method that add a new person to the persons list
     * @param person
     */
    public void addPerson(Person person){
        persons.add(person);
    }

    /**
     * Method that removes a persons from the list
     * @param person
     */
    public void removePerson (Person person){
        persons.remove(person);
    }

    /**
     * Method that find a person with the param value
     * @param name
     * @return
     */
    public List<Person> findByName(String name){

        return persons.stream().filter(p->p.getName().get().equals(name)).collect(Collectors.toList());

    }

    /**
     * Method that find all persons which names start with the param value
     * @param letter
     * @return
     */
    public List<Person> filterByLetter(String letter){

        return persons.stream().filter(p->p.getName().get().startsWith(letter)).collect(Collectors.toList());

    }

    /**
     * Method that calculates the averange income from the persons list
     * @return Averange income of all the persons
     */
    public double averangeIncome(){
        return persons.parallelStream().map(p->p.income).collect(Collectors.toList()).parallelStream().mapToInt(Integer::intValue).average().getAsDouble();

    }

    /**
     * Method that return the max income from the persons list
     * @return person with the max income
     */
    public double maxIncomePerson(){
        return persons.parallelStream().map(p->p.income).mapToInt(Integer::intValue).max().getAsInt();
    }

    /**
     * Method that return the general statics of the income of all the persons in the persons list
     * @return count, max, min, average and sum of the income
     */
    public IntSummaryStatistics incomeStatics(){
        return persons.stream().collect(Collectors.summarizingInt(p->p.income));
    }

    /**
     * Method that returns a phrase  with the name of the persons that are older that a specific age
     * @param age
     * @return name of the persons
     */
    public String phraseStatics(int age){
       return persons.parallelStream().filter(p->p.age>age).count()>0 ? persons.parallelStream().filter(p->p.age>age).map(p->p.name.get()).collect(Collectors.joining(", ",""," are older than "+age)):"PERSONS NOT FOUNT";
    }

    /**
     * Method that returns a map with the combination ,name and age, of all the persons in the persons list
     * @return Names anf ages of all persons
     */
    public Map<String,Integer> ageByPerson(){
        return persons.stream().collect(Collectors.toMap(p->p.name.get(), p->p.age));
    }

    /**
     * Method that returns the personal data of a person
     * @param name
     * @return name, lastname and age of a person
     */
    public String allPersonsDataAsString(String name){
        return persons.parallelStream().filter(p->p.getName().get().equals(name)).collect(Collector.of(()->new StringJoiner("|"),(j, p)->j.add("NAME: "+p.name.get()+" LASTNAME: "+p.lastName.orElse("NOT AVALAIBLE")+" AGE: "+p.age),(j1, j2)->j1.merge(j2),StringJoiner::toString));
    }

    /**
     * Method that returns the sum of all the incomes
     * @return the total income
     */
    public int totalIncome(){
        return persons.parallelStream().map(p->p.income).reduce((x, y)->x+y).get();
    }

    /*
    *GETTERS
     */
    public List<Person> getPersons() {
        return persons;
    }
}
