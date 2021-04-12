package functionalinterface;

import java.util.function.Supplier;

public class _Supplier {
    public static void main(String[] args) {
        System.out.println(getConnection());

        // Supplier
        System.out.println(getConnectionSupplier.get());
    }

    static String getConnection(){
        return "testConnectionUrl";
    }

    static Supplier<String> getConnectionSupplier = () -> "testConnectionUrl With Supplier";

}
