package com.abcd.pay.extension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * 解决GET请求，查询参数又是对象的时，原生的feign调用不支持的问题
 * @author yangxi
 *
 */
@Component
public class FeignRquestInterceptor implements RequestInterceptor {
	
	private Logger log = LoggerFactory.getLogger(FeignRquestInterceptor.class);

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public void apply(RequestTemplate template) {
		// 如果是GET请求，而且请求参数是对象
		if (template.method().equals("GET") && template.body() != null) {
			try {
				JsonNode jsonNode = objectMapper.readTree(template.body());
				template.body(null);

				Map<String, Collection<String>> queries = new HashMap<>();
				buildQuery(jsonNode, "", queries);
				template.queries(queries);
			} catch (Exception e) {
				log.error("error", e);
			}
		}
	}

	private void buildQuery(JsonNode jsonNode, String path, Map<String, Collection<String>> queries) {
		if (!jsonNode.isContainerNode()) { // 叶子节点
			if (jsonNode.isNull()) {
				return;
			}
			Collection<String> values = queries.get(path);
			if (null == values) {
				values = new ArrayList<>();
				queries.put(path, values);
			}
			values.add(jsonNode.asText());
			return;
		}
		if (jsonNode.isArray()) { // 数组节点
			Iterator<JsonNode> it = jsonNode.elements();
			while (it.hasNext()) {
				buildQuery(it.next(), path, queries);
			}
		} else {
			Iterator<Map.Entry<String, JsonNode>> it = jsonNode.fields();
			while (it.hasNext()) {
				Map.Entry<String, JsonNode> entry = it.next();
				if (StringUtils.hasText(path)) {
					buildQuery(entry.getValue(), path + "." + entry.getKey(), queries);
				} else { // 根节点
					buildQuery(entry.getValue(), entry.getKey(), queries);
				}
			}
		}
	}

}