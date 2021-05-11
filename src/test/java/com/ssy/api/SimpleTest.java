package com.ssy.api;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description sdt单元测试demo
 * @Author sunshaoyu
 * @Date 2021年04月02日-13:14
 */
@Slf4j
public class SimpleTest {

	@Test
	void test1() throws Throwable {
	}

	@Test
	public void test2() {
		Document document = DocumentHelper.createDocument();
		document.setXMLEncoding("GB2312");
		Element e = document.addElement("xmlData").addElement("reportTicket");

		e.addElement("csFnshi").addText("2");
		e.addElement("serialNo").addText(UUID.randomUUID().toString().replace("-", ""));
		System.out.println(document.asXML());
	}

	public static boolean isNull(Object obj) {
		if (null == obj) {
			return true;
		} else if (obj instanceof String) {
			String str = String.class.cast(obj);
			return str.trim().isEmpty() || str.equals("null");
		} else if (obj instanceof Map) {
			return Map.class.cast(obj).isEmpty();
		} else if (obj instanceof Collection) {
			return Collection.class.cast(obj).isEmpty();
		} else if (obj.getClass().isArray()) {
			return Array.getLength(obj) == 0;
		}
		return false;
	}

	public int unique(int[] arr) {
		if (null == arr || arr.length == 0) {
			return 0;
		}

		// 排序后的快慢指针
		int i = 0;
		for (int j = 1; j < arr.length; j++) {
			if (arr[j] != arr[i]) {
				arr[++i] = arr[j];
			}
		}
		final int len = i + 1;
		for (int k = len; k < arr.length; k++) {
			arr[k] = 0;
		}
		return len;
	}

	public void sum(int[] arr, int target) {
		if (null == arr || arr.length == 0) {
			return;
		}

		// <值, 下标>
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			Integer index = map.get(target - arr[i]);
			if (index != null) {
				System.out.println(i + ":[" + arr[i] + "] + " + index + ":[" + arr[index] + "]");
			} else {
				map.put(arr[i], i);
			}
		}
	}

	public void q(int[] arr, int l, int h) {
		if (null == arr || arr.length == 0 || l > h) {
			return;
		}

		int key = arr[l];
		int i = l, j = h;
		while (i < j) {
			while (i < j && arr[j] > key) {
				j--;
			}

			while (i < j && arr[i] <= key) {
				i++;
			}

			if (i < j) {
				swap(arr, i, j);
			}
		}
		swap(arr, i, l);

		q(arr, l, i - 1);
		q(arr, i + 1, h);
	}

	public void s(int[] arr) {
		if (null == arr || arr.length == 0) {
			return;
		}

		for (int i = 1; i < arr.length; i++) {
			boolean flag = false;
			for (int j = 0; j < arr.length - i; j++) {
				if (arr[j] > arr[j + 1]) {
					swap(arr, j, j + 1);
					flag = true;
				}
			}

			if (!flag) {
				break;
			}
		}
	}

	public void swap(int[] arr, int i, int j) {
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}

	class MyHashSet<E> {

		private final Object PRESENT = new Object();
		private transient Map<E, Object> map;

		public MyHashSet(Collection<E> collection) {
			map = new HashMap<>();
			addAll(collection);
		}

		public void addAll(Collection<E> collection) {
			collection.forEach(e -> {
				map.put(e, PRESENT);
			});
		}

		public void forEach(Consumer<? super E> consumer) {
			for (E e : map.keySet()) {
				consumer.accept(e);
			}
		}

		public int size() {
			return map.keySet().size();
		}
	}
}
