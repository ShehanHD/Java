package combinatorpattern;

import java.time.LocalDate;
import java.time.Period;

public class CustomerValidatorService {
    public boolean isValid(Customer customer){
        return isEmailValid(customer.getEmail()) && isAdult(customer.getDob()) && isPhoneNumberValid(customer.getPhoneNumber());
    }

    private boolean isEmailValid(String email){
        return email.contains("@");
    }

    private boolean isPhoneNumberValid(String phoneNumber){
        return phoneNumber.contains("+39");
    }

    private boolean isAdult(LocalDate dob){
        return Period.between(dob, LocalDate.now()).getYears() > 18;
    }
}
