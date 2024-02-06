package src.Class.list;

public class SsafyStackTesting {

	public static void main(String[] args) {
		IStack<String> stack = new SsafyStack<String>();
		System.out.println(stack.isEmpty() + "//" + stack.size());
		stack.push("갓병찬");
		stack.push("자바의신전은수");
		stack.push("김동근");
		stack.push("닥터홍");
		stack.push("The Java 조용준");
		stack.push("축신 정상훈");
		
		System.out.println(stack.isEmpty() + "//" + stack.size());
		stack.push("a");
		stack.push("b"); 
		
		System.out.println(stack.peek());System.out.println(stack.isEmpty() + "//" + stack.size());
		System.out.println(stack.pop());
		System.out.println(stack.isEmpty() + "//" + stack.size());
		
		while(!stack.isEmpty()) {
			System.out.println(stack.pop());
		}


	}

}
