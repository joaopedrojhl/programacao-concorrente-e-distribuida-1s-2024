package aula02;

public class Ola extends Thread {
	public void run() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(this.getName() + " Entrou em run...");
		System.out.println("Nome da thread: " + this.getName());
		System.out.println(this.getName() + " Ola!!!");	
	}
}
