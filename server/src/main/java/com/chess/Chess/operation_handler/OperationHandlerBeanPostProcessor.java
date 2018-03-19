package com.chess.Chess.operation_handler;

import com.chess.Chess.exceptions.IllegalMethodReturnType;
import com.chess.Chess.exceptions.MultipleOperationHandlers;
import network.Response;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Collect all methods in beans that annotated with OperationHandler annotations
 * and add them to OperationHandlers
 *
 * @see OperationHandler
 * @see OperationHandlers
 */
@Component
public class OperationHandlerBeanPostProcessor implements BeanPostProcessor {

    private OperationHandlers operationHandlers;

    @Autowired
    public OperationHandlerBeanPostProcessor(OperationHandlers operationHandlers) {
        this.operationHandlers = operationHandlers;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Method[] methods = bean.getClass().getDeclaredMethods();

        for (Method method : methods) {
            OperationHandler operationHandler = method.getAnnotation(OperationHandler.class);

            if (operationHandler != null) {
                String path = operationHandler.path();

                if (path.isEmpty()) {
                    path = method.getName();
                }

                if (operationHandlers.containsOperationHandler(path)) {
                    throw new MultipleOperationHandlers("Two handlers for one path " + path);
                }

                if (!(method.getReturnType().equals(Response.class) || method.getReturnType().equals(Void.TYPE))) {
                    throw new IllegalMethodReturnType(
                            "Operation handler return type must be Response subclass or void"
                    );
                }

                operationHandlers.addOperationHandler(path, new Handler(operationHandler, method, bean));
            }
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
