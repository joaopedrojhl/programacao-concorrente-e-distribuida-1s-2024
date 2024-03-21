package aula2;

public class MainThread {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Dentro da main!");
		System.out.println("Nome da thread main: " + Thread.currentThread().getName());
		Ola ola1 = new Ola();
		Ola ola2 = new Ola();
		Ola ola3 = new Ola();
		Tchau tchau1 = new Tchau();
		Tchau tchau2 = new Tchau();
		
		ola1.start();
		ola2.start();
		ola3.start();
		tchau1.start();
		tchau2.start();
		
		ola1.join();
		ola2.join();
		ola3.join();
		tchau1.join();
		tchau2.join();
		
		System.out.println("Acabou a main!");

	}
}
