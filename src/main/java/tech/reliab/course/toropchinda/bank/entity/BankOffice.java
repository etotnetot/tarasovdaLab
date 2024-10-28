package tech.reliab.course.toropchinda.bank.entity;

import tech.reliab.course.toropchinda.bank.enums.BankStatus;

public class BankOffice {
    private int id;
    private String name;
    private String address;
    private BankStatus status;
    private boolean canPlaceAtm;
    private int atmCount;
    private boolean canLoan;
    private boolean canWithdraw;
    private boolean canDeposit;
    private double balance;
    private double rentCost;
    private Bank bank;

    /**
     * Конструктор класса BankOffice
     * @param name Название офиса
     * @param address Адрес банковского офиса
     * @param bank Банк, которому принадлежит офис
     */
    public BankOffice(String name, String address, Bank bank, double rentCost) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.bank = bank;
        this.status = BankStatus.WORKING;
        this.canPlaceAtm = true;
        this.atmCount = 0;
        this.canLoan = true;
        this.canWithdraw = true;
        this.canDeposit = true;
        this.balance = bank.getTotalMoney();
        this.rentCost = rentCost;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(BankStatus status) {
        this.status = status;
    }

    public void setOfficeMoney(double money) {
        this.balance = money;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() { return this.id; }

    public int getBankId() { return this.bank.getId(); }

    public String getAddress() {
        return this.address;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Id банковского офиса: " + id + "\n" +
                "Название офиса: " + name + "\n" +
                "Адрес банковского офиса: " + address + "\n" +
                "Статус: " + status.name() + "\n" +
                "Можно разместить банкомат: " + (this.canPlaceAtm ? "да" : "нет") + "\n" +
                "Кол-во банкоматов в офисе: " + atmCount + "\n" +
                "Можно оформить кредит в данном офисе: " + canLoan + "\n" +
                "Работает на выдачу денег: " + canWithdraw + "\n" +
                "Можно внести деньги: " + canDeposit + "\n" +
                "Кол-во денег в банковском офисе: " + balance + "\n" +
                "Стоимость аренды банковского офиса: " + rentCost;
    }
}