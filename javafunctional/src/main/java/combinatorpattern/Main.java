package combinatorpattern;

import java.time.LocalDate;

import static combinatorpattern.CustomerRegistrationValidator.*;

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer("Alice", "test@gmail.com", "+390000000", LocalDate.of(2001, 3, 12));

        //System.out.println(new CustomerValidatorService().isValid(customer));

        // With combinator

        ValidationResult result = isValidEmail()
                .and(isPhoneNumberValid())
                .and(isAdult())
                .apply(customer);

        System.out.println(result);
    }
}
