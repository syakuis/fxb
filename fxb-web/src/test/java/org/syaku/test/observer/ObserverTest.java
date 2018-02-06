package org.syaku.test.observer;

import org.junit.Test;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 1. 13.
 */
public class ObserverTest {
  @Test
  public void test() {
    Nintendo nintendo = new Nintendo();
    Game nintendoGame = new Game();
    nintendoGame.setTitle("마리오카드");
    nintendo.observe(nintendoGame);

    Ps4 ps4 = new Ps4();
    Game ps4Game = new Game();
    ps4Game.setTitle("철권");
    ps4.observe(ps4Game);

    Observers observers = new Observers();
    observers.addObserver(nintendo);
    observers.addObserver(ps4);

    Game game = new Game();
    game.setTitle("good");

    observers.fireEvent(game);

    Class clazz = Game.class;

  }
}
