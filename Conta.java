public abstract class Conta {
    protected int id;
    protected String numero;
    protected int idCliente;
    protected int idAgencia;
    protected double saldo;
    protected String tipo; // "POUPANCA", "CORRENTE", "INVESTIMENTO"
    protected String status;
    protected java.sql.Timestamp dataAbertura;
    protected java.sql.Timestamp dataEncerramento;
    // Getters e setters
} 