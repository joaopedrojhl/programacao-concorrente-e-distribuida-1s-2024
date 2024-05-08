package AT3_N1;

import java.util.Queue;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Recepcionista extends Thread {
    private Queue<Pessoa> filaEspera;
    private Quarto[] quartos;
    private Lock lock = new ReentrantLock();
    private Condition condicaoQuartoDisponivel = lock.newCondition();

    public Recepcionista(Quarto[] quartos) {
        this.quartos = quartos;
        this.filaEspera = new LinkedList<>();
    }

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                boolean quartosDisponiveis = false;
                for (Quarto quarto : quartos) {
                    if (!quarto.estaOcupado()) {
                        quartosDisponiveis = true;
                        break;
                    }
                }

                if (!quartosDisponiveis) {
                    Pessoa pessoa = new Pessoa();
                    filaEspera.add(pessoa);
                    System.out.println("Pessoa " + pessoa.getId() + " entrou na fila de espera.");

                    try {
                        condicaoQuartoDisponivel.await(60000, TimeUnit.MILLISECONDS); // Espera por 1 minuto
                        System.out.println("Pessoa " + pessoa.getId() + " esperou muito tempo. Decide passear.");
                        continue; // A pessoa decide passear
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (!filaEspera.isEmpty()) {
                    Pessoa pessoa = filaEspera.poll();
                    System.out.println("Pessoa " + pessoa.getId() + " saiu da fila de espera e tenta alugar um quarto.");

                    boolean quartoAlocado = false;
                    for (Quarto quarto : quartos) {
                        if (!quarto.estaOcupado()) {
                            if (quarto.reservar()) {
                                quartoAlocado = true;
                                break;
                            }
                        }
                    }

                    if (!quartoAlocado) {
                        System.out.println("Pessoa " + pessoa.getId() + " não conseguiu alugar um quarto. Deixa uma reclamação e vai embora.");
                        continue;
                    }

                    pessoa.quartoAlocado();
                    notificarQuartoDisponivel();
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public void notificarQuartoDisponivel() {
        lock.lock();
        try {
            condicaoQuartoDisponivel.signal();
        } finally {
            lock.unlock();
        }
    }
}
