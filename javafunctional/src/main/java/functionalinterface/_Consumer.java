package functionalinterface;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class _Consumer {
    public static void main(String[] args) {
        Customer maria = new Customer("Maria", "999999999");

        // Normal
        greetCustomer(maria);

        // Consumer
        greetCustomerConsumer.accept(maria);

        // BiConsumer
        greetCustomerConsumerV2.accept(maria, false);
    }

    static Consumer<Customer> greetCustomerConsumer = customer -> System.out.println("Hello " + customer.customerName + "; Pn: " + customer.customerPhoneNumber);

    static BiConsumer<Customer, Boolean> greetCustomerConsumerV2 = (customer, showPhoneNumber) -> System.out.println("Hello " + customer.customerName + "; Pn: " + (showPhoneNumber ? customer.customerPhoneNumber : "******"));

    static void greetCustomer(Customer customer){
        System.out.println("Hello " + customer.customerName + "; Pn: " + customer.customerPhoneNumber);
    }

    static class Customer{
        private final String customerName;
        private final String customerPhoneNumber;

        public Customer(String customerName, String customerPhoneNumber) {
            this.customerName = customerName;
            this.customerPhoneNumber = customerPhoneNumber;
        }
    }
}
