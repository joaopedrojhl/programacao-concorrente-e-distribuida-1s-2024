package AT2_N1;

import AT2_N1.Banco;
import AT2_N1.Conta;
import AT2_N1.Loja;
import AT2_N1.Cliente;
import AT2_N1.Funcionario;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco("Bradesco", 1000000000);

        Conta contaLoja01 = new Conta("1", 0);
        Conta contaLoja02 = new Conta("2", 0);

        List<Funcionario> funcionariosLoja01 = new ArrayList<>();
        List<Funcionario> funcionariosLoja02 = new ArrayList<>();

        Conta contaFunc01Loja01 = new Conta("Func01Loja01", 0);
        Conta contaFunc02Loja01 = new Conta("Func02Loja01", 0);
        Conta contaFunc01Loja02 = new Conta("Func01Loja02", 0);
        Conta contaFunc02Loja02 = new Conta("Func02Loja02", 0);

        Conta contaInvestimentoFuncionario1Loja01 = new Conta("InvestimentoFuncionario1Loja01", 0);
        Conta contaInvestimentoFuncionario2Loja01 = new Conta("InvestimentoFuncionario2Loja01", 0);
        Conta contaInvestimentoFuncionario1Loja02 = new Conta("InvestimentoFuncionario1Loja02", 0);
        Conta contaInvestimentoFuncionario2Loja02 = new Conta("InvestimentoFuncionario2Loja02", 0);

        Funcionario funcionario1Loja01 = new Funcionario("Func01Loja1", "End01", "CPF01", 1400,
                contaFunc01Loja01, contaInvestimentoFuncionario1Loja01, contaLoja01);
        Funcionario funcionario2Loja01 = new Funcionario("Func02Loja1", "End02", "CPF02", 1400,
                contaFunc02Loja01, contaInvestimentoFuncionario2Loja01, contaLoja02);
        Funcionario funcionario1Loja02 = new Funcionario("Func01Loja2", "End03", "CPF03", 1400,
                contaFunc01Loja02, contaInvestimentoFuncionario1Loja02, contaLoja01);
        Funcionario funcionario2Loja02 = new Funcionario("Func02Loja2", "End04", "CPF04", 1400,
                contaFunc02Loja02, contaInvestimentoFuncionario2Loja02, contaLoja02);

        funcionariosLoja01.add(funcionario1Loja01);
        funcionariosLoja01.add(funcionario2Loja01);
        funcionariosLoja02.add(funcionario1Loja02);
        funcionariosLoja02.add(funcionario2Loja02);

        Loja loja01 = new Loja("Loja 1", banco, contaLoja01, funcionariosLoja01);
        Loja loja02 = new Loja("Loja 2", banco, contaLoja02, funcionariosLoja02);

        Conta contaCliente01 = new Conta("Conta01", 1000);
        Conta contaCliente02 = new Conta("Conta02", 1000);
        Conta contaCliente03 = new Conta("Conta03", 1000);
        Conta contaCliente04 = new Conta("Conta04", 1000);
        Conta contaCliente05 = new Conta("Conta05", 1000);

        Cliente cliente01 = new Cliente("Cliente01", "End", "CPF01", contaCliente01, loja01, loja02);
        Cliente cliente02 = new Cliente("Cliente02", "End", "CPF02", contaCliente02, loja01, loja02);
        Cliente cliente03 = new Cliente("Cliente03", "End3", "CPF03", contaCliente03, loja01, loja02);
        Cliente cliente04 = new Cliente("Cliente04", "End4", "CPF04", contaCliente04, loja01, loja02);
        Cliente cliente05 = new Cliente("Cliente05", "End5", "CPF05", contaCliente05, loja01, loja02);

        Thread threadCliente01 = new Thread(cliente01);
        Thread threadCliente02 = new Thread(cliente02);
        Thread threadCliente03 = new Thread(cliente03);
        Thread threadCliente04 = new Thread(cliente04);
        Thread threadCliente05 = new Thread(cliente05);

        threadCliente01.start();
        threadCliente02.start();
        threadCliente03.start();
        threadCliente04.start();
        threadCliente05.start();

        Thread threadFuncionario1Loja01 = new Thread(funcionario1Loja01);
        Thread threadFuncionario2Loja01 = new Thread(funcionario2Loja01);
        Thread threadFuncionario1Loja02 = new Thread(funcionario1Loja02);
        Thread threadFuncionario2Loja02 = new Thread(funcionario2Loja02);

        try {
            threadCliente01.join();
            threadCliente02.join();
            threadCliente03.join();
            threadCliente04.join();
            threadCliente05.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadFuncionario1Loja01.start();
        threadFuncionario2Loja01.start();
        threadFuncionario1Loja02.start();
        threadFuncionario2Loja02.start();

        try {
            threadFuncionario1Loja01.join();
            threadFuncionario2Loja01.join();
            threadFuncionario1Loja02.join();
            threadFuncionario2Loja02.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}