package org.java;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.Getter;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 4. 5.
 */
public class EqualsTest {

  @Test
  public void test() {
    Context context = new Context();
    Map<String, Object> newData = context.getData();
    Map<String, Object> newDataChild = new HashMap<>();
    Map<String, Object> newDataChildChild = new HashMap<>();

    newDataChild.put("a", "a");
    newDataChildChild.put("a", "a");

    newDataChild.put("nn", newDataChildChild);
    newData.put("n", newDataChild);

    Context context2 = new Context();
    Map<String, Object> newData2 = context2.getData();
    Map<String, Object> newDataChild2 = new HashMap<>();
    Map<String, Object> newDataChildChild2 = new HashMap<>();

    newDataChild2.put("a", "a");
    newDataChildChild2.put("a", "a");

    newDataChild2.put("nn", newDataChildChild2);
    newData2.put("n", newDataChild2);

    System.out.println(newData.toString());
    System.out.println(newData2.toString());

    Assert.assertEquals(newData, newData2);
    Assert.assertEquals(context, context2);
  }

}

class Context {
  @Getter
  private Map<String, Object> data = new HashMap<>();

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }

    if (object == null) {
      return false;
    }

    if (object instanceof Context) {
      return data.equals(((Context) object).getData());
    }

    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.data);
  }
}
