package tech.reliab.course.toropchinda.bank.service;

import tech.reliab.course.toropchinda.bank.entity.Bank;

public interface BankService extends CrudOperations<Bank> {
    void addOffice();

    void addAtm();

    void removeAtm();
}