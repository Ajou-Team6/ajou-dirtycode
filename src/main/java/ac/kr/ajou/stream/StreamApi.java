package ac.kr.ajou.stream;

import java.util.List;
import java.util.stream.Collectors;

public class StreamApi {
    public List<String> filterString(List<String> stringList, String filteredString) {
        return stringList.stream()
                .filter(string -> string.equals(filteredString))
                .collect(Collectors.toList());
    }

    public List<String> filterByName(List<Person> personList, String filteredName) {
        return personList.stream()
                .filter(person -> person.getName().equals(filteredName))
                .map(person -> {
                    return person.getAddress();
                }).collect(Collectors.toList());
    }

    public int getAggregationAge(List<Person> personList, String filteredName)
    {
        return personList.stream()
                .filter(person -> person.getName().equals(filteredName))
                .mapToInt(person -> person.getAge())
                .sum();
    }

}
