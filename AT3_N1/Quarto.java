package AT3_N1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Quarto extends Thread {
    private boolean ocupado;
    private int numeroHospedes;
    private final int capacidadeMaxima = 4;
    private boolean chaveNaRecepcao;
    private boolean emLimpeza;
    private Lock lock = new ReentrantLock();
    private Condition condicaoCamareira = lock.newCondition();

    public Quarto() {
        this.ocupado = false;
        this.numeroHospedes = 0;
        this.chaveNaRecepcao = false; // Inicialmente, a chave não está na recepção
        this.emLimpeza = false; // Inicialmente, o quarto não está em limpeza
    }

    public synchronized boolean reservar() {
        if (!ocupado && !chaveNaRecepcao && !emLimpeza) {
            ocupado = true;
            chaveNaRecepcao = true; // Após a reserva, a chave é colocada na recepção
            return true; // Quarto reservado com sucesso
        }
        return false; // Quarto já está ocupado, chave está na recepção ou em limpeza
    }

    public synchronized void liberar() {
        ocupado = false;
        chaveNaRecepcao = true; // Ao liberar, a chave é colocada na recepção
        numeroHospedes = 0; // Ao liberar, o número de hóspedes é resetado
        emLimpeza = true; // Inicia a limpeza do quarto
        condicaoCamareira.signal(); // Sinaliza para a camareira que o quarto está pronto para limpeza
    }

    public synchronized void pegarChave() {
        chaveNaRecepcao = false;
    }

    public synchronized void finalizarLimpeza() {
        emLimpeza = false; // Finaliza a limpeza do quarto
    }

    public synchronized void adicionarHospede() {
        if (numeroHospedes < capacidadeMaxima) {
            numeroHospedes++;
        }
    }

    public synchronized void removerHospede() {
        if (numeroHospedes > 0) {
            numeroHospedes--;
        }
    }

    public synchronized boolean estaOcupado() {
        return ocupado;
    }

    public synchronized boolean chaveNaRecepcao() {
        return chaveNaRecepcao;
    }

    public synchronized boolean estaCheio() {
        return numeroHospedes >= capacidadeMaxima;
    }

    public synchronized boolean todosHospedesSairam() {
        return numeroHospedes == 0;
    }

    public void aguardarLimpeza() throws InterruptedException {
        lock.lock();
        try {
            while (emLimpeza) {
                condicaoCamareira.await(); // Aguarda a limpeza do quarto
            }
        } finally {
            lock.unlock();
        }
    }

    public void run() {
        // A implementação da thread do quarto não é necessária neste caso
        // Podemos removê-la
    }
}
