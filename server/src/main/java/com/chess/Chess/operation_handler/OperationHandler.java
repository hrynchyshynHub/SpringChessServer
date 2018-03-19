package com.chess.Chess.operation_handler;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Use to mark methods as OperationHandler to handle client requests
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface OperationHandler {

    String path() default "";

    boolean closeConnection() default true;

}
