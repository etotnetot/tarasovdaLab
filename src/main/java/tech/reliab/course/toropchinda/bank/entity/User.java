package tech.reliab.course.toropchinda.bank.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User extends Person {
    protected String workplace;
    protected double monthlyIncome;
    protected int bankCount;
    protected int creditRating;
    private List<Bank> banks = new ArrayList<>();
    private List<CreditAccount> creditAccounts = new ArrayList<>();
    private List<PaymentAccount> paymentAccounts = new ArrayList<>();

    /**
     * Конструктор класса User
     * @param id ID клиента
     * @param fullName ФИО клиента
     * @param birthDate Дата рождения клиента
     * @param workplace Место работы клиента
     */
    public User(int id, String fullName, LocalDate birthDate, String workplace) {
        super(fullName, birthDate);
        this.workplace = workplace;
        this.monthlyIncome = Math.random() * 10000;
    }
    public int getId() { return this.id; }

    public double getMonthlyIncome() { return this.monthlyIncome; }

    public List<CreditAccount> getCreditAccounts() { return this.creditAccounts; }

    public List<PaymentAccount> getPaymentAccounts() { return this.paymentAccounts; }

    public List<Bank> getBanks() { return this.banks; }

    public void setCreditAccounts(List<CreditAccount> creditAccounts) {
        this.creditAccounts = creditAccounts;
    }

    public void setBanks(List<Bank> banks) {
        this.banks = banks;
    }

    public void setCreditRating(int creditRating) {
        this.creditRating = creditRating;
    }

    public void setPaymentAccounts(List<PaymentAccount> paymentAccounts) { this.paymentAccounts = paymentAccounts; }

    public void setId(int id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setMonthlyIncome(int income) { this.monthlyIncome = income; }

    /**
     * Переопределение метода toString для User
     * @return Данные обьекта
     */
    @Override
    public String toString() {
        return "Id клиента: " + this.id + "\n" +
                "ФИО: " + this.fullName + "\n" +
                "Дата рождения: " + this.birthDate + "\n" +
                "Место работы: " + this.workplace + "\n" +
                "Ежемесячный доход: " + String.format("%.2f", this.monthlyIncome) + "\n" +
                "Кредитный рейтинг для банка: " + this.creditRating + "\n" +
                "Банки, которыми он пользуется: " + this.bankCount + "\n" +
                "Кредитные счета: " + this.creditAccounts.size() + "\n" +
                "Платежные счета: " + this.paymentAccounts.size();
    }
}