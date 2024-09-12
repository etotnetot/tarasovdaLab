package tech.reliab.course.toropchinda.bank.service.impl;

import tech.reliab.course.toropchinda.bank.entity.BankOffice;
import tech.reliab.course.toropchinda.bank.service.BankOfficeService;

public class BankOfficeServiceImpl implements BankOfficeService {
    private BankOffice office;

    /**
     * Создание нового офиса.
     */
    @Override
    public void create(BankOffice newUser) {
        this.office = newUser;
    }

    /**
     * Чтение данных офиса.
     * @param id Идентификатор офиса
     */
    @Override
    public BankOffice read(int id) {
        if (this.office != null && this.office.getId() == id) {
            return office;
        } else {
            return null;
        }
    }

    /**
     * Обновление данных офиса.
     */
    @Override
    public void update(BankOffice newUser) {
        if (this.office != null && this.office.getId() == newUser.getId()) {
            this.office = newUser;
        }
    }

    /**
     * Удаление офиса.
     * @param id Идентификатор офиса
     */
    @Override
    public void delete(int id) {
        if (this.office != null && this.office.getId() == id) {
            this.office = null;
        }
    }
}