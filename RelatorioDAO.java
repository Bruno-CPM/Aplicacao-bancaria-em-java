import java.util.*;
import java.sql.*;

public class RelatorioDAO {
    public void inserir(Relatorio relatorio) {
        String sql = "INSERT INTO relatorio (idFuncionario, tipo, dataGeracao, caminhoArquivo) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, relatorio.getIdFuncionario());
            stmt.setString(2, relatorio.getTipo());
            stmt.setTimestamp(3, relatorio.getDataGeracao());
            stmt.setString(4, relatorio.getCaminhoArquivo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Relatorio relatorio) {
        String sql = "UPDATE relatorio SET idFuncionario=?, tipo=?, dataGeracao=?, caminhoArquivo=? WHERE id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, relatorio.getIdFuncionario());
            stmt.setString(2, relatorio.getTipo());
            stmt.setTimestamp(3, relatorio.getDataGeracao());
            stmt.setString(4, relatorio.getCaminhoArquivo());
            stmt.setInt(5, relatorio.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM relatorio WHERE id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Relatorio buscarPorId(int id) {
        String sql = "SELECT * FROM relatorio WHERE id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Relatorio relatorio = new Relatorio();
                relatorio.setId(rs.getInt("id"));
                relatorio.setIdFuncionario(rs.getInt("idFuncionario"));
                relatorio.setTipo(rs.getString("tipo"));
                relatorio.setDataGeracao(rs.getTimestamp("dataGeracao"));
                relatorio.setCaminhoArquivo(rs.getString("caminhoArquivo"));
                return relatorio;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Relatorio> listarTodos() {
        List<Relatorio> lista = new ArrayList<>();
        String sql = "SELECT * FROM relatorio";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Relatorio relatorio = new Relatorio();
                relatorio.setId(rs.getInt("id"));
                relatorio.setIdFuncionario(rs.getInt("idFuncionario"));
                relatorio.setTipo(rs.getString("tipo"));
                relatorio.setDataGeracao(rs.getTimestamp("dataGeracao"));
                relatorio.setCaminhoArquivo(rs.getString("caminhoArquivo"));
                lista.add(relatorio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
} 