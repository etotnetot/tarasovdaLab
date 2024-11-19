package tech.reliab.course.toropchinda.bank.service;

import tech.reliab.course.toropchinda.bank.entity.*;

import java.util.List;
import java.util.Optional;

public interface AtmService {
    BankAtm createBankAtm(String name,
                          String address, Bank bank, BankOffice location, Employee employee,
                          boolean cashWithdrawal, boolean cashDeposit, double maintenanceCost);

    Optional<BankAtm> getAtmById(int id);

    List<BankAtm> getAllAtms();

    List<BankAtm> getAllAtmsByBank(Bank bank);
}