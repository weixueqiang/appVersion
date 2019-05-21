package com.example.demo.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

// 由于每个RequestMappingInfo中都会持有一个RequestCondition对象，并且这些对象都是有状态的，
// 因而这里必须为其使用prototype进行标注，表示每次从BeanFactory中获取该对象时都会创建一个新的对象
@Component
@Scope("prototype")
public class TemplateRequestCondition implements RequestCondition<TemplateRequestCondition> {

	private int[] templates;

	public TemplateRequestCondition(int[] templates) {
		this.templates = templates;
	}

	// 这里combine()方法主要是供给复合类型的RequestMapping使用的，这种类型的Mapping可以持有
	// 两个Mapping信息，因而需要对两个Mapping进行合并，这个合并的过程其实就是对每个RequestMappingInfo
	// 中的各个条件进行合并，这里就是对RequestCondition条件进行合并
	public TemplateRequestCondition combine(TemplateRequestCondition other) {
		System.out.println("templates" + Arrays.asList(this.templates));
		System.out.println("other.templates" + other.templates);
		int[] allTemplates = mergeTemplates(other.templates);
		return new TemplateRequestCondition(allTemplates);
	}

	// 判断当前请求对应用户选择的模板与当前接口所能处理的模板是否一致，
	// 如果一致则返回当前RequestCondition，这里RequestMappingHandlerMapping在匹配请求时，
	// 如果当前条件的匹配结果不为空，则说明当前条件是能够匹配上的，如果返回值为空，则说明其不能匹配
	public TemplateRequestCondition getMatchingCondition(HttpServletRequest request) {
		String serverName = request.getServerName();
		for (int s = 0; s < templates.length; s++) {
			System.out.println(templates[s]);
		}

		System.out.println("getMatchingCondition()..templates" + Arrays.asList(this.templates).toString());
		int template = getTemplateByServerName(serverName);
		for (int i = 0; i < templates.length; i++) {
			if (template == templates[i]) {
				return this;
			}
		}

		return null;
	}

	// 对两个RequestCondition对象进行比较，这里主要是如果存在两个注册的一样的Mapping，那么就会对
	// 这两个Mapping进行排序，以判断哪个Mapping更适合处理当前request请求
	public int compareTo(TemplateRequestCondition other, HttpServletRequest request) {
		System.out.println("compareTo()templates" + Arrays.asList(this.templates).toString());
		System.out.println("compareTo()other.templates" + other.templates);
		return null != templates && null == other.templates ? 1 : null == templates && null != other.templates ? -1 : 0;
	}

	// 项目中实际会用到的，根据当前请求的域名获取其对应用户所选择的模板
	private int getTemplateByServerName(String serverName) {
		if (serverName.equalsIgnoreCase("peer1")) {
			return 1;
		} else if (serverName.equalsIgnoreCase("peer2")) {
			return 2;
		}

		return 0;
	}

	// 将两个template数据进行合并
	private int[] mergeTemplates(int[] otherTemplates) {
		if (null == otherTemplates) {
			return templates;
		}

		int[] results = new int[templates.length + otherTemplates.length];
		for (int i = 0; i < templates.length; i++) {
			results[i] = templates[i];
		}

		for (int i = templates.length; i < results.length; i++) {
			results[i] = otherTemplates[i - templates.length];
		}

		return results;
	}

}