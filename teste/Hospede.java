package teste;

public class Hospede extends Thread {
    private String nome;
    private Hotel hotel;

    public Hospede(String nome, Hotel hotel) {
        this.nome = nome;
        this.hotel = hotel;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public void run() {
        hotel.reservarQuarto(this);
        // Lógica do hospede após reservar quarto
    }
}
