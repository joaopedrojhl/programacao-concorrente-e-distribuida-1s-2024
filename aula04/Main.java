package aula04;

public class Main {
    public static void main(String[] args) {
        while (true) {

            Counter counter = new Counter(0);

            Incrementer inc1 = new Incrementer(counter);
            Incrementer inc2 = new Incrementer(counter);

            inc1.start();
            inc2.start();

            try {
                inc1.join();
                inc2.join();
            } catch (InterruptedException e) {
                System.out.println("Erro: Thread foi interrompida!!!");
            }
            if (counter.getValue() != 2) {
                System.out.println("Valor final do contador: " + counter.getValue());

                break;
            }

        }
    }
}
