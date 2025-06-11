import java.util.*;
import java.sql.*;

public class TransacaoDAO {
    public void inserir(Transacao transacao) {
        String sql = "INSERT INTO transacao (idContaOrigem, idContaDestino, tipo, valor, dataHora, descricao) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            if (transacao.getIdContaOrigem() != null) stmt.setInt(1, transacao.getIdContaOrigem()); else stmt.setNull(1, Types.INTEGER);
            if (transacao.getIdContaDestino() != null) stmt.setInt(2, transacao.getIdContaDestino()); else stmt.setNull(2, Types.INTEGER);
            stmt.setString(3, transacao.getTipo());
            stmt.setDouble(4, transacao.getValor());
            stmt.setTimestamp(5, transacao.getDataHora());
            stmt.setString(6, transacao.getDescricao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Transacao transacao) {
        String sql = "UPDATE transacao SET idContaOrigem=?, idContaDestino=?, tipo=?, valor=?, dataHora=?, descricao=? WHERE id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            if (transacao.getIdContaOrigem() != null) stmt.setInt(1, transacao.getIdContaOrigem()); else stmt.setNull(1, Types.INTEGER);
            if (transacao.getIdContaDestino() != null) stmt.setInt(2, transacao.getIdContaDestino()); else stmt.setNull(2, Types.INTEGER);
            stmt.setString(3, transacao.getTipo());
            stmt.setDouble(4, transacao.getValor());
            stmt.setTimestamp(5, transacao.getDataHora());
            stmt.setString(6, transacao.getDescricao());
            stmt.setInt(7, transacao.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM transacao WHERE id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Transacao buscarPorId(int id) {
        String sql = "SELECT * FROM transacao WHERE id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Transacao transacao = new Transacao();
                transacao.setId(rs.getInt("id"));
                int idOrigem = rs.getInt("idContaOrigem");
                int idDestino = rs.getInt("idContaDestino");
                transacao.setIdContaOrigem(rs.wasNull() ? null : idOrigem);
                transacao.setIdContaDestino(rs.wasNull() ? null : idDestino);
                transacao.setTipo(rs.getString("tipo"));
                transacao.setValor(rs.getDouble("valor"));
                transacao.setDataHora(rs.getTimestamp("dataHora"));
                transacao.setDescricao(rs.getString("descricao"));
                return transacao;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Transacao> listarTodos() {
        List<Transacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM transacao";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Transacao transacao = new Transacao();
                transacao.setId(rs.getInt("id"));
                int idOrigem = rs.getInt("idContaOrigem");
                int idDestino = rs.getInt("idContaDestino");
                transacao.setIdContaOrigem(rs.wasNull() ? null : idOrigem);
                transacao.setIdContaDestino(rs.wasNull() ? null : idDestino);
                transacao.setTipo(rs.getString("tipo"));
                transacao.setValor(rs.getDouble("valor"));
                transacao.setDataHora(rs.getTimestamp("dataHora"));
                transacao.setDescricao(rs.getString("descricao"));
                lista.add(transacao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
} 