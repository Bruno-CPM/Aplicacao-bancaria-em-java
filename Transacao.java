public class Transacao {
    private int id;
    private Integer idContaOrigem;
    private Integer idContaDestino;
    private String tipo; // "DEPOSITO", "SAQUE", "TRANSFERENCIA"
    private double valor;
    private java.sql.Timestamp dataHora;
    private String descricao;
    // Getters e setters
} 