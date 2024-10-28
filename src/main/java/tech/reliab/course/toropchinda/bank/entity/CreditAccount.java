package tech.reliab.course.toropchinda.bank.entity;

import java.time.LocalDate;

public class CreditAccount {
    private int id;
    private User user;
    private String bankName;
    private LocalDate startDate;
    private LocalDate endDate;
    private int loanTermInMonths;
    private double loanAmount;
    private double monthlyPayment;
    private double interestRate;
    private Employee employee;
    private PaymentAccount paymentAccount;
    private Bank bank;

    /**
     * Конструктор класса CreditAccount
     * @param user Пользователь, за которым закреплен этот кредитный счет
     * @param startDate Дата начала кредита
     * @param loanTermInMonths Кол-во месяцев, на которые взят кредит
     * @param interestRate Процентная ставка
     * @param employee Сотрудник, который выдал кредит
     * @param paymentAccount Платежный счет в банке с которого будет осуществляться погашение данного кредита
     */
    public CreditAccount(User user, Bank bank, LocalDate startDate,
                         int loanTermInMonths, double interestRate,
                         Employee employee, PaymentAccount paymentAccount) {
        this.user = user;
        this.bankName = bank.getName();
        this.startDate = startDate;
        this.loanTermInMonths = loanTermInMonths;
        this.interestRate = bank.getInterestRate();
        this.employee = employee;
        this.paymentAccount = paymentAccount;
        this.bank = bank;
    }

    public int getId() { return this.id; }

    public User getUser() { return this.user; }

    public void setId(int id) { this.id = id; }

    public void setEndDate(LocalDate date) { this.endDate = date; }

    public void setLoanAmount(double amount) { this.loanAmount = amount; }

    public void setMonthlyPayment(double payment) { this.monthlyPayment = payment; }

    public void setInterestRate(double rate) { this.interestRate = rate; }

    public void setBank(Bank bank) { this.bank = bank; }

    /**
     * Переопределение метода toString() для аккаунта.
     * @return Информация об обьекте
     */
    @Override
    public String toString() {
        return "Информация о кредитном счете: " + this.id + ":\n" +
                "ID: " + this.id + "\n" +
                "Пользователь, за которым закреплен этот кредитный счет: " + this.user.getFullName() + "\n" +
                "Название банка, где взят кредит: " + this.bank.getName() + "\n" +
                "Дата начала кредита: " + this.startDate.toString() + "\n" +
                "Дата окончания кредита: " + this.endDate.toString() + "\n" +
                "Кол-во месяцев, на которые взят кредит: " + this.loanTermInMonths + "\n" +
                "Сумма кредита: " + this.loanAmount + "\n" +
                "Ежемесячный платеж: " + this.monthlyPayment + "\n" +
                "Процентная ставка: " + this.bank.getInterestRate() + "\n" +
                "Платежный счет в банке, с которого будет осуществляться погашение данного кредита: " + this.paymentAccount.getId();
    }
}