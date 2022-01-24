package com.itemis.mgttg.controller;

public class Result {

    private ResultCode resultCode;
    private float answer;
    private String message;

    public Result(ResultCode resultCode){
        this.resultCode = resultCode;
        this.answer=0f;
        this.message="";
    }
    public Result(ResultCode resultCode, float answer){
        this.resultCode = resultCode;
        this.answer = answer;
        this.message="";
    }
    public Result(ResultCode resultCode, String message){
        this.resultCode = resultCode;
        this.answer=0f;
        this.message=message;
    }

    public boolean isSuccess(){
        return resultCode == ResultCode.OK;
    }
    public float getAnswer(){
        return answer;
    }
    public String getMessage(){
        return message;
    }


}
