import java.util.*;
import java.sql.*;

public class UsuarioDAO {
    public void inserir(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome, email, senhaMd5, cpf, telefone, dataNascimento, tipo, ativo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenhaMd5());
            stmt.setString(4, usuario.getCpf());
            stmt.setString(5, usuario.getTelefone());
            stmt.setDate(6, usuario.getDataNascimento());
            stmt.setString(7, usuario.getTipo());
            stmt.setBoolean(8, usuario.isAtivo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Usuario usuario) {
        String sql = "UPDATE usuario SET nome=?, email=?, senhaMd5=?, cpf=?, telefone=?, dataNascimento=?, tipo=?, ativo=? WHERE id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenhaMd5());
            stmt.setString(4, usuario.getCpf());
            stmt.setString(5, usuario.getTelefone());
            stmt.setDate(6, usuario.getDataNascimento());
            stmt.setString(7, usuario.getTipo());
            stmt.setBoolean(8, usuario.isAtivo());
            stmt.setInt(9, usuario.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM usuario WHERE id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Usuario buscarPorId(int id) {
        String sql = "SELECT * FROM usuario WHERE id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenhaMd5(rs.getString("senhaMd5"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setTelefone(rs.getString("telefone"));
                usuario.setDataNascimento(rs.getDate("dataNascimento"));
                usuario.setTipo(rs.getString("tipo"));
                usuario.setAtivo(rs.getBoolean("ativo"));
                return usuario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Usuario> listarTodos() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenhaMd5(rs.getString("senhaMd5"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setTelefone(rs.getString("telefone"));
                usuario.setDataNascimento(rs.getDate("dataNascimento"));
                usuario.setTipo(rs.getString("tipo"));
                usuario.setAtivo(rs.getBoolean("ativo"));
                lista.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
} 