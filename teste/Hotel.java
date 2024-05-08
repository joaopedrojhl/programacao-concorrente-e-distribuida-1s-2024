package teste;

import java.util.concurrent.locks.*;

public class Hotel {
    private Quarto[] quartos;
    private Lock lock;

    public Hotel(int numQuartos) {
        quartos = new Quarto[numQuartos];
        for (int i = 0; i < numQuartos; i++) {
            quartos[i] = new Quarto(i + 1);
        }
        lock = new ReentrantLock();
    }

    public boolean reservarQuarto(Hospede hospede) {
        lock.lock();
        try {
            for (Quarto quarto : quartos) {
                if (!quarto.isOcupado()) {
                    if (quarto.reservar(hospede)) {
                        System.out.println(hospede.getNome() + " reservou o quarto " + quarto.getNumero());
                        return true;
                    }
                }
            }
            System.out.println("Todos os quartos estão ocupados.");
            return false;
        } finally {
            lock.unlock();
        }
    }

    public void liberarQuarto(int numQuarto) {
        lock.lock();
        try {
            quartos[numQuarto - 1].liberar();
            System.out.println("Quarto " + numQuarto + " foi liberado.");
        } finally {
            lock.unlock();
        }
    }
}
