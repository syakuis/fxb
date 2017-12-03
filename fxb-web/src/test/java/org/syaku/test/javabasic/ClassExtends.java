package org.syaku.test.javabasic;

import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 12. 1.
 */
public class ClassExtends {
  @Test
  public void test() {
    AClass aClass = new AClass();

    // b 는 a 라는 클래스를 상속받았다.
    // 하디만 b 는 a 라는 클래스로 인스턴스 할 수 없다.
    // BClass bClass = new AClass(); // incompatible types : 호환할 수 없다고 오류가 발생.

    // b 클래스를 인스터스하여 a 클래스 타입으로 타입변환(casting)을 할 수 있다.
    AClass bClass2 = new BClass();
  }

  @Getter @Setter
  public class AClass {
    String name;
  }

  @Getter @Setter
  public class BClass extends AClass {
    String name2;
  }
}
