import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * //intput
 * Given private static final Integer[] elments = [2,4,6,8,9] Then
 *
 * AC1 Write a function that get all pair numbers with API stream
 *
 * AC2 Write a function to filter X(number) and return x number with API stream
 *
 * AC3  Write a function to filter X(number) with API stream if not exits throw IllegalArgumentException
 */

public class Test {

    private static Integer[] elements = [2,4,6,8,9];

    public static void main(String[] args) {
        System.out.println(getPairs());
        System.out.println(filterByNumber(2));
        System.out.println(filterByNumberOrThrow(2));
    }

    //AC1 Write a function that get all pair numbers with API stream
    private static Integer[] getPairs(Integer[] elements) {
        return Arrays.stream(elements).filter( number -> number%2 == 0)
                .collect(Collectors.toList());
    }

    //AC2 Write a function to filter X(number) and return x number with API stream
    private static Optional<Integer> filterByNumber(Integer x) {
        return Arrays.stream(elements).filter(element -> element == x)
                .findFirst();
    }
    private static Integer[] filterByNumberOrThrow(Integer x) {
        return Arrays.stream(elements).filter(element -> element == x)
                .findAny().orElseThrow(() -> new IllegalArgumentException("Invalid number"));
    }

}
