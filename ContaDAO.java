import java.util.*;
import java.sql.*;

public class ContaDAO {
    public void inserir(Conta conta) {
        String sql = "INSERT INTO conta (numero, idCliente, idAgencia, saldo, tipo, status, dataAbertura, dataEncerramento) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, conta.getNumero());
            stmt.setInt(2, conta.getIdCliente());
            stmt.setInt(3, conta.getIdAgencia());
            stmt.setDouble(4, conta.getSaldo());
            stmt.setString(5, conta.getTipo());
            stmt.setString(6, conta.getStatus());
            stmt.setTimestamp(7, conta.getDataAbertura());
            stmt.setTimestamp(8, conta.getDataEncerramento());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Conta conta) {
        String sql = "UPDATE conta SET numero=?, idCliente=?, idAgencia=?, saldo=?, tipo=?, status=?, dataAbertura=?, dataEncerramento=? WHERE id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, conta.getNumero());
            stmt.setInt(2, conta.getIdCliente());
            stmt.setInt(3, conta.getIdAgencia());
            stmt.setDouble(4, conta.getSaldo());
            stmt.setString(5, conta.getTipo());
            stmt.setString(6, conta.getStatus());
            stmt.setTimestamp(7, conta.getDataAbertura());
            stmt.setTimestamp(8, conta.getDataEncerramento());
            stmt.setInt(9, conta.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM conta WHERE id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Conta buscarPorId(int id) {
        String sql = "SELECT * FROM conta WHERE id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Como Conta é abstrata, normalmente você teria subclasses. Aqui, instanciando Conta como se fosse concreta.
                Conta conta = new Conta() {};
                conta.setId(rs.getInt("id"));
                conta.setNumero(rs.getString("numero"));
                conta.setIdCliente(rs.getInt("idCliente"));
                conta.setIdAgencia(rs.getInt("idAgencia"));
                conta.setSaldo(rs.getDouble("saldo"));
                conta.setTipo(rs.getString("tipo"));
                conta.setStatus(rs.getString("status"));
                conta.setDataAbertura(rs.getTimestamp("dataAbertura"));
                conta.setDataEncerramento(rs.getTimestamp("dataEncerramento"));
                return conta;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Conta> listarTodos() {
        List<Conta> lista = new ArrayList<>();
        String sql = "SELECT * FROM conta";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Conta conta = new Conta() {};
                conta.setId(rs.getInt("id"));
                conta.setNumero(rs.getString("numero"));
                conta.setIdCliente(rs.getInt("idCliente"));
                conta.setIdAgencia(rs.getInt("idAgencia"));
                conta.setSaldo(rs.getDouble("saldo"));
                conta.setTipo(rs.getString("tipo"));
                conta.setStatus(rs.getString("status"));
                conta.setDataAbertura(rs.getTimestamp("dataAbertura"));
                conta.setDataEncerramento(rs.getTimestamp("dataEncerramento"));
                lista.add(conta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
} 