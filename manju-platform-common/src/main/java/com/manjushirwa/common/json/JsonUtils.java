package com.manjushirwa.common.json;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 用于json转换
 */
public class JsonUtils {
	 private static final Logger _logger = LoggerFactory.getLogger(JsonUtils.class);
	/**
	 * json字符串转对象
	 * @date 2018年4月15日 下午4:02:21
	 * @author jopson
	 * @param jsonStr
	 * @param classOfT
	 * @return
	 */
	public <T> T jsonToObject(String jsonStr, Class<T> classOfT) {
		try {
			return JSON.parseObject(jsonStr, classOfT);

		} catch (Exception e) {
			_logger.error("json字符串转对象异常，{}", e);
		}
		return null;
	}

	/**
	 * 对象装Json字符串， 自动去除为null值字段
	 * @date 2018年4月15日 下午4:05:30
	 * @author jopson
	 * @param obj
	 * @return
	 */
	public String objectToJson(Object obj) {
		try {
			String jsonStr = null;
			if (obj == null) {
				return null;
			}
			jsonStr = JSON.toJSONStringWithDateFormat(obj, "yyyy-MM-dd HH:mm:ss");
			return jsonStr != null ? jsonStr : null;
		} catch (Exception e) {
			_logger.error("对象装Json字符串，自动去除为null值字段异常，{}", e);
		}
		return null;
	}

	/**
	 * 对象装Json字符串，保留为null值字段
	 * @date 2018年4月15日 下午4:06:39
	 * @author jopson
	 * @param obj
	 * @return
	 */
	public String objectToJsonWriteMapNullValue(Object obj) {
		try {
			String jsonStr = null;
			if (obj == null) {
				return null;
			}
			jsonStr = JSON.toJSONStringWithDateFormat(obj, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteMapNullValue);
			return jsonStr != null ? jsonStr : "";

		} catch (Throwable e) {

		}
		return null;
	}

	/**
	 * 对象装Json字符串，保留为null值字段 需要在对应的实体bean 字段加上@JSONField
	 * @date 2018年4月15日 下午4:07:43
	 * @author jopson
	 * @param obj
	 * @return
	 */
	public String objectToJsonCustomize(Object obj) {
		try {
			String jsonStr = null;
			if (obj == null) {
				return null;
			}

			jsonStr = JSON.toJSONString(obj);
			return jsonStr != null ? jsonStr : "";

		} catch (Exception e) {
			_logger.error("对象装Json字符串，保留为null值字段 需要在对应的实体bean 字段加上@JSONField异常，{}", e);
		}
		return null;
	}
}
