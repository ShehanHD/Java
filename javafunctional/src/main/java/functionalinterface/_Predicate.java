package functionalinterface;

import java.util.function.Predicate;

public class _Predicate {
    public static void main(String[] args) {
        // Normal
        System.out.println(isPhoneNumberValid("+393802019965"));
        System.out.println(isPhoneNumberValid("+393802019965s"));

        // Predicate
        System.out.println(isPhoneNumberValidPredicate.test("+393802019965"));
        System.out.println(isPhoneNumberValidPredicate.and(containsNumber3Predicate).test("+393802019965"));
    }

    static boolean isPhoneNumberValid(String phoneNumber){
        return phoneNumber.startsWith("+39") && phoneNumber.length() == 13;
    }

    static Predicate<String> isPhoneNumberValidPredicate = phoneNumber -> phoneNumber.startsWith("+39") && phoneNumber.length() == 13;

    static Predicate<String> containsNumber3Predicate = phoneNumber -> phoneNumber.contains("3");
}
