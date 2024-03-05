package ssafy_class;

import java.util.ArrayDeque;
import java.util.Queue;


public class QueueAPITest {

	public static void main(String[] args) {

		Queue<String> queue = new ArrayDeque<String>();
		System.out.println(queue.isEmpty() + "//" + queue.size());
		queue.offer("갓병찬");
		queue.offer("자바의신전은수");
		queue.offer("김동근");
		queue.offer("닥터홍");
		queue.offer("The Java 조용준");
		queue.offer("축신 정상훈");
		
		System.out.println(queue.isEmpty() + "//" + queue.size());
		queue.offer("a");
		queue.offer("b"); 
		
		System.out.println(queue.peek());System.out.println(queue.isEmpty() + "//" + queue.size());
		System.out.println(queue.poll());
		System.out.println(queue.isEmpty() + "//" + queue.size());
		
		while(!queue.isEmpty()) {
			System.out.println(queue.poll());
		}
	

	}

}
