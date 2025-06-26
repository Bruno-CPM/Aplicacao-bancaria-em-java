# Sistema de Cadastro de Pacientes

Um sistema completo de cadastro de pacientes desenvolvido em Java com interface gráfica Swing.

## Funcionalidades

- **Cadastro de Pacientes**: Adicionar novos pacientes com validação completa de dados
- **Listagem de Pacientes**: Visualizar todos os pacientes cadastrados
- **Busca por Nome**: Filtrar pacientes pelo nome
- **Visualização Detalhada**: Ver todos os dados de um paciente específico
- **Exclusão de Pacientes**: Remover pacientes do sistema
- **Persistência de Dados**: Salva automaticamente em arquivo local
- **Validação Robusta**: Proteção contra dados inválidos ou absurdos
- **Tratamento de Exceções**: Sistema não para em caso de erros

## Estrutura do Projeto

```
├── Paciente.java              # Classe modelo do paciente
├── PacienteDAO.java           # Gerenciamento de persistência
├── PacienteValidator.java     # Validação de dados
├── SistemaPacientes.java      # Tela principal do sistema
├── TelaAdicionarPaciente.java # Tela de cadastro
├── TelaListarPacientes.java   # Tela de listagem
├── pacientes.dat              # Arquivo de dados (criado automaticamente)
└── README.md                  # Este arquivo
```

## Como Executar

1. **Compilar o projeto**:
   ```bash
   javac *.java
   ```

2. **Executar o sistema**:
   ```bash
   java SistemaPacientes
   ```

## Campos do Paciente

### Campos Obrigatórios:
- **Nome**: Mínimo 3 caracteres, máximo 100, apenas letras e espaços
- **CPF**: 11 dígitos com validação de dígitos verificadores
- **Data de Nascimento**: Formato yyyy-MM-dd, não pode ser no futuro
- **Telefone**: 10 ou 11 dígitos
- **Email**: Formato válido de email
- **Endereço**: Mínimo 5 caracteres
- **Cidade**: Mínimo 2 caracteres
- **Estado**: 2 letras maiúsculas (ex: SP, RJ)
- **CEP**: 8 dígitos

### Campo Opcional:
- **Observações**: Texto livre para anotações

## Validações Implementadas

### CPF
- Verifica se tem exatamente 11 dígitos
- Valida os dígitos verificadores
- Rejeita CPFs com todos os dígitos iguais

### Data de Nascimento
- Formato obrigatório: yyyy-MM-dd
- Não pode ser no futuro
- Não pode ser há mais de 150 anos

### Email
- Formato válido de email
- Deve conter @ e domínio

### Telefone
- Apenas números
- 10 ou 11 dígitos

### Estado
- Exatamente 2 caracteres
- Apenas letras maiúsculas

## Funcionalidades da Interface

### Tela Principal
- Botão "Adicionar Paciente": Abre tela de cadastro
- Botão "Listar Pacientes": Abre tela de listagem
- Botão "Sair": Fecha o sistema

### Tela de Adicionar
- Formulário completo com todos os campos
- Validação em tempo real
- Botão "Salvar": Cadastra o paciente
- Botão "Limpar": Limpa todos os campos
- Botão "Cancelar": Fecha a tela

### Tela de Listar
- Tabela com todos os pacientes
- Campo de busca por nome
- Duplo clique para visualizar detalhes
- Botões para visualizar, editar, excluir
- Contador de pacientes no título

## Persistência de Dados

- Os dados são salvos automaticamente no arquivo `pacientes.dat`
- O arquivo é criado automaticamente na primeira execução
- Os dados são carregados automaticamente ao iniciar o sistema
- Formato de serialização Java para garantir integridade

## Tratamento de Exceções

O sistema trata as seguintes situações:
- **Dados inválidos**: Validação antes de salvar
- **Erro de arquivo**: Cria nova lista se arquivo corrompido
- **Erro de interface**: Mensagens amigáveis para o usuário
- **Erro de sistema**: Log de erros e recuperação automática

## Exemplo de Uso

1. Execute o sistema
2. Clique em "Adicionar Paciente"
3. Preencha os dados:
   - Nome: João Silva
   - CPF: 12345678901
   - Data: 1990-05-15
   - Telefone: 11987654321
   - Email: joao@email.com
   - Endereço: Rua das Flores, 123
   - Cidade: São Paulo
   - Estado: SP
   - CEP: 01234567
4. Clique em "Salvar"
5. Volte à tela principal e clique em "Listar Pacientes"
6. Veja o paciente cadastrado na lista

## Tecnologias Utilizadas

- **Java 8+**: Linguagem principal
- **Swing**: Interface gráfica
- **Serialização Java**: Persistência de dados
- **Regex**: Validação de formatos
- **Collections**: Gerenciamento de dados

## Requisitos do Sistema

- Java 8 ou superior
- Sistema operacional com suporte a Java Swing
- 100MB de espaço em disco (para dados)

## Notas de Desenvolvimento

- Código organizado em camadas (Model, DAO, View)
- Validação robusta com tratamento de exceções
- Interface responsiva e intuitiva
- Documentação completa do código
- Fácil manutenção e extensão 