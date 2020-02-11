package test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Integer[] empIds = {1, 2, 3};
        Stream<Integer> infiniteStream = Stream.iterate(2, i -> i * 2);
        List<Integer> emps = Stream.of(empIds)
                .map(Integer::new)
                .collect(Collectors.toList());
    }
}
