package br.com.collegesmaster.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@Aspect
public class ExceptionHandlerAOP {

	@Around("@annotation(HandlingRuntimeException)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		try {
			return joinPoint.proceed();
		} catch(RuntimeException exception) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error", exception);
		}
	}
}
