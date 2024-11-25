package tech.reliab.course.toropchinda.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tech.reliab.course.toropchinda.bank.entity.*;
import tech.reliab.course.toropchinda.bank.service.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

@SpringBootApplication
public class TBankApplication implements CommandLineRunner {
    private static Integer totalBanks = 5;

    @Autowired
    private UserService userService;
    @Autowired
    private BankService bankService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private AtmService atmService;
    @Autowired
    private PaymentAccountService paymentAccountService;
    @Autowired
    private CreditAccountService creditAccountService;
    @Autowired
    private BankOfficeService officeService;

    public static void main(String[] args) {
        SpringApplication.run(TBankApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var banks = IntStream.range(0, totalBanks)
                .mapToObj(idx -> {
                    String bankName = String.format("Мега Банк %d", idx);
                    int id = new Random().nextInt(Integer.MAX_VALUE);
                    int rating = new Random().nextInt(101);
                    double totalMoney = new Random().nextInt(1_000_001);
                    double interestRate = 20 - (rating / 10.0);

                    return new Bank(id, bankName, rating, totalMoney, interestRate);
                })
                .toList();

        banks.forEach(this::initializeBankDetails);

        System.out.println("Выберите опцию:");
        System.out.println("1. Просмотреть банк");
        System.out.println("2. Просмотреть информацию о пользователе");

        var scanner = new Scanner(System.in);
        var choice = scanner.nextInt();

        switch (choice) {
            case 1 -> displayBankInfo();
            case 2 -> displayUserInfo();
            case 3 -> System.out.println("Выход");
            default -> System.out.println("Некоректный ввод");
        }
    }

    private void displayBankInfo() {
        var banks = bankService.getAllBanks();

        for (var bank : banks) {
            System.out.println(bank);
        }

        var scanner = new Scanner(System.in);

        System.out.println("Введите ID банка");
        var bankId = scanner.nextInt();

        var selectedBank = bankService.getBankById(bankId);

        System.out.println(selectedBank);

        System.out.println("Банкоматы:");
        System.out.println(atmService.getAllAtmsByBank(selectedBank));

        System.out.println("Офисы:");
        System.out.println(officeService.getAllBankOfficesByBank(selectedBank));

        System.out.println("Сотрудники:");
        System.out.println(employeeService.getAllEmployeesByBank(selectedBank));

        System.out.println("Клиенты:");
        System.out.println(userService.getUsersByBank(selectedBank));
    }

    private void displayUserInfo() {
        var users = userService.getAllUsers();
        for (var user : users) {
            System.out.println(user);
        }

        var scanner = new Scanner(System.in);

        System.out.println("Введите ID пользователя");
        var userId = scanner.nextInt();
        var user = userService.getUserById(userId);

        if (user.isEmpty()) return;

        var creditAccounts = creditAccountService.getCreditAccountByUserId(user.get().getId());
        var paymentAccounts = paymentAccountService.getAllPaymentAccountsByUserId(user.get().getId());

        System.out.println("Кредитные счета:");
        System.out.println(creditAccounts);

        System.out.println("Платежные счета:");
        System.out.println(paymentAccounts);
    }

    private void initializeBankDetails(Bank bank) {
        bankService.registerBank(bank);
        int numEmployees = 5;
        int numOffices = 5;
        int numAtms = 5;

        List<User> clients = Arrays.asList(
                userService.create("Павел Иванович", LocalDate.now(), "Разработчик"),
                userService.create("Анна Сергеевна", LocalDate.now(), "Тестировщик"),
                userService.create("Игорь Петрович", LocalDate.now(), "Аналитик")
        );

        var offices = IntStream.range(0, numOffices).boxed().map((idx) -> officeService.createBankOffice(
                String.format("Главный офис %d", idx),
                "Ленинский проспект 30",
                true,
                true,
                true,
                true,
                700,
                bank
        )).toList();

        var employees = IntStream.range(0, numEmployees).boxed().map((idx) -> employeeService.createEmployee(
                String.format("Смирнова Татьяна%d Алексеевна", idx),
                LocalDate.now(),
                "Сотрудник банка",
                bank,
                false,
                offices.get(idx % offices.size()),
                true,
                40000
        )).toList();

        var atms = IntStream.range(0, numAtms).boxed().map((idx) ->
                atmService.createBankAtm(
                        "Надежный банкомат",
                        "Ленинский проспект 30",
                        bank,
                        String.valueOf(offices.get(idx % offices.size())),
                        employees.get(idx % employees.size()),
                        true,
                        true,
                        1000
                )).toList();

        for (var client : clients) {
            PaymentAccount payAccount = paymentAccountService.createPaymentAccount(client, bank);

            CreditAccount creditAcc = creditAccountService.createCreditAccount(
                    client,
                    bank,
                    LocalDate.now(),
                    10,
                    700000,
                    15,
                    employees.get(client.hashCode() % employees.size()),
                    payAccount
            );

            paymentAccountService.createPaymentAccount(client, bank);

            creditAccountService.createCreditAccount(
                    client,
                    bank,
                    LocalDate.now(),
                    18,
                    60000,
                    13,
                    employees.get(client.hashCode() % employees.size()),
                    payAccount
            );
        }
    }
}