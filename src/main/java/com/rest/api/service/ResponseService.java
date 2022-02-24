package com.rest.api.service;


import com.rest.api.model.response.CommonResult;
import com.rest.api.model.response.ListResult;
import com.rest.api.model.response.SingleResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService{

    //enum으로 api요청 결과에 대한 Code, message를 정의합니다.
    public enum CommonResponse {
        Success(0,"성공입니다."),
        Fail(-1,"실패입니다.");

        int code;
        String msg;

        CommonResponse(int code, String msg){
            this.code=code;
            this.msg=msg;
        }

        public int getCode(){
            return code;
        }
        public String getMsg(){
            return msg;
        }
    }
        // 단일 건 결과를 처리하는 메소드
        public <T>SingleResult<T> getSingleResult(T data){
            SingleResult<T> result = new SingleResult<>();
            result.setData(data);
            setSuccessResult(result);
            return result;
        }

        // 다중 건 결과를 처리하는 메소드
        public <T> ListResult<T> getListResult(List<T> list){
            ListResult<T> result = new ListResult<>();
            result.setList(list);
            setSuccessResult(result);
            return result;
        }

        // 성공결과만 처리하는 메소드
        public CommonResult getSuccessResult(){
            CommonResult result = new CommonResult();
            setSuccessResult(result);
            return result;
        }

        private void setSuccessResult(CommonResult result){
            result.setSuccess(true);
            result.setCode(CommonResponse.Success.getCode());
            result.setMsg(CommonResponse.Success.getMsg());
        }



}
