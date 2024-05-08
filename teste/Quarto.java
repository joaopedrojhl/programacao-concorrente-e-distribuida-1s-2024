package teste;

public class Quarto extends Thread {
    private int numero;
    private boolean ocupado;
    private Hospede hospede;

    public Quarto(int numero) {
        this.numero = numero;
        this.ocupado = false;
    }

    public boolean reservar(Hospede hospede) {
        if (!ocupado) {
            ocupado = true;
            this.hospede = hospede;
            return true;
        }
        return false;
    }

    public void liberar() {
        ocupado = false;
        hospede = null;
    }

    public int getNumero() {
        return numero;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    @Override
    public void run() {
        // Lógica do quarto (se necessário)
    }
}

