package org.fxb.boot;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 8. 12.
 */

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//@WebListener
public class FxbListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("init start"); // 초기화와 동시에 실행되는 부분
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("init destory"); // Destory와 동시에 실행되는 부분
	}

}