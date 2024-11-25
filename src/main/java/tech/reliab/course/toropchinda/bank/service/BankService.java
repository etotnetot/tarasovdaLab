package tech.reliab.course.toropchinda.bank.service;

import tech.reliab.course.toropchinda.bank.entity.Bank;

import java.util.List;

public interface BankService {
    void registerBank(Bank bank);

    Bank getBankById(long id);

    List<Bank> getAllBanks();

    void deleteBank(long id);

    int addOffice(int id);

    int addAtm(int id);

    int addEmployee(int id);

    int addClient(int id);

    int removeOffice(int id);

    int removeAtm(int id);

    int removeEmployee(int id);

    int removeClient(int id);

    Bank getBankIfExists(int id);
}