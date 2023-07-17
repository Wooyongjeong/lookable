package com.lookable.exception.dto;

import lombok.RequiredArgsConstructor;

import static com.lookable.exception.dto.HttpStatusCode.BAD_GATEWAY;
import static com.lookable.exception.dto.HttpStatusCode.BAD_REQUEST;
import static com.lookable.exception.dto.HttpStatusCode.CONFLICT;
import static com.lookable.exception.dto.HttpStatusCode.FORBIDDEN;
import static com.lookable.exception.dto.HttpStatusCode.INTERNAL_SERVER;
import static com.lookable.exception.dto.HttpStatusCode.METHOD_NOT_ALLOWED;
import static com.lookable.exception.dto.HttpStatusCode.NOT_ACCEPTABLE;
import static com.lookable.exception.dto.HttpStatusCode.NOT_FOUND;
import static com.lookable.exception.dto.HttpStatusCode.NOT_IMPLEMENTED;
import static com.lookable.exception.dto.HttpStatusCode.SERVICE_UNAVAILABLE;
import static com.lookable.exception.dto.HttpStatusCode.TOO_MANY_REQUESTS;
import static com.lookable.exception.dto.HttpStatusCode.UNAUTHORIZED;
import static com.lookable.exception.dto.HttpStatusCode.UNSUPPORTED_MEDIA_TYPE;

@RequiredArgsConstructor
public enum ErrorCode {

    /**
     * 400 Bad Request (잘못된 요청)
     */
    E400_INVALID(BAD_REQUEST, "BR000", "잘못된 요청입니다"),

    /**
     * 401 UnAuthorized (인증 실패)
     */
    E401_UNAUTHORIZED(UNAUTHORIZED, "UA000", "인증에 실패하였습니다. 다시 로그인 해주세요"),
    E401_UNAUTHORIZED_NOT_MATCH_PASSWORD(UNAUTHORIZED, "UA001", "비밀번호가 일치하지 않습니다"),

    /**
     * 403 Forbidden (권한 등의 이유로 허용되지 않은 요청)
     */
    E403_FORBIDDEN(FORBIDDEN, "FB000", "허용되지 않은 요청입니다"),
    E403_FORBIDDEN_TOKEN(FORBIDDEN, "FB001", "잘못된 토큰입니다"),
    E403_FORBIDDEN_AUTHENTICATE(FORBIDDEN, "FB002", "아이디 혹은 비밀번호를 확인해주세요"),
    E403_FORBIDDEN_NOT_AUTHOR(FORBIDDEN, "FB003", "작성자가 아닙니다"),

    /**
     * 404 Not Found (존재하지 않는 리소스)
     */
    E404_NOT_FOUND(NOT_FOUND, "NF000", "존재하지 않습니다"),

    /**
     * 405 Method Not Allowed
     */
    E405_METHOD_NOT_ALLOWED(METHOD_NOT_ALLOWED, "MN000", "허용되지 않은 HTTP 메소드입니다"),

    /**
     * 409 Conflict (중복되는 리소스)
     */
    E409_CONFLICT(CONFLICT, "CF000", "이미 존재합니다"),
    E409_CONFLICT_NICKNAME(CONFLICT, "CF001", "사용중인 닉네임입니다"),
    E409_CONFLICT_PASSWORD(CONFLICT, "CF002", "이전에 사용한 비밀번호입니다"),
    E409_CONFLICT_PROFILE_IMG(CONFLICT, "CF003", "기존 프로필 이미지와 동일한 이미지입니다"),

    /**
     * 429 Too Many Request (RateLimit)
     */
    E429_TOO_MANY_REQUESTS(TOO_MANY_REQUESTS, "TM000", "일시적으로 요청이 몰려 처리중입니다\n잠시 후 다시 이용해주세요"),

    /**
     * 500 Internal Server Error (서버 내부 에러)
     */
    E500_INTERNAL_SERVER(INTERNAL_SERVER, "IS000", "서버 오류가 발생하였습니다\n잠시 후 다시 시도해주세요"),

    /**
     * 501 Not Implemented (지원하지 않는 요청)
     */
    E501_NOT_IMPLEMENTED(NOT_IMPLEMENTED, "NI000", "해당 요청은 지원하지 않습니다"),

    /**
     * 502 Bad Gateway (외부 시스템의 Bad Gateway)
     */
    E502_BAD_GATEWAY(BAD_GATEWAY, "BG000", "통신이 제대로 되지 않았습니다\n잠시 후 다시 시도해주세요"),

    /**
     * 503 Service UnAvailable
     */
    E503_SERVICE_UNAVAILABLE(SERVICE_UNAVAILABLE, "SU000", "해당 기능은 현재 사용할 수 없습니다"),
    ;

    private final HttpStatusCode statusCode;
    private final String code;
    private final String message;

    public int getStatus() {
        return statusCode.getStatus();
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
