package gp.jose.practice.reserveration.meetindAndBooking.model;

public interface UserInterface {
    Long getUserId();
    String getFIO();
    boolean equals(String login, String password);
    String getLogin(String login);
}
