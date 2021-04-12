package finals;

import java.util.function.Function;

public class Lambdas {
    public static void main(String[] args) {
        Function<String, String > printName = name -> {
            if(name.isBlank()) throw new IllegalArgumentException("error");
            return name.toUpperCase();
        };

        System.out.println(printName.apply(""));
    }
}
