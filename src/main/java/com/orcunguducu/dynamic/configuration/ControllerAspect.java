package com.orcunguducu.dynamic.configuration;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerAspect {
	private static final Logger LOG = LoggerFactory
			.getLogger(ControllerAspect.class);
	
	@AfterReturning("within(@org.springframework.web.bind.annotation.RestController *)")
    public void afterMethodInControllerClass(JoinPoint joinPoint) {
		//Get logged in user
		LOG.info("Called method is:"+joinPoint.getSignature());
    }
}
