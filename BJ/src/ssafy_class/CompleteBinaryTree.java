package ssafy_class;

import java.util.ArrayDeque;
import java.util.Queue;

public class CompleteBinaryTree<T> {
	
	private Object[] nodes;
	private final int SIZE;
	private int lastIndex;
	
	public CompleteBinaryTree(int size) {
		SIZE = size;
		nodes = new Object[size+1];
	}
	
	public boolean isEmpty() {
		return lastIndex == 0;
	}
	public boolean isFull() {
		return lastIndex==SIZE;
	}
	public void add(T e) {
		if(isFull()) {
			System.out.println("isFull!");
			return;
		}
		nodes[++lastIndex] = e;
	}
	
	public void bfs() {
		if(isEmpty()) return;
		
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(1); // 방문할 노드를 관리할 값 넣기 !(노드 번호인덱스)
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			System.out.println(nodes[current]);
			
			if(current*2 <= lastIndex) {
				queue.offer(current*2);
			}
			if(current*2+1 <= lastIndex) {
				queue.offer(current*2+1);
			}
		}
	}
	public static void main(String[] args) {
		int N = 6;
		CompleteBinaryTree<Character> tree = new CompleteBinaryTree<>(10);
		for(int i = 0; i < 10; i++) {
			tree.add((char)(65+i));
		}
//		tree.bfs();
		tree.dfsByPreorder();
		tree.dfsByInorder();
		tree.dfsByPostorder();


	}
	
	public void dfsByPreorder() {
		if(isEmpty()) return;
		System.out.println("=============Preorder=============");
		dfsByPreorder(1);
		System.out.println();
	}
	//전위순회
	private void dfsByPreorder(int current) {
		System.out.print(nodes[current]+ " ");
		if(current*2 <= lastIndex) {
			dfsByPreorder(current*2);
		}

		if(current*2+1 <= lastIndex) {
			dfsByPreorder(current*2+1);
		}
	}
	
	public void dfsByInorder() {
		if(isEmpty()) return;
		System.out.println("=============Inorder=============");
		dfsByInorder(1);
		System.out.println();
	}
	//전위순회
	private void dfsByInorder(int current) {
		
		if(current*2 <= lastIndex) {
			dfsByInorder(current*2);
		}
		System.out.print(nodes[current]+ " ");
		if(current*2+1 <= lastIndex) {
			dfsByInorder(current*2+1);
		}
	}
	
	public void dfsByPostorder() {
		if(isEmpty()) return;
		System.out.println("=============Postorder=============");
		dfsByPostorder(1);
		System.out.println();
	}
	//전위순회
	private void dfsByPostorder(int current) {
		if(current*2 <= lastIndex) {
			dfsByPostorder(current*2);
		}

		if(current*2+1 <= lastIndex) {
			dfsByPostorder(current*2+1);
		}
		System.out.print(nodes[current]+ " ");
	}

}
