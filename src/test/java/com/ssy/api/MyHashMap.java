package com.ssy.api;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @Description 手撕HashMap
 * @Author sunshaoyu
 * @Date 2021年04月02日-14:52
 */
public class MyHashMap<K, V> implements Map<K, V> {

	private int capacity = 16;
	private float loadFactor = .75f;
	private int threshHold;
	private int size = 0;

	private Set<K> keySet;
	private Set<Node> entrySet;
	private Collection<Node> values;
	private Node[] table;

	static class Node<K, V> {
		final int hash;
		final K key;
		V value;
		Node next;

		Node(int hash, K key, V value, Node next) {
			this.hash = hash;
			this.key = key;
			this.value = value;
			this.next = next;
		}

		public int getHash() {
			return hash;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public Node getNext() {
			return next;
		}

		public void setValue(V value) {
			this.value = value;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			Node<?, ?> node = (Node<?, ?>) o;
			return key.equals(node.key) &&
					value.equals(node.value);
		}

		@Override
		public int hashCode() {
			return Objects.hash(key, value);
		}
	}

	public MyHashMap() {
		this.threshHold = (int) (capacity * loadFactor);
	}

	private int hash(K key) {
		if (null == key) {
			return 0;
		}
		int h;
		return (h = key.hashCode()) ^ (h >>> 16);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean containsKey(Object key) {
		return keySet.contains(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return values.contains(value);
	}

	@Override
	public V get(Object key) {
		return null;
	}

	@Override
	public Object put(Object key, Object value) {
		Node<K, V> p;
		Node<K, V>[] tab;
		int n, i;
		if ((tab = table) == null || tab.length == 0) {

		}

		return null;
	}

	@Override
	public V remove(Object key) {
		return null;
	}

	@Override
	public void putAll(Map m) {

	}

	@Override
	public void clear() {

	}

	@Override
	public Set keySet() {
		return null;
	}

	@Override
	public Collection values() {
		return null;
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		return null;
	}
}
