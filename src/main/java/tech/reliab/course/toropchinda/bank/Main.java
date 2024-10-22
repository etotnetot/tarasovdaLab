package tech.reliab.course.toropchinda.bank;
import tech.reliab.course.toropchinda.bank.entity.*;
import tech.reliab.course.toropchinda.bank.service.*;
import tech.reliab.course.toropchinda.bank.service.impl.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank(1, "Sber");

        BankOffice office = new BankOffice(1, "Main Office", "123 Main St", bank, 10000);

        Employee employee = new Employee(1, "John Doe", LocalDate.of(1990, 1, 1),
                "Manager", bank, "manager", true , office, true, 50000);

        User user = new User(1, "Alice Smith", LocalDate.of(1985, 5, 15),
                "IT Company");

        PaymentAccount paymentAccount = new PaymentAccount(1, user, bank.getName(), bank);

        BankAtm atm = new BankAtm(1, "ATM-001", "Street 56", bank, "Office 45",
                employee, true, false, 1000);

        CreditAccount creditAccount = new CreditAccount(1, user, bank, LocalDate.of(2012, 5, 15),
                LocalDate.of(2017, 5, 15), 19, 100000, 500,
                2.4, employee, paymentAccount);

        BankService bankService = new BankServiceImpl();
        BankOfficeService bankOfficeService = new BankOfficeServiceImpl();
        AtmServiceImpl atmService = new AtmServiceImpl();
        EmployeeService employeeService = new EmployeeServiceImpl();
        UserService userService = new UserServiceImpl();
        PaymentAccountService paymentAccountService = new PaymentAccountServiceImpl();
        CreditAccountService creditAccountService = new CreditAccountServiceImpl();

        bankService.create(bank);
        bankOfficeService.create(office);
        atmService.create(atm);
        employeeService.create(employee);
        userService.create(user);
        paymentAccountService.create(paymentAccount);
        creditAccountService.create(creditAccount);

        System.out.println(bankService.read(1).toString());
        System.out.println(bankOfficeService.read(1).toString());
        System.out.println(atmService.read(1).toString());
        System.out.println(employeeService.read(1).toString());
        System.out.println(userService.read(1).toString());
        System.out.println(paymentAccountService.read(1).toString());
        System.out.println(creditAccountService.read(1).toString());
    }
}