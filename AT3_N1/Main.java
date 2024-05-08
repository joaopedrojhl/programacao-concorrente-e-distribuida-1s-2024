package AT3_N1;

public class Main {
    public static void main(String[] args) {
        // Criando quartos
        Quarto[] quartos = new Quarto[10];
        for (int i = 0; i < 10; i++) {
            quartos[i] = new Quarto();
        }

        // Criando recepcionista e camareiras
        Recepcionista recepcionista = new Recepcionista(quartos);
        Camareira[] camareiras = new Camareira[10];
        for (int i = 0; i < 10; i++) {
            camareiras[i] = new Camareira(quartos);
        }

        // Iniciando as threads dos quartos, recepcionista e camareiras
        for (Quarto quarto : quartos) {
            quarto.start();
        }
        recepcionista.start();
        for (Camareira camareira : camareiras) {
            camareira.start();
        }

        // Simula a chegada de pessoas
        for (int i = 0; i < 50; i++) { // Ajustado para 50 hóspedes
            Hospede hospede = new Hospede(quartos);
            hospede.start();
            try {
                Thread.sleep(1000); // Simula intervalo entre chegadas de hóspedes
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
