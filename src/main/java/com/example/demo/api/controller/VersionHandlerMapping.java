package com.example.demo.api.controller;

import java.lang.reflect.Method;

import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Component
public class VersionHandlerMapping extends RequestMappingHandlerMapping implements PriorityOrdered {
	@Override
	protected RequestCondition<?> getCustomMethodCondition(Method method) {
		method.setAccessible(true);
		Version template = method.getAnnotation(Version.class);
		// int[] templates = null == template ? new int[0] : template.value();
		int value = template == null ? 0 : template.value();
		System.err.println("VersionHandlerMapping--value--\n" + value);
		return obtainApplicationContext().getBean(RequestCondition.class, value);
		// return new VersionRequestCondition(value);
	}

	/**
	 * 如果使用了自定义的RequestMapping处理请求，那么在Spring的配置文件中尽量不要使用<mvc:annotation-driven/>标签，
	 * 因为Spring在解析该标签时会往BeanFactory中注册一个RequestMappingHandlerMapping的对象，这样就会对我们自定义的HandlerMapping进行干扰，
	 * 因为需要注意request具体是由我们定义的HandlerMapping处理的还是Spring提供的RequestMappingHandlerMapping处理的。如果确实需要使用该标签，
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.handler.AbstractHandlerMapping#getOrder()
	 */
	@Override
	public int getOrder() {
		return 1;
	}

}