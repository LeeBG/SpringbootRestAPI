package com.rest.api.advice;

import com.rest.api.advice.exception.CUserNotFoundException;
import com.rest.api.model.response.CommonResult;
import com.rest.api.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

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

//    @ExceptionHandler(Exception.class)//Exception이 발생하면 해당 Handler로 처리하겠다고 명시하는 annotation입니다.
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)//해당 Exception이 발생하면 Response에 출력되는 HttpStatus Code가 500으로 내려가도록 설정합니다.
//    // 참고로 성공 시엔 HttpStatus code가 200으로 내려갑니다.
//    protected CommonResult defaultException(HttpRequest request, Exception e){
//        return responseService.getFailResult();
//        //Exception 발생시 이미 만들어둔 CommonResult의 실패 결과를 json 형태로 출력하도록 설정합니다.
//        //위에서 세팅한 HttpStatus Code외에 추가로 api 성공 실패여부를 다시 세팅하는 이유는 상황에 따라 다양한 메시지를 전달하기 위해서 입니다.
//    }

    @ExceptionHandler(CUserNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult userNotFoundException(HttpServletRequest request, CUserNotFoundException e){
        return responseService.getFailResult();
    }
}
