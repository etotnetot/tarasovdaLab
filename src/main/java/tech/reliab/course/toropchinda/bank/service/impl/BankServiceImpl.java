package tech.reliab.course.toropchinda.bank.service.impl;

import tech.reliab.course.toropchinda.bank.entity.Bank;
import tech.reliab.course.toropchinda.bank.service.BankService;

public class BankServiceImpl implements BankService {
    private Bank bank;

    /**
     * Создание нового банка.
     */
    @Override
    public void create(Bank newUser) {
        this.bank = newUser;
    }

    /**
     * Чтение данных банка.
     * @param id Идентификатор банка
     */
    @Override
    public Bank read(int id) {
        if (this.bank != null && this.bank.getId() == id) {
            return bank;
        } else {
            return null;
        }
    }

    /**
     * Обновление данных банка.
     */
    @Override
    public void update(Bank newUser) {
        if (this.bank != null && this.bank.getId() == newUser.getId()) {
            this.bank = newUser;
        }
    }

    /**
     * Удаление банка.
     * @param id Идентификатор банка
     */
    @Override
    public void delete(int id) {
        if (this.bank != null && this.bank.getId() == id) {
            this.bank = null;
        }
    }

    /**
     * Добавление офиса.
     */
    public void addOffice() {
        this.bank.officesCount++;
    }

    /**
     * Добавление банкомата.
     */
    public void addAtm() {
        this.bank.atmsCount++;
    }

    /**
     * Удаление банкомата.
     */
    public void removeAtm() {
        this.bank.atmsCount--;
    }
}