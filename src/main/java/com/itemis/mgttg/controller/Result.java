package com.itemis.mgttg.controller;

import java.util.Objects;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return Float.compare(result.answer, answer) == 0 && resultCode == result.resultCode && Objects.equals(message, result.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resultCode, answer, message);
    }

    @Override
    public String toString() {
        return "Result{" +
                "resultCode=" + resultCode +
                ", answer=" + answer +
                ", message='" + message + '\'' +
                '}';
    }
}
