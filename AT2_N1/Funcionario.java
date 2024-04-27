package AT2_N1;

import AT2_N1.Conta;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Funcionario extends Pessoa implements Runnable {
    private double salario;
    private Conta contaSalario;
    private Conta contaInvestimentos;
    private boolean recebeuSalario;
    private Lock lock;

    public Funcionario(String nome, String endereco, String cpf, double salario, Conta contaSalario,
            Conta contaInvestimentos, Conta contaLoja) {
        super(nome, endereco, cpf);
        this.salario = salario;
        this.contaSalario = contaSalario;
        this.contaInvestimentos = contaInvestimentos;
        this.recebeuSalario = false;
        this.lock = new ReentrantLock();
    }

    public boolean recebeuSalario() {
        return recebeuSalario;
    }

    @Override
    public void run() {
        realizarOperacoes();
    }

    private void realizarOperacoes() {
        lock.lock();
        try {
            receberSalario(salario);
            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread interrupted while sleeping: " + e.getMessage());
            }
        } finally {
            lock.unlock();
        }
    }

    public void receberSalario(Double salarioPago) {
        salarioPago = salario;
        contaSalario.depositar(salario);
        recebeuSalario = true;
    }

    public void investirSalario() {
        double valorInvestimento = salario * 0.2;
        if (contaSalario.getSaldo() >= valorInvestimento) {
            contaSalario.sacar(valorInvestimento);
            contaInvestimentos.depositar(valorInvestimento);
            System.out.println("### Investimento de Salário ###");
            System.out.println(getNome() + " investiu R$" + valorInvestimento + " em conta de investimentos");
        } else {
            System.out.println(getNome() + " não possui saldo suficiente para investir R$" + valorInvestimento);
        }
    }

    public double getSalario() {
        return salario;
    }

    public Conta getContaSalario(){
        return contaSalario;
    }
}