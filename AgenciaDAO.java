import java.util.*;
import java.sql.*;

public class AgenciaDAO {
    public void inserir(Agencia agencia) {
        String sql = "INSERT INTO agencia (nome, endereco) VALUES (?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, agencia.getNome());
            stmt.setString(2, agencia.getEndereco());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Agencia agencia) {
        String sql = "UPDATE agencia SET nome=?, endereco=? WHERE id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, agencia.getNome());
            stmt.setString(2, agencia.getEndereco());
            stmt.setInt(3, agencia.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM agencia WHERE id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Agencia buscarPorId(int id) {
        String sql = "SELECT * FROM agencia WHERE id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Agencia agencia = new Agencia();
                agencia.setId(rs.getInt("id"));
                agencia.setNome(rs.getString("nome"));
                agencia.setEndereco(rs.getString("endereco"));
                return agencia;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Agencia> listarTodos() {
        List<Agencia> lista = new ArrayList<>();
        String sql = "SELECT * FROM agencia";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Agencia agencia = new Agencia();
                agencia.setId(rs.getInt("id"));
                agencia.setNome(rs.getString("nome"));
                agencia.setEndereco(rs.getString("endereco"));
                lista.add(agencia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
} 