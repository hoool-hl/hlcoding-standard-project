package com.hlcoding.framework.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 对象复制工具类
 */
@Slf4j
public class BeanCopyUtils {

	private BeanCopyUtils() { }

	/** 缓存类转换模型 */
	private static final Map<String, BeanCopier> BEAN_COPIERS = new ConcurrentHashMap<>();

	/**
	 * 批量复制对象属性值（同属性名与同属性类型才复制）
	 *
	 * @param sourceList 源对象集合
	 * @param targetClass 结果类
	 * @return 复制属性后的对象集合
	 * @author sy
	 */
	public static <T> List<T> copyList(final List<?> sourceList, @NotNull Class<T> targetClass) {
		if (sourceList == null || sourceList.isEmpty()) {
			return Collections.emptyList();
		}

		List<T> list = new ArrayList<>(sourceList.size());
		sourceList.forEach(obj -> list.add(copy(obj, targetClass, null)));

		return list;
	}

	/**
	 * 复制对象属性值（同属性名与同属性类型才复制）
	 *
	 * @param sourceObject 源对象
	 * @param targetClass 结果类
	 * @return 复制属性后的对象
	 * @author sy
	 */
	public static <T> T copy(final Object sourceObject, Class<T> targetClass) {
		return copy(sourceObject, targetClass, null);
	}

	/**
	 * 复制对象属性值（同属性名与同属性类型才复制）
	 *
	 * @param srcObj 源对象
	 * @param destObj 结果对象
	 * @author sy
	 * @createTime 2019年7月1日 下午1:41:32
	 */
	public static Object copy(final Object srcObj, final Object destObj) {
		return copy(srcObj, destObj, null);
	}

	/**
	 * 复制对象属性值（converter为null时按照默认原则：同属性名与同属性类型才复制）
	 *
	 * @param sourceObject 源对象
	 * @param targetClass 结果类
	 * @param converter 自定义属性复制原则
	 * @return 复制属性后的对象
	 * @author sy
	 */
	public static <T> T copy(final Object sourceObject, Class<T> targetClass, Converter converter) {
		// 源对象为空则直接返回null
		if (sourceObject == null || targetClass == null) {
			return null;
		}

		// 获取复制工具
		BeanCopier copier = getBeanCopier(sourceObject.getClass(), targetClass, converter != null);

		try {
			T target = targetClass.newInstance();
			// 复制对象属性
			copier.copy(sourceObject, target, converter);
			return target;

		} catch (InstantiationException | IllegalAccessException e) {
			log.error(
					String.format("failed to copy object from %s to %s", sourceObject.getClass().getName(), targetClass.getName())
					, e);
		}

		return null;
	}

	/**
	 * 复制对象属性值（converter为null时按照默认原则：同属性名与同属性类型才复制）
	 *
	 * @param sourceObject 源对象
	 * @param targetObject 结果对象
	 * @param converter 自定义属性复制原则
	 * @author sy
	 */
	public static Object copy(final Object sourceObject, final Object targetObject, Converter converter) {
		if(sourceObject == null || targetObject == null) {
			return null;
		}

		// 获取复制工具
		BeanCopier copier = getBeanCopier(sourceObject.getClass(), targetObject.getClass(), converter != null);
		// 复制对象属性
		copier.copy(sourceObject, targetObject, converter);

		return targetObject;
	}

	/** 根据类名获取复制工具 */
	private static BeanCopier getBeanCopier(Class<?> sourceClass, Class<?> targetClass, boolean userConverter) {

		// 拼接缓存key值
		String key = generateKey(sourceClass, targetClass);

        BeanCopier copier;
        if (!BEAN_COPIERS.containsKey(key)) {
            copier = BeanCopier.create(sourceClass, targetClass, userConverter);
            BEAN_COPIERS.put(key, copier);
        } else {
            copier = BEAN_COPIERS.get(key);
        }

        return copier;
    }

    /** 根据类名构建KEY值 */
    private static String generateKey(Class<?> srcClazz, Class<?> destClazz) {
        return srcClazz.getName() + destClazz.getName();
    }
}
