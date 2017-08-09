package org.fxb.commons.web.servlet.handlers;

import javax.servlet.http.HttpServletResponse;

/**
 * 응답코드
 * 200 : 성공
 * 401 : 로그인하지 않음.
 * 402 : 접근권한이 없음.
 * 403 : 중복로그인 여부 필요.
 * 404 : 비밀번호 기간이 만료됨.
 * 405 : RSA 암호화, 복호화 오류
 * 406 : 로그인 실패
 * 480 : 폼 유효성검사 오류
 *
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 16. 8. 19.
 *
 * @see Done
 * @see HttpServletResponse
 */
public enum StatusCode {
	OK(HttpServletResponse.SC_OK),
	Unauthorized(HttpServletResponse.SC_UNAUTHORIZED),
	AccessDenied(HttpServletResponse.SC_FORBIDDEN),
	SecurityError(940),
	LoginFailure(950),
	DuplicationLogin(951),
	PasswordUseExpired(952),
	RSAFailure(941),
	FormValidation(942);

	private final int code;

	StatusCode(int code) {
		this.code = code;
	}

	public int getCode() { return code; }
}
