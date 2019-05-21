package com.example.demo.api.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

// 由于每个RequestMappingInfo中都会持有一个RequestCondition对象，并且这些对象都是有状态的，
// 因而这里必须为其使用prototype进行标注，表示每次从BeanFactory中获取该对象时都会创建一个新的对象
@Component
@Scope("prototype")
public class VersionRequestCondition implements RequestCondition<VersionRequestCondition> {

	private int version;

	public VersionRequestCondition(int version) {
		this.version = version;
	}

	@Override
	public VersionRequestCondition combine(VersionRequestCondition other) {
		return this;
	}

	@Override
	public VersionRequestCondition getMatchingCondition(HttpServletRequest request) {
		Pattern pattern = Pattern.compile("v(\\d+)/");
		Matcher matcher = pattern.matcher(request.getRequestURL());
		System.err.println("version--\n" + this.version);
		if (matcher.find()) {
			String group = matcher.group(1);
			if (group.equals(this.version + "")) {
				return this;
			}
		}
		return null;
	}

	@Override
	public int compareTo(VersionRequestCondition other, HttpServletRequest request) {
		if (other == null) {
			return 1;
		}
		return this.version - other.version;
	}

}