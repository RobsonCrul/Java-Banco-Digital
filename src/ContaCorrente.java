import java.math.BigDecimal;

public final class ContaCorrente extends Conta{
    private final BigDecimal limiteChequeEspecial;

    public ContaCorrente(Cliente titular, int numero, BigDecimal limiteChequeEspecial) {
        super(titular, numero);
        this.limiteChequeEspecial = limiteChequeEspecial == null ? BigDecimal.ZERO : limiteChequeEspecial;
    }

    @Override
    protected boolean podeSacar(BigDecimal valor){
        //Permite usar cheque especial até limite
        BigDecimal disponivel = getSaldo().add(limiteChequeEspecial);
        return disponivel.compareTo(valor) >= 0;
    }

    public BigDecimal getLimiteChequeEspecial(){
        return limiteChequeEspecial;
    }
}
