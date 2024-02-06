package src.Class.list;

import java.util.EmptyStackException;

public class SsafyStack<E> implements IStack<E>{

	private Node<E> top;
	
	@Override
	public void push(E e) {
		top = new Node<E>(e, top);
		
	}

	@Override
	public E pop() {
		if(isEmpty()) throw new EmptyStackException();
		Node<E> popNode = top;
		top = popNode.link;
		popNode.link = null;
		return popNode.data;
	}

	@Override
	public E peek() {
		if(isEmpty()) throw new EmptyStackException();
		return top.data;
	}

	@Override
	public boolean isEmpty() {
		
		return top == null;
	}

	@Override
	public int size() { // 모등 노드를 순회하는 로직으로 작성.(노드 순회 목적)

		int count = 0;
		
		for (Node<E> temp = top; temp!= null ; temp = temp.link) {
			count++;
		}
		return count;
	}

}
