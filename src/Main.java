import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Banco banco = new Banco("Banco OO");

    public static void main(String[] args) {
        seedDados(); // dados iniciais para testar
        menuPrincipal();
    }

    private static void seedDados() {
        Cliente c1 = new Cliente("Robson", "123.456.789-00");
        Cliente c2 = new Cliente("Maria", "987.654.321-00");
        var cc = banco.criarContaCorrente(c1, BigDecimal.valueOf(500));
        var cp = banco.criarContaPoupanca(c2);
        cc.depositar(BigDecimal.valueOf(1000));
        cp.depositar(BigDecimal.valueOf(300));
    }

    private static void menuPrincipal() {
        while (true) {
            System.out.println("\n=== Banco OO ===");
            System.out.println("1. Criar conta corrente");
            System.out.println("2. Criar conta poupança");
            System.out.println("3. Depositar");
            System.out.println("4. Sacar");
            System.out.println("5. Transferir");
            System.out.println("6. Ver extrato");
            System.out.println("7. Listar contas");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            String opc = scanner.nextLine().trim();
            try {
                switch (opc) {
                    case "1" -> criarContaCorrente();
                    case "2" -> criarContaPoupanca();
                    case "3" -> depositar();
                    case "4" -> sacar();
                    case "5" -> transferir();
                    case "6" -> verExtrato();
                    case "7" -> listarContas();
                    case "0" -> { System.out.println("Até mais!"); return; }
                    default -> System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private static void criarContaCorrente() {
        System.out.print("Nome do titular: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Limite cheque especial (ex: 500): ");
        BigDecimal limite = new BigDecimal(scanner.nextLine());
        Cliente cliente = new Cliente(nome, cpf);
        var conta = banco.criarContaCorrente(cliente, limite);
        System.out.println("Conta corrente criada. Número: " + conta.getNumero());
    }

    private static void criarContaPoupanca() {
        System.out.print("Nome do titular: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        Cliente cliente = new Cliente(nome, cpf);
        var conta = banco.criarContaPoupanca(cliente);
        System.out.println("Conta poupança criada. Número: " + conta.getNumero());
    }

    private static void depositar() {
        System.out.print("Número da conta: ");
        int num = Integer.parseInt(scanner.nextLine());
        var opt = banco.buscarConta(num);
        if (opt.isEmpty()) { System.out.println("Conta não encontrada."); return; }
        System.out.print("Valor: ");
        BigDecimal val = new BigDecimal(scanner.nextLine());
        opt.get().depositar(val);
        System.out.println("Depósito realizado. Saldo: " + opt.get().getSaldo());
    }

    private static void sacar() {
        System.out.print("Número da conta: ");
        int num = Integer.parseInt(scanner.nextLine());
        var opt = banco.buscarConta(num);
        if (opt.isEmpty()) { System.out.println("Conta não encontrada."); return; }
        System.out.print("Valor: ");
        BigDecimal val = new BigDecimal(scanner.nextLine());
        opt.get().sacar(val);
        System.out.println("Saque realizado. Saldo: " + opt.get().getSaldo());
    }

    private static void transferir() {
        System.out.print("Conta origem: ");
        int origem = Integer.parseInt(scanner.nextLine());
        var o = banco.buscarConta(origem);
        if (o.isEmpty()) { System.out.println("Conta origem não encontrada."); return; }
        System.out.print("Conta destino: ");
        int destino = Integer.parseInt(scanner.nextLine());
        var d = banco.buscarConta(destino);
        if (d.isEmpty()) { System.out.println("Conta destino não encontrada."); return; }
        System.out.print("Valor: ");
        BigDecimal val = new BigDecimal(scanner.nextLine());
        o.get().transferir(d.get(), val);
        System.out.println("Transferência efetuada.");
    }

    private static void verExtrato() {
        System.out.print("Número da conta: ");
        int num = Integer.parseInt(scanner.nextLine());
        var opt = banco.buscarConta(num);
        if (opt.isEmpty()) { System.out.println("Conta não encontrada."); return; }
        System.out.println("Extrato da conta " + num + ":");
        opt.get().getExtrato().forEach(System.out::println);
        System.out.println("Saldo atual: " + opt.get().getSaldo());
    }

    private static void listarContas() {
        System.out.println("Contas no banco:");
        banco.listarContas().forEach(c -> {
            System.out.println("Nº " + c.getNumero() + " - " + c.getTitular().getNome() + " - Saldo: " + c.getSaldo() + (c instanceof ContaCorrente cc ? " - Limite: " + cc.getLimiteChequeEspecial() : ""));
        });
    }
}
