import java.util.Random;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
		Random gen = new Random();
		System.out.println("********Simple Tester********");
		System.out.println("Input values:");
		for(int i = 0; i<10; i++) {
			int val = gen.nextInt(50) + 1;
			list.addLast(val);
			System.out.print(val);
			if(i != 9) {
				System.out.print(", ");
			}
		}
		System.out.println();
		System.out.println("Output:\n" + list); //note use of toString method of list object here
		
	}

}
