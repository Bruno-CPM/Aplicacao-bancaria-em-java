import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class PacienteValidator {
    
    public static void validarPaciente(Paciente paciente) throws IllegalArgumentException {
        if (paciente == null) {
            throw new IllegalArgumentException("Paciente não pode ser nulo");
        }
        
        validarNome(paciente.getNome());
        validarCpf(paciente.getCpf());
        validarDataNascimento(paciente.getDataNascimento());
        validarTelefone(paciente.getTelefone());
        validarEmail(paciente.getEmail());
        validarEndereco(paciente.getEndereco());
        validarCidade(paciente.getCidade());
        validarEstado(paciente.getEstado());
        validarCep(paciente.getCep());
    }
    
    public static void validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        if (nome.trim().length() < 3) {
            throw new IllegalArgumentException("Nome deve ter pelo menos 3 caracteres");
        }
        if (nome.trim().length() > 100) {
            throw new IllegalArgumentException("Nome deve ter no máximo 100 caracteres");
        }
        if (!nome.matches("^[a-zA-ZÀ-ÿ\\s]+$")) {
            throw new IllegalArgumentException("Nome deve conter apenas letras e espaços");
        }
    }
    
    public static void validarCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF é obrigatório");
        }
        
        // Remove caracteres não numéricos
        String cpfLimpo = cpf.replaceAll("[^0-9]", "");
        
        if (cpfLimpo.length() != 11) {
            throw new IllegalArgumentException("CPF deve ter 11 dígitos");
        }
        
        // Verifica se todos os dígitos são iguais
        if (cpfLimpo.matches("(\\d)\\1{10}")) {
            throw new IllegalArgumentException("CPF inválido");
        }
        
        // Validação dos dígitos verificadores
        if (!validarDigitosVerificadores(cpfLimpo)) {
            throw new IllegalArgumentException("CPF inválido");
        }
    }
    
    private static boolean validarDigitosVerificadores(String cpf) {
        try {
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += Integer.parseInt(String.valueOf(cpf.charAt(i))) * (10 - i);
            }
            int resto = soma % 11;
            int digito1 = resto < 2 ? 0 : 11 - resto;
            
            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += Integer.parseInt(String.valueOf(cpf.charAt(i))) * (11 - i);
            }
            resto = soma % 11;
            int digito2 = resto < 2 ? 0 : 11 - resto;
            
            return Integer.parseInt(String.valueOf(cpf.charAt(9))) == digito1 &&
                   Integer.parseInt(String.valueOf(cpf.charAt(10))) == digito2;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static void validarDataNascimento(LocalDate dataNascimento) {
        if (dataNascimento == null) {
            throw new IllegalArgumentException("Data de nascimento é obrigatória");
        }
        
        LocalDate hoje = LocalDate.now();
        if (dataNascimento.isAfter(hoje)) {
            throw new IllegalArgumentException("Data de nascimento não pode ser no futuro");
        }
        
        if (dataNascimento.isBefore(hoje.minusYears(150))) {
            throw new IllegalArgumentException("Data de nascimento inválida");
        }
    }
    
    public static void validarTelefone(String telefone) {
        if (telefone == null || telefone.trim().isEmpty()) {
            throw new IllegalArgumentException("Telefone é obrigatório");
        }
        
        String telefoneLimpo = telefone.replaceAll("[^0-9]", "");
        if (telefoneLimpo.length() < 10 || telefoneLimpo.length() > 11) {
            throw new IllegalArgumentException("Telefone deve ter 10 ou 11 dígitos");
        }
    }
    
    public static void validarEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email é obrigatório");
        }
        
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!Pattern.matches(emailRegex, email.trim())) {
            throw new IllegalArgumentException("Email inválido");
        }
    }
    
    public static void validarEndereco(String endereco) {
        if (endereco == null || endereco.trim().isEmpty()) {
            throw new IllegalArgumentException("Endereço é obrigatório");
        }
        if (endereco.trim().length() < 5) {
            throw new IllegalArgumentException("Endereço deve ter pelo menos 5 caracteres");
        }
    }
    
    public static void validarCidade(String cidade) {
        if (cidade == null || cidade.trim().isEmpty()) {
            throw new IllegalArgumentException("Cidade é obrigatória");
        }
        if (cidade.trim().length() < 2) {
            throw new IllegalArgumentException("Cidade deve ter pelo menos 2 caracteres");
        }
    }
    
    public static void validarEstado(String estado) {
        if (estado == null || estado.trim().isEmpty()) {
            throw new IllegalArgumentException("Estado é obrigatório");
        }
        if (estado.trim().length() != 2) {
            throw new IllegalArgumentException("Estado deve ter 2 caracteres");
        }
        if (!estado.trim().matches("^[A-Z]{2}$")) {
            throw new IllegalArgumentException("Estado deve ser abreviado com 2 letras maiúsculas");
        }
    }
    
    public static void validarCep(String cep) {
        if (cep == null || cep.trim().isEmpty()) {
            throw new IllegalArgumentException("CEP é obrigatório");
        }
        
        String cepLimpo = cep.replaceAll("[^0-9]", "");
        if (cepLimpo.length() != 8) {
            throw new IllegalArgumentException("CEP deve ter 8 dígitos");
        }
    }
    
    public static LocalDate parseData(String dataStr) throws IllegalArgumentException {
        try {
            return LocalDate.parse(dataStr);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Data inválida. Use o formato yyyy-MM-dd");
        }
    }
} 