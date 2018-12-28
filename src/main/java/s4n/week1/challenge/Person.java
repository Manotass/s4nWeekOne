package s4n.week1.challenge;

import java.util.Optional;

public class Person {

    /**
    * ATRIBUTS WITH OPTIONAL API
    * */
    Optional<String> name;
    Optional<String> lastName;
    Optional<String> id;
    String phone;
    int age;
    String sex;
    int income;

    /**
    * CONSTRUCTOR METHOD OF THE CLASS
    * */
    public Person(String name, String lastName, String id, String phone, int age, String sex, int income) {
        this.name = Optional.ofNullable(name);
        this.lastName = Optional.ofNullable(lastName);
        this.id = Optional.ofNullable(id);
        this.phone = phone;
        this.age = age;
        this.sex = sex;
        this.income = income;
    }

    /*GETTERS*/

    public Optional<String> getName() {
        return name;
    }

    public Optional<String> getLastName() {
        return lastName;
    }

    public Optional<String> getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public int getIncome() {
        return income;
    }

}
