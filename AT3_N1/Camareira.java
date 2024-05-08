package AT3_N1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Camareira extends Thread {
    private Quarto[] quartos;
    private Lock lock = new ReentrantLock();

    public Camareira(Quarto[] quartos) {
        this.quartos = quartos;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000); // Simula o tempo de limpeza da camareira

                // Limpa os quartos
                for (Quarto quarto : quartos) {
                    lock.lock();
                    try {
                        if (!quarto.estaOcupado() || quarto.chaveNaRecepcao()) {
                            limparQuarto(quarto);
                        }
                    } finally {
                        lock.unlock();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void limparQuarto(Quarto quarto) {
        synchronized (quarto) {
            if (quarto.estaOcupado()) {
                // Não limpa o quarto se os hóspedes ainda estiverem nele
                System.out.println("Camareira: Quarto " + quarto.hashCode() + " está ocupado, não pode ser limpo agora.");
                return;
            }

            System.out.println("Camareira limpando quarto " + quarto.hashCode());
            // Simula o tempo de limpeza
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            quarto.finalizarLimpeza();
            System.out.println("Quarto " + quarto.hashCode() + " limpo");
        }
    }
}
