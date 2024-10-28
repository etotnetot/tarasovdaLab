package tech.reliab.course.toropchinda.bank.entity;

public class PaymentAccount {
    private int id;
    private User user;
    private String bankName;
    private double balance;
    private Bank bank;

    /**
     * Конструктор класса PaymentAccount
     * @param user Пользователь, за которым закреплен этот платежный счет
     * @param bank Банк, в котором открыт этот счет
     */
    public PaymentAccount(User user, Bank bank) {
        this.user = user;
        this.balance = 0;
        this.bank = bank;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() { return this.user; }

    public void setBank(Bank bank) { this.bank = bank; }

    /**
     * Переопределение метода toString() для аккаунта.
     * @return Информация об обьекте
     */
    @Override
    public String toString() {
        return "Id платёжного счета: " + this.id + "\n" +
                "Пользователь, за которым закреплен этот платежный счет: " + this.user.getFullName() + "\n" +
                "Название банка, в котором открыт этот счет: " + this.bank.getName() + "\n" +
                "Сумма, которая лежит в данный момент на счету: " + this.balance;
    }
}