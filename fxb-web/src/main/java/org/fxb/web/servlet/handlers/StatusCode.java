package org.fxb.web.servlet.handlers;

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
 * @see Success
 *
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 16. 8. 19.
 */
public enum StatusCode {
	OK(200),
	SecurityError(401),
	Unauthorized(401),
	AccessDenied(402),
	DuplicationLogin(403),
	PasswordUseExpired(404),
	RSAFailure(405),
	LoginFailure(406),
	FormValidation(480);

	private final int code;

	StatusCode(int code) {
		this.code = code;
	}

	public int getCode() { return code; }
}
