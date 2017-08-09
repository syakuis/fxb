package org.java8.lamda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.ToString;
import org.junit.Test;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 3. 29.
 */
public class FunctionTest {

  @Test
  public void basic() {
    List<String> data = Arrays.asList("1", "2", "", "4");

    // 익명 함수를 사용함
    Predicate<String> predicate = a -> !"".equals(a);

    data.stream()
        .filter(predicate)
        .forEach(System.out::println);

    // 숫자 형식의 목록으로 변환하여 반환함.
    List<Integer> result = data.stream()
        .filter(s -> !"".equals(s))
        .map(Integer::parseInt)
        .collect(Collectors.toList());

    result.forEach(System.out::println);
  }

  /**
   * 다중 목록을 하나로 합치기
   */
  @Test
  public void combineMultipleLists() {
    List<List<String>> data = Arrays.asList(
        Arrays.asList("1", "2"),
        Arrays.asList("a", "b"),
        Arrays.asList("가", "나", "다")
    );

    data.stream()
        .flatMap(Collection::stream)
        .collect(Collectors.toList())
        .forEach(System.out::println);

    /*
    1
    2
    a
    b
    가
    나
    다
    */
  }

  /**
   * 새로운 클래스에 대상 데이터를 넣고 반환하기.
   */
  @Test
  public void newListAdd() {
    // NewList 클래스에 빈값 제외하고 담기
    List<String> data2 = Arrays.asList("1", "2", "", "4");
    NewList list = data2.stream()
        .filter(s -> !"".equals(s))
        .mapToInt(Integer::parseInt)
        // Collector<T: 담을 그릇, A: 그릇에 담는 로직, R: 최종 결과를 반영>
        // Function.identity : 담는 로직에서 최종 결과가 완성되었기 때문에 변환 과정이 필요없으므로 사용한다.
        .collect(
            NewList::new, // () -> new NewList(),
            NewList::add, // (NewList result, Integer item) -> { result.add(item); },
            (data, result) -> {
              result.addAll(data.getList());
            });

    // 메서드 레퍼런스로 변경


    list.getList().stream().forEach(System.out::println);
  }
}

@ToString
class NewList {
  private static final List<Integer> result = new ArrayList<>();

  public void add(Integer e) {
    result.add(e);
  }

  public List<Integer> getList() {
    return Collections.unmodifiableList(result);
  }

  public boolean addAll(List<Integer> list) {
    return result.addAll(list);
  }
}
