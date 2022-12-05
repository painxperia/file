package com.zpain.file;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author zhangjun
 * @date 2021/12/23  15:58
 */
@Getter
@Setter
@NoArgsConstructor
public class Result<T> {

    private boolean success;
    private String message;
    private T result;

    public Result(boolean success, T result) {
        this.success = success;
        this.result = result;
    }

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public static <T> Result<T> successResult(T result) {
        return new Result<T>(true, result);
    }

    public static <T> Result<T> successResult() {
        return new Result<T>(true, "success");
    }

    public static <T> Result<T> falseResult(String message) {
        return new Result<T>(false, message);
    }

}
