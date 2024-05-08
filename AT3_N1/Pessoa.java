package AT3_N1;

public class Pessoa {
    private static int contador = 0;
    private int id;
    private int tentativas;

    public Pessoa() {
        this.id = ++contador;
        this.tentativas = 0;
    }

    public int getId() {
        return id;
    }

    public int getTentativas() {
        return tentativas;
    }

    public void incrementarTentativas() {
        tentativas++;
    }

    public void quartoAlocado() {
        tentativas = 0; // Reseta o número de tentativas quando a pessoa consegue alugar um quarto
    }
}

