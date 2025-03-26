# Sistema de Gerenciamento de Professores, Alunos e Disciplinas

Este projeto é um sistema de gerenciamento de professores, alunos e disciplinas utilizando Spring Boot e PostgreSQL.

## Tecnologias Utilizadas
- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Docker & Docker Compose

## Estrutura do Projeto
O projeto contém os seguintes serviços:

- `AlunoService`: Gerencia alunos, incluindo operações CRUD e associação a disciplinas.
- `ProfessorService`: Gerencia professores, incluindo operações CRUD.
- `DisciplinaService`: Gerencia disciplinas, permitindo associação e remoção de professores e alunos.

## Configuração e Execução
### Pré-requisitos
Certifique-se de ter instalado:
- Docker
- Docker Compose

### Como Executar
1. Clone o repositório:

2. Construa e execute os containers com Docker Compose:
   ```sh
   docker-compose up --build
   ```
3. A aplicação estará disponível em `http://localhost:8080`.

## Configuração do Banco de Dados
A aplicação utiliza um banco de dados PostgreSQL, configurado no `docker-compose.yml`:

- **Host:** `db`
- **Porta:** `5432`
- **Usuário:** `postgres`
- **Senha:** `root`
- **Banco de Dados:** `professor`

## Endpoints Principais
A aplicação expõe os seguintes endpoints:

### Aluno
- `POST /alunos` - Criar um novo aluno
- `GET /alunos/{id}` - Buscar um aluno por ID
- `PUT /alunos/{id}` - Atualizar um aluno
- `DELETE /alunos/{id}` - Remover um aluno
- `POST /alunos/{alunoId}/disciplinas/{disciplinaId}` - Adicionar um aluno a uma disciplina
- `DELETE /alunos/{alunoId}/disciplinas/{disciplinaId}` - Remover um aluno de uma disciplina

### Professor
- `POST /professores` - Criar um novo professor
- `GET /professores/{id}` - Buscar um professor por ID
- `PUT /professores/{id}` - Atualizar um professor
- `DELETE /professores/{id}` - Remover um professor

### Disciplina
- `POST /disciplinas` - Criar uma nova disciplina
- `GET /disciplinas/{id}` - Buscar uma disciplina por ID
- `PUT /disciplinas/{id}` - Atualizar uma disciplina
- `DELETE /disciplinas/{id}` - Remover uma disciplina
- `POST /disciplinas/{disciplinaId}/professores/{professorId}` - Associar um professor a uma disciplina
- `DELETE /disciplinas/{disciplinaId}/professores` - Remover um professor de uma disciplina

## Considerações Finais
Este projeto serve como um exemplo de CRUD utilizando Spring Boot e PostgreSQL.
Seu código será utilizado nas aulas de Programação Orientada a Objetos e Testes de Software.
