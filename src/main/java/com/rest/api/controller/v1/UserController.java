package com.rest.api.controller.v1;

import com.rest.api.Repository.UserJpaRepository;
import com.rest.api.advice.exception.CUserNotFoundException;
import com.rest.api.model.User;
import com.rest.api.model.response.CommonResult;
import com.rest.api.model.response.SingleResult;
import com.rest.api.service.ResponseService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(tags = {"1. Swagger Test"})  // Swagger 최상단 controller 명칭
@RequiredArgsConstructor        // class상단에 선언하면 class내부에 final이나 @notnull로 선언된 객체에 대해서 생성자 주입을 수행
@RestController
@RequestMapping(value = "/v1")
public class UserController {
    private final UserJpaRepository userJpaRepository;
    private final ResponseService responseService;

    @ApiOperation(value = "User Get 요청", notes = "User Get 요청", response = String.class) // value: 해당 파라미터 명칭, notes : 메소드에 대한 설명란
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "페이지 없음"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @GetMapping("/users")
    public List<User> findAllUser(){
        return userJpaRepository.findAll();
    }


    @ApiOperation(value = "User 한건 조회", notes = "User get 한건 요청", response = String.class) // value: 해당 파라미터 명칭, notes : 메소드에 대한 설명란
    @GetMapping(value = "/user/{msrl}")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "페이지 없음"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public SingleResult<User> findUserById(@ApiParam(value = "ID로회원검색",required = true)@PathVariable long msrl)/*throws Exception*/{
        // 결과 데이터가 단일 건인 경우에는 getBasicResult를 이용해서 결과를 출력한다.
        return responseService.getSingleResult(userJpaRepository.findById(msrl).orElseThrow(CUserNotFoundException::new));
    }


    @ApiOperation(value = "User Post 요청", notes = "User Post 요청", response = String.class) // value: 해당 파라미터 명칭, notes : 메소드에 대한 설명란
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "페이지 없음"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @PostMapping("/user")
    public User save(@ApiParam(value = "회원아이디",required = true)@RequestParam String uid,
                     @ApiParam(value = "회원이름",required = true)@RequestParam String name){
        User userEntity = User.builder()
                .uid(uid)
                .name(name)
                .build();
        return userJpaRepository.save(userEntity);
    }

    @ApiOperation(value = "회원 수정", notes = "회원 정보를 수정한다.")
    @PutMapping(value = "/user")
    public SingleResult<User> update(@ApiParam(value = "회원번호",required = true)@RequestParam long msrl,
                                     @ApiParam(value = "회원아이디",required = true)@RequestParam String uid,
                                     @ApiParam(value = "회원이름",required = true)@RequestParam String name){
        User user = User.builder()
                .msrl(msrl)
                .uid(uid)
                .name(name)
                .build();
        return responseService.getSingleResult(userJpaRepository.save(user));
    }

    @ApiOperation(value = "회원 삭제", notes = "userId로 회원정보를 삭제한다.")
    @DeleteMapping(value = "/user/{msrl}")
    public CommonResult delete(
            @ApiParam(value = "회원 번호",required = true)@PathVariable long msrl){
        userJpaRepository.deleteById(msrl);
        // 성공결과 정보만 필요한 경우 getSuccessResult()를 이용해서 결과를 출력한다.
        return responseService.getSuccessResult();
    }
}
