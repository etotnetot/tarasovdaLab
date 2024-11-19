package tech.reliab.course.toropchinda.bank;
import tech.reliab.course.toropchinda.bank.entity.*;
import tech.reliab.course.toropchinda.bank.service.*;
import tech.reliab.course.toropchinda.bank.service.impl.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    private static List<String> userFirstNames = Arrays.asList("Дима", "Дмитрий", "Митя");
    private static List<String> userLastNames = Arrays.asList("Кальянов", "Пепсов", "Николаев");
    private static Integer totalBanks = 5;
    static UserService userSrv = new UserServiceImpl();
    static BankService bankSrv = new BankServiceImpl(userSrv);
    static EmployeeService empSrv = new EmployeeServiceImpl(bankSrv);
    static AtmService atmSrv = new AtmServiceImpl(bankSrv);
    static PaymentAccountService payAccSrv = new PaymentAccountServiceImpl(userSrv, bankSrv);
    static CreditAccountService creditAccSrv = new CreditAccountServiceImpl(userSrv, bankSrv);
    static BankOfficeService officeSrv = new BankOfficeServiceImpl(bankSrv);

    private static List<Bank> setupBanks() {
        return IntStream.range(0, totalBanks)
                .mapToObj(idx -> {
                    String bankName = String.format("Мега Банк %d", idx);
                    int id = new Random().nextInt(Integer.MAX_VALUE);
                    int rating = new Random().nextInt(101);
                    double totalMoney = new Random().nextInt(1_000_001);
                    double interestRate = 20 - (rating / 10.0);

                    return new Bank(id, bankName, rating, totalMoney, interestRate);
                })
                .toList();
    }

    public static void main(String[] args) throws IOException {
        var banks = setupBanks();
        banks.forEach(Main::initializeBankDetails);

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

    private static void displayBankInfo() {
        var banks = bankSrv.getAllBanks();

        for (var bank : banks) {
            System.out.println(bank);
        }

        var scanner = new Scanner(System.in);

        System.out.println("Введите ID банка");
        var bankId = scanner.nextInt();

        var selectedBank = bankSrv.getBankById(bankId);
        if (selectedBank.isEmpty())
            return;

        System.out.println(selectedBank.get());

        System.out.println("Банкоматы:");
        System.out.println(atmSrv.getAllAtmsByBank(selectedBank.get()));

        System.out.println("Офисы:");
        System.out.println(officeSrv.getAllBankOfficesByBank(selectedBank.get()));

        System.out.println("Сотрудники:");
        System.out.println(empSrv.getAllEmployeesByBank(selectedBank.get()));

        System.out.println("Клиенты:");
        System.out.println(userSrv.getUsersByBank(selectedBank.get()));
    }

    private static void displayUserInfo() {
        var users = userSrv.getAllUsers();
        for (var user : users) {
            System.out.println(user);
        }

        var scanner = new Scanner(System.in);

        System.out.println("Введите ID пользователя");
        var userId = scanner.nextInt();
        var user = userSrv.getUserById(userId);

        if (user.isEmpty()) return;

        var creditAccounts = creditAccSrv.getCreditAccountByUserId(user.get().getId());
        var paymentAccounts = payAccSrv.getAllPaymentAccountsByUserId(user.get().getId());

        System.out.println("Кредитные счета:");
        System.out.println(creditAccounts);

        System.out.println("Платежные счета:");
        System.out.println(paymentAccounts);
    }

    private static void initializeBankDetails(Bank bank) {
        bankSrv.registerBank(bank);
        int numEmployees = 5;
        int numOffices = 5;
        int numAtms = 5;

        List<User> clients = Arrays.asList(
                userSrv.create("Павел Иванович", LocalDate.now(), "Разработчик"),
                userSrv.create("Анна Сергеевна", LocalDate.now(), "Тестировщик"),
                userSrv.create("Игорь Петрович", LocalDate.now(), "Аналитик")
        );

        var offices = IntStream.range(0, numOffices).boxed().map((idx) -> officeSrv.createBankOffice(
                String.format("Главный офис %d", idx),
                "Ленинский проспект 30",
                true,
                true,
                true,
                true,
                700,
                bank
        )).toList();

        var employees = IntStream.range(0, numEmployees).boxed().map((idx) -> empSrv.createEmployee(
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
                atmSrv.createBankAtm(
                        "Надежный банкомат",
                        "Ленинский проспект 30",
                        bank,
                        offices.get(idx % offices.size()),
                        employees.get(idx % employees.size()),
                        true,
                        true,
                        1000
                )).toList();

        for (var client : clients) {
            PaymentAccount payAccount = payAccSrv.createPaymentAccount(client, bank);

            CreditAccount creditAcc = creditAccSrv.createCreditAccount(
                    client,
                    bank,
                    LocalDate.now(),
                    10,
                    700000,
                    15,
                    employees.get(client.hashCode() % employees.size()),
                    payAccount
            );

            payAccSrv.createPaymentAccount(client, bank);

            creditAccSrv.createCreditAccount(
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