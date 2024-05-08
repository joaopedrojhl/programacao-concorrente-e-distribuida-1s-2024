package teste;

public class Camareira extends Thread {
    private String nome;
    private Hotel hotel;

    public Camareira(String nome, Hotel hotel) {
        this.nome = nome;
        this.hotel = hotel;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public void run() {
        // Lógica da camareira (se necessário)
    }
}