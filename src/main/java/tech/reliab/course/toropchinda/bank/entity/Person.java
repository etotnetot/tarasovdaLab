package tech.reliab.course.toropchinda.bank.entity;

import java.time.LocalDate;

public abstract class Person {
    protected int id;
    protected String fullName;
    protected LocalDate birthDate;

    /**
     * Конструктор класса Person
     * @param fullName ФИО человека
     * @param birthDate Дата рождения человека
     */
    public Person(String fullName, LocalDate birthDate) {
        this.fullName = fullName;
        this.birthDate = birthDate;
    }

    public int getId() { return this.id; }

    public String getFullName() { return this.fullName; }
}