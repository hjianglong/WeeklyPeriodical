package com.example.weeklyperiodical.ex.handler;

import com.example.weeklyperiodical.ex.ServiceException;
import com.example.weeklyperiodical.web.JsonResult;
import com.example.weeklyperiodical.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;
import java.util.StringJoiner;

/**
 * 全局异常处理器
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public JsonResult handleServiceException(ServiceException e) {
        log.debug("开始处理ServiceException");
        return JsonResult.fail(e);
    }

    @ExceptionHandler
    public JsonResult handleBindException(BindException e) {
        log.debug("开始处理BindException");

        // String delimiter = "，";
        // String prefix = "添加相册失败，";
        // String suffix = "！";
        // StringJoiner stringJoiner = new StringJoiner(delimiter, prefix, suffix);
        // List<FieldError> fieldErrors = e.getFieldErrors();
        // for (FieldError fieldError : fieldErrors) {
        //     String defaultMessage = fieldError.getDefaultMessage();
        //     stringJoiner.add(defaultMessage);
        // }
        // return JsonResult.fail(ServiceCode.ERR_BAD_REQUEST, stringJoiner.toString());

        String defaultMessage = e.getFieldError().getDefaultMessage();
        return JsonResult.fail(ServiceCode.ERR_BAD_REQUEST, defaultMessage);
    }

    @ExceptionHandler
    public JsonResult handleConstraintViolationException(ConstraintViolationException e) {
        log.debug("开始处理ConstraintViolationException");

        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        String delimiter = "，";
        StringJoiner stringJoiner = new StringJoiner(delimiter);
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            stringJoiner.add(constraintViolation.getMessage());
        }
        return JsonResult.fail(ServiceCode.ERR_BAD_REQUEST, stringJoiner.toString());
    }


    @ExceptionHandler({
            InternalAuthenticationServiceException.class,
            BadCredentialsException.class
    })
    public JsonResult handleAuthenticationException(AuthenticationException e) {
        log.debug("开始处理handleAuthenticationException");
        log.debug("异常类型："+e.getClass().getName());
        log.debug("异常消息："+e.getMessage());
//        log.debug("跟踪信息：");
//        e.printStackTrace();
        String message = "登录失败，用户名或密码错误！";
        return JsonResult.fail(ServiceCode.ERR_UNAUTHORIZED, message);
    }

//    @ExceptionHandler
//    public JsonResult handleBadCredentialsException(BadCredentialsException e) {
//        log.debug("开始处理"+e.getClass().getName());
//        String message = "登录失败，用户名或密码错误！";
//        return JsonResult.fail(ServiceCode.ERR_UNAUTHORIZED, message);
//    }

    @ExceptionHandler
    public JsonResult handleDisabledException(DisabledException e) {
        log.debug("开始处理"+e.getClass().getName());
        log.debug("异常类型："+e.getClass().getName());
        log.debug("异常消息："+e.getMessage());
        String message = "登录失败，账号已经被禁用！";
        return JsonResult.fail(ServiceCode.ERR_UNAUTHORIZED_DISABLED, message);
    }



    @ExceptionHandler
    public JsonResult handleAccessDeniedException(AccessDeniedException e) {
        log.debug("开始处理AccessDeniedException");
        log.debug("异常消息："+e.getMessage());
        String message = "权限不足，禁止访问";
        return JsonResult.fail(ServiceCode.ERR_FORBIDDEN, message);
    }
    @ExceptionHandler
    public JsonResult handleThrowable(Throwable e){
        log.debug("开始处理Throwable");
        e.printStackTrace();
        String message="服务器忙请稍后在试（开发阶段，请检查服务器控制台";
        return JsonResult.fail(ServiceCode.ERR_UNKNOWN,message);
    }


}
