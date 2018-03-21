package com.zxchaos;

/**
 * 二叉查找树
 * 
 * @author zhangxin
 */
public class BinarySearchTree<K extends Comparable<K>, V> {

	private Node root;

	private class Node {
		K key;
		V value;
		Node left;// 左子树
		Node right;// 右子树
		int n;// 以该节点为根节点的树中节点数目

		public Node(K key, V value, int n) {
			this.key = key;
			this.value = value;
			this.n = n;
		}
	}

	public V get(K key) {
		V res = get(root, key);
		return res;
	}

	private V get(Node node, K key) {
		if (node == null) {
			return null;
		}
		int res = node.key.compareTo(key);
		if (res > 0) {
			return get(node.left, key);
		} else if (res < 0) {
			return get(node.right, key);
		}
		return node.value;
	}

	public void put(K key, V value) {
		root = put(root, key, value);
	}

	private Node put(Node node, K key, V value) {
		if (node == null) {
			return new Node(key, value, 1);
		}

		int res = node.key.compareTo(key);
		if (res < 0) {
			node.right = put(node.right, key, value);
		} else if (res > 0) {
			node.left = put(node.left, key, value);
		} else {
			node.value = value;
		}
		node.n = size(node.left) + size(node.right) + 1;
		return node;
	}

	public K min() {
		Node node = min(root);
		return node.key;
	}

	private Node min(Node node) {
		if (node == null) {
			return null;
		}
		if (node.left == null) {
			return node;
		}
		return min(node.left);
	}

	public void delete(K key) {
		root = delete(root, key);
	}

	private Node delete(Node node, K key) {
		if (node == null) {
			return null;
		}
		System.out.println("node key:" + node.key + ", node value:" + node.value);
		int res = node.key.compareTo(key);
		if (res > 0) {
			node.left = delete(node.left, key);
		} else if (res < 0) {
			node.right = delete(node.right, key);
		} else {
			if (node.right == null) {
				return node.left;
			} else if (node.left == null) {
				return node.right;
			} else {// 左右子树都不为空
				Node min = min(node.right);
				min.left = node.left;
				min.right = deleteMin(node.right);
				node = min;
				System.out.println("min, key:" + min.key + ", min value:" + min.value);
			}
		}
		node.n = size(node.left) + size(node.right) + 1;
		return node;
	}

	public void deleteMin() {
		root = deleteMin(root);
	}

	private Node deleteMin(Node node) {
		if (node == null) {
			return null;
		}
		if (node.left == null) {
			return null;
		}
		node.left = deleteMin(node.left);
		return node;
	}

	public int size() {
		return size(root);
	}

	private int size(Node node) {
		if (node == null) {
			return 0;
		}
		return size(node.left) + size(node.right) + 1;
	}

	public static void main(String[] args) {
		BinarySearchTree<String, Integer> bst = new BinarySearchTree<>();
		bst.put("vvv", 111);
		bst.put("aaa", 222);
		bst.put("bbb", 333);
		bst.put("eee", 444);
		System.out.println(bst.get("bbb"));
		System.out.println(bst.get("eee"));
		bst.delete("eee");
		System.out.println(bst.get("eee"));
		System.out.println("size:" + bst.size());
	}

}
