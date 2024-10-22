package tech.reliab.course.toropchinda.bank.service;

import tech.reliab.course.toropchinda.bank.entity.User;

public interface UserService extends CrudOperations<User> {
    int calculateCreditRating();
}