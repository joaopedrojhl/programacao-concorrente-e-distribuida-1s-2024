package aula04;

public class Incrementer extends Thread{
    private Counter counter;

    public Incrementer (Counter counter){
        this.counter = counter;
    }
    public void run(){
        System.out.println(
            this.getName() + " está incrementando contador...");
        this.counter.increment();
    }
}
