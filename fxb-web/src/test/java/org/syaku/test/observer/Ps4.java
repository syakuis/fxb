package org.syaku.test.observer;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 1. 13.
 */
public class Ps4 implements VideoGame {

  @Override
  public void observe(@ObserverTarget Game game) {
    System.out.println(game.getTitle());
  }
}
