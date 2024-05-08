package teste;

import AT3_N1.Camareira;
import AT3_N1.Recepcionista;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel(10); // Cria um hotel com 10 quartos

        // Cria hóspedes, camareiras e recepcionistas e inicia as threads
        for (int i = 0; i < 50; i++) {
            new Hospede("Hospede " + (i + 1), hotel).start();
        }

        for (int i = 0; i < 10; i++) {
            new Camareira("Camareira " + (i + 1), hotel).start();
        }

        for (int i = 0; i < 5; i++) {
            new Recepcionista("Recepcionista " + (i + 1), hotel).start();
        }
    }
}
