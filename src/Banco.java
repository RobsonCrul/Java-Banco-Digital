import java.math.BigDecimal;
import java.util.*;

public class Banco {
    private final String nome;
    private final Map<Integer, Conta> contas = new HashMap<>();
    private int proximaConta = 1;

    public Banco(String nome){
        this.nome = nome;
    }

    public Conta criarContaCorrente(Cliente cliente, BigDecimal limiteEspecial){
        int numero = proximaConta++;
        Conta c = new ContaCorrente(cliente, numero, limiteEspecial);
        contas.put(numero, c);
        return c;
    }

    public Conta criarContaPoupanca(Cliente cliente){
        int numero = proximaConta++;
        Conta c = new ContaPoupanca(numero, cliente);
        contas.put(numero, c);
        return c;
    }

    public Optional<Conta> buscarConta(int numero){
        return Optional.ofNullable(contas.get(numero));
    }

    public Collection<Conta> listarContas(){
        return List.copyOf(contas.values());
    }

    public boolean removerConta(int numero){
        return contas.remove(numero) != null;
    }

    @Override
    public String toString() {
        return "Banco " + nome + " - " +contas.size() + " contas";
    }

}
