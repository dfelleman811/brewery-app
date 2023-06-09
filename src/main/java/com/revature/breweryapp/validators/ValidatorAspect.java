package com.revature.breweryapp.validators;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ValidatorAspect {

    @Before("@annotation(com.revature.breweryapp.validators.BeerStyleValidator)")
    public void validateRequestParams(JoinPoint joinPoint) {
       Arrays.stream(joinPoint.getArgs()).toList().forEach(System.out::println);
       // basically I want to iterate over the params to see if they're annotated with the custom validator
        // and if so - make sure the param is in the list of available styles before completing the request

    }


}
