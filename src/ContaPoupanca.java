import java.math.BigDecimal;

public final class ContaPoupanca extends Conta{
    public ContaPoupanca(int numero, Cliente titular){
        super(titular, numero);
    }

    @Override
    protected boolean podeSacar(BigDecimal valor) {
        //Poupança não permite saldo negativo
        return getSaldo().compareTo(valor) >= 0;
    }

    //Método para aplicar rendimento mensal
    public void aplicarRendimentoMensal(double taxaPercentual){
        if(taxaPercentual <= 0) return;
        BigDecimal taxa = BigDecimal.valueOf(taxaPercentual / 100.0);
        BigDecimal rendimento = getSaldo().multiply(taxa);
        if(rendimento.compareTo(BigDecimal.ZERO) > 0){
            saldo = saldo.add(rendimento);
            registrarExtrato("Rendimento aplicado: + " + rendimento);
        }
    }
}
