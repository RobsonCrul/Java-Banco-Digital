import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract sealed class Conta permits ContaCorrente, ContaPoupanca {

    private final int numero;
    private final Cliente titular;
    protected BigDecimal saldo;
    private List<String> extrato = new ArrayList<>(); //Armazenar linhs simples de extrato

    public Conta(Cliente titular, int numero) {
        this.titular = titular;
        this.numero = numero;
        this.saldo = BigDecimal.ZERO;
        this.extrato = new ArrayList<>();
        registrarExtrato("Conta criada: " + LocalDateTime.now());
    }

    public int getNumero() {
        return numero;
    }

    public Cliente getTitular() {
        return titular;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    protected void registrarExtrato(String linha){
        extrato.add(LocalDateTime.now() + " - " + linha);
    }

    public List<String> getExtrato(){
        return List.copyOf(extrato);
    }

    public void depositar(BigDecimal valor){
        if(valor.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Valor do depósito deve ser positivo");
        }
        saldo = saldo.add(valor);
        registrarExtrato("Depósito: " + valor);
    }

    public void sacar(BigDecimal valor){
        if(valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor de saque deve ser positivo.");
        }
        if(!podeSacar(valor)){
            throw new IllegalArgumentException("Saldo insuficiente");
        }
        saldo = saldo.subtract(valor);
        registrarExtrato("Saque: -R$" + valor);
    }

    public void transferir(Conta destino, BigDecimal valor){
        if (destino == null) throw new IllegalArgumentException("Conta destino inválida");
        if (valor.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("Valor deve ser positivo");
        if(!podeSacar(valor)) throw new IllegalArgumentException("Saldo insuficiente para transferência");
        this.saldo = this.saldo.subtract(valor);
        destino.saldo = destino.saldo.add(valor);
        this.registrarExtrato("Transferência para conta " + destino.numero + ": -" + valor);
        destino.registrarExtrato("Transferência recebida da conta "+ this.numero + ": +" + valor);
    }

    protected boolean podeSacar(BigDecimal valor) {
        return false;
    }

    @Override
    public String toString() {
        return "Conta{" +
                "numero=" + numero +
                ", titular=" + titular +
                ", saldo=" + saldo +
                '}';
    }
}
