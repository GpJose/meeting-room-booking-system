package gp.jose.practice.reserveration.meetindAndBooking.service;

import gp.jose.practice.reserveration.meetindAndBooking.model.enums.ActionsEnum;

import java.util.Scanner;

public interface ControlInterface {
    void action(ActionsEnum action, Scanner in);
}
