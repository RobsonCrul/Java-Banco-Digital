🏦 Banco Digital em Java

Este projeto foi desenvolvido como um exercício prático para treinar lógica de programação orientada a objetos (OO) utilizando Java 21.
A aplicação simula um sistema bancário simples, permitindo criar contas, realizar depósitos, saques e transferências entre contas.

🚀 Tecnologias utilizadas

- Java 21

- Paradigma Orientado a Objetos (POO)

- Collections Framework (List, Map, Optional)

🧠 Conceito do projeto

-  O sistema é composto pelas seguintes classes principais:

    - Banco → Gerencia uma lista de contas.

    - Cliente → Representa o titular da conta.

     - Conta (abstrata) → Classe base com os comportamentos comuns (sacar, depositar, transferir).

    - ContaCorrente e ContaPoupanca → Especializações da classe Conta.

    - Main → Classe principal que executa o fluxo de teste das operações bancárias.

⚙️ Principais recursos do código

🔹 1. Uso de List

As contas são armazenadas em uma List<Conta>, que facilita percorrer e exibir todas as contas registradas no banco.

private List<Conta> contas = new ArrayList<>();


Isso permite manipular várias contas dinamicamente — adicionando, removendo ou iterando sobre elas.

🔹 2. Uso de Map

O Map<String, Conta> pode ser utilizado para buscar contas rapidamente com base em um identificador único (como o número da conta ou CPF do cliente).

    private Map<String, Conta> mapaContas = new HashMap<>();


Assim, o acesso se torna O(1), ou seja, instantâneo, sem precisar percorrer toda a lista.

🔹 3. Uso de Optional

O Optional evita erros de NullPointerException quando buscamos contas que podem não existir.

    Optional<Conta> contaDestino = Optional.ofNullable(mapaContas.get(numeroConta));

Essa abordagem torna o código mais seguro e limpo.

🔹 4. Uso de Collections

O projeto também faz uso da API Collections, permitindo ordenar, buscar ou filtrar contas conforme necessário.

Exemplo de ordenação:

    Collections.sort(contas, Comparator.comparing(Conta::getSaldo).reversed());


Isso ordena as contas pelo saldo de forma decrescente.

🔹 5. Uso de Exceptions

O tratamento de exceções garante que operações inválidas sejam tratadas corretamente, mantendo a integridade dos dados.

    public void sacar(double valor) {
        if (valor <= 0) {
        throw new IllegalArgumentException("Valor de saque inválido.");
        }
        if (saldo < valor) {
        throw new RuntimeException("Saldo insuficiente.");
        }
        saldo -= valor;
    }


Dessa forma, erros são interceptados e tratados de maneira controlada, sem travar o programa.

💡 Conceitos aplicados

- Encapsulamento e herança (POO)

- Uso de abstração com classes e métodos abstratos

- Tratamento seguro de dados com Optional e Exceptions

- Manipulação eficiente de dados com Collections

🧩 Exemplo de execução

=== Banco Digital ===

    Conta criada para Robson
    
    Depósito de R$ 1000,00
    
    Transferência de R$ 250,00 para João
    
    Saldo final: R$ 750,00
