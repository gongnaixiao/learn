package guava;

import com.google.common.base.Optional;

public class HelloGuava {
    public static void main(String[] args) {
        Optional<Integer> possible = Optional.of(5);
        possible.isPresent(); // returns true
        possible.get(); // returns 5
        System.out.println(possible.isPresent());
        System.out.println(possible.get());
    }

}
