package ssafy_class;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueTest {

	public static void main(String[] args) {

		queueTest(new ArrayDeque<>());
		queueTest(new PriorityQueue<>());
		usePhone();
	}
	
	public static void queueTest(Queue<Integer> q) {
		q.offer(10);
		q.offer(50);
		q.offer(30);
		while(!q.isEmpty()) {
			System.out.println(q.getClass().getSimpleName()+" : " + q.poll());
		}
	}

	private static void usePhone() {
		PriorityQueue<SmartPhone> q = new PriorityQueue<>((p1,p2) -> {
			if(p1.price == p2.price) {
				return p1.num.compareTo(p2.num);
			}else {
				return Integer.compare(p1.price, p2.price);
			}
		});
		// 가격이 같으면 num의 오름차순으로 정렬하시오.
		
		q.offer(new SmartPhone(100, "010"));
		q.offer(new SmartPhone(100, "111"));
		q.offer(new SmartPhone(40, "000"));
		
		while(!q.isEmpty()) {
			System.out.println(q.poll().num);
		}
	}
	static class SmartPhone{
		int price;
		String num;
		
		public SmartPhone(int price, String num) {
			super();
			this.price = price;
			this.num = num;
		}
		
	}
}
