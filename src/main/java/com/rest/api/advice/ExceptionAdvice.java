package com.rest.api.advice;

import com.rest.api.advice.exception.CAuthenticationEntryPointException;
import com.rest.api.advice.exception.CEmailSigninFailedException;
import com.rest.api.advice.exception.CUserNotFoundException;
import com.rest.api.model.response.CommonResult;
import com.rest.api.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import javax.servlet.http.HttpServletRequest;

/**
 * ControllerAdvice의 사용
 * ControllerAdvice는 Spring에서 제공하는 annotation으로 Controller 전역에 적용되는 코드를 작성할 수 있게 해 줍니다.
 * 또한 설정시 특정 패키지를 명시하면 적용되는 Controller의 범위도 제한할 수 있습니다.
 * 이러한 특성을 이용하면 @ControllerAdvice와 @ExceptionHandler를 조합하여 예외 처리를 공통 코드로 분리하여 작성할 수 있습니다.
 */
@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    private final ResponseService responseService;
    private final MessageSource messageSource;

    @ExceptionHandler(Exception.class)//Exception이 발생하면 해당 Handler로 처리하겠다고 명시하는 annotation입니다.
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)//해당 Exception이 발생하면 Response에 출력되는 HttpStatus Code가 500으로 내려가도록 설정합니다.
    protected CommonResult defaultException(HttpServletRequest request, Exception e){
        return responseService.getFailResult(Integer.valueOf(getMessage("unKnown.code")),getMessage("unKnown.msg"));
    }

    @ExceptionHandler(CUserNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult userNotFoundException(HttpServletRequest request, CUserNotFoundException e){
        return responseService.getFailResult(Integer.valueOf(getMessage("userNotFound.code")),getMessage("userNotFound.msg"));
    }
    // code정보에 해당하는 메시지를 조회합니다.
    private String getMessage(String code){
        return getMessage(code, null);
    }

    // code정보, 추가 Argument로 현재 locale에 맏는 메시지를 조회합니다.
    private String getMessage(String code, Object[] args){
        return messageSource.getMessage(code,args, LocaleContextHolder.getLocale());
    }

    @ExceptionHandler(CEmailSigninFailedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult emailSigninFailed(HttpServletRequest request, CEmailSigninFailedException e){
        return responseService.getFailResult(Integer.valueOf(getMessage("emailSigninFailed.code")),getMessage("emailSigninFailed.msg"));
    }

    @ExceptionHandler(CAuthenticationEntryPointException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult authenticationEntryPointException(HttpServletRequest request, CAuthenticationEntryPointException e){
        return responseService.getFailResult(Integer.valueOf(getMessage("entryPointException.code")),getMessage("entryPointException.msg"));
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected CommonResult accessDeniedException(HttpServletRequest request, AccessDeniedException e){
        return responseService.getFailResult(Integer.valueOf(getMessage("accessDenied.code")),getMessage("accessDenied.msg"));
    }
}
