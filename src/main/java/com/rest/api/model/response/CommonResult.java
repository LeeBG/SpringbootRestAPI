package com.rest.api.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResult {  //api실행결과를 담는 공통 모델
    //ApiModeProperty : 변수를 설명해주는 swagger 어노테이션
    @ApiModelProperty(value = "응답 성공 여부: true/false")
    private boolean success;

    @ApiModelProperty(value = "응답 코드: 양수/음수")
    private int code;

    @ApiModelProperty(value = "응답 메시지")
    private String msg;

}
