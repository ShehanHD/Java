package functionalinterface;

import java.util.function.BiFunction;
import java.util.function.Function;

public class _Function {
    public static void main(String[] args) {
        // Function
        /*Integer incerment2 = incrementByOneFunction.apply(4);
        System.out.println(incerment2);*/

        Function<Integer, Integer> addAndMultiply = incrementByOneFunction.andThen(multiplyBy10);
        System.out.println(addAndMultiply.apply(4));

        // BiFunction
        System.out.println(incrementByOneAndMultiply.apply(8, 10));
    }

    static Function<Integer, Integer> incrementByOneFunction = number -> number + 1;

    static Function<Integer, Integer> multiplyBy10 = number -> number * 10;

    static BiFunction<Integer, Integer, Integer> incrementByOneAndMultiply = (numberToIncrement, numberToMultiply) -> (numberToIncrement + 1) * numberToMultiply;
}
