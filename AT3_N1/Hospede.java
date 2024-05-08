package AT3_N1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Hospede extends Thread {
    private Quarto[] quartosDisponiveis;
    private final Lock lock = new ReentrantLock();

    public Hospede(Quarto[] quartosDisponiveis) {
        this.quartosDisponiveis = quartosDisponiveis;
    }

    @Override
    public void run() {
        lock.lock();
        try {
            int numHospedes = (int) (Math.random() * 6) + 1;
            int numQuartosNecessarios = (numHospedes + 3) / 4;

            Quarto[] quartosReservados = new Quarto[numQuartosNecessarios];

            for (int i = 0; i < numQuartosNecessarios; i++) {
                boolean quartoCheio = true;
                for (Quarto quarto : quartosDisponiveis) {
                    if (!quarto.estaOcupado() && !quarto.estaCheio()) {
                        quartoCheio = false;
                        if (quarto.reservar()) {
                            quartosReservados[i] = quarto;
                            quarto.adicionarHospede();
                            break;
                        }
                    }
                }
                if (quartoCheio) {
                    System.out.println("Não há quartos disponíveis para acomodar o grupo " + this.getId());
                    break;
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (Quarto quarto : quartosReservados) {
                if (quarto != null) {
                    if (quarto.todosHospedesSairam()) {
                        // Se todos os hóspedes do quarto decidiram sair, então todos saem juntos
                        quarto.liberar(); // Libera o quarto
                        quarto.removerHospede(); // Remove os hóspedes
                    }
                }
            }
        } finally {
            lock.unlock();
        }
    }
}
