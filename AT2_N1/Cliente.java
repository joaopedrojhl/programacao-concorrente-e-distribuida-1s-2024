package AT2_N1;

import AT2_N1.Conta;
import AT2_N1.Loja;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cliente extends Pessoa implements Runnable {
    private Conta conta;
    private Loja loja01;
    private Loja loja02;
    private Lock lock;

    public Cliente(String nome, String endereco, String cpf, Conta conta, Loja loja1, Loja loja2) {
        super(nome, endereco, cpf);
        this.conta = conta;
        this.loja01 = loja01;
        this.loja02 = loja02;
        this.lock = new ReentrantLock();
    }

    @Override
    public void run() {
        realizarCompras();
    }

    public void realizarCompras() {
        while (true) {
            int valorCompra = Math.random() < 0.5 ? 100 : 200;
            lock.lock();
            try {
                if (conta.getSaldo() >= valorCompra) {
                    conta.sacar(valorCompra);
                    String nomeLoja = Math.random() < 0.5 ? loja01.getNome() : loja02.getNome();
                    System.out.println("INICIANDO COMPRA!!");
                    System.out.println(getNome() + " realizou uma compra de R$" + valorCompra + " na loja " + nomeLoja);
                    if (Math.random() < 0.5) {
                        loja01.receberPagamento(valorCompra);
                    } else {
                        loja02.receberPagamento(valorCompra);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.err.println("Thread interrupted enquanto sleeping: " + e.getMessage());
                    }
                } else {
                    System.out.println(
                            getNome() + " não possui saldo suficiente para realizar a compra de R$" + valorCompra);
                    break;
                }
            } finally {
                lock.unlock();
            }
        }
        System.out.println(getNome() + " finalizou suas compras. Saldo final: R$" + conta.getSaldo());
    }
}