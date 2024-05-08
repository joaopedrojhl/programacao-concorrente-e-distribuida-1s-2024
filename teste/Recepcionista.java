package teste;

public class Recepcionista extends Thread {
    private String nome;
    private Hotel hotel;

    public Recepcionista(String nome, Hotel hotel) {
        this.nome = nome;
        this.hotel = hotel;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public void run() {
        // Lógica do recepcionista (se necessário)
    }
}
