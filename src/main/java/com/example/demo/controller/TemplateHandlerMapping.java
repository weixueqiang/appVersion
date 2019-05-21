package com.example.demo.controller;

import java.lang.reflect.Method;

import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Component
public class TemplateHandlerMapping extends RequestMappingHandlerMapping implements PriorityOrdered {
	@Override
	protected RequestCondition<?> getCustomMethodCondition(Method method) {
		method.setAccessible(true);
		Template template = method.getAnnotation(Template.class);
		int[] templates = null == template ? new int[0] : template.value();
		return obtainApplicationContext().getBean(RequestCondition.class, templates);
	}

	@Override
	public int getOrder() {
		return 0;
	}

}