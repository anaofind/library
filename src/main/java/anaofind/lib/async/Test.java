package anaofind.lib.async;

public class Test {

	public static void main(String[] args) {
		
		Test test = new Test();
		Async.call(() -> test.add(1, 2)).then((res) -> {
			System.out.println(res+1);
			System.out.print("3 + 4 = ");
			Async.call(() -> test.printADD(3,4)).await();
			System.out.println("END");
		});
		System.out.println("RES WAIT");
		
	}
	
	public int add(int a, int b) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a+b;
	}
	
	
	public Void printADD(int a, int b) {
		System.out.println(add(a,b));
		return null;
	}
	
}
