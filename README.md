# Employee Registration

API REST para cadastro e gerenciamento de funcionários. A aplicação permite criar, listar, consultar, atualizar e desativar funcionários, mantendo também os dados de endereço vinculados a cada cadastro.

## Tecnologias

- Java 21
- Spring Boot
- Spring Data JPA
- Bean Validation
- Flyway
- MySQL
- Lombok
- Maven

## Como executar

1. Crie um banco MySQL chamado `employee-registration`.
2. Confira as credenciais em `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost/employee-registration
spring.datasource.username=
spring.datasource.password=
```

As migrations do Flyway criam e atualizam as tabelas `addresses` e `employees` automaticamente ao iniciar a aplicação.

## Modelo da aplicação

Um funcionário possui:

- `id`
- `name`
- `email`
- `phone`
- `role`
- `salary`
- `admissionDate`
- `deactivationDate`
- `department`
- `address`
- `active`

O endereço possui:

- `id`
- `street`
- `neighborhood`
- `postalCode`
- `number`
- `complement`
- `city`
- `state`
  
A relação entre funcionário e endereço é `OneToOne`: cada funcionário possui um único endereço vinculado, e cada endereço pertence a um único funcionário.

Departamentos aceitos:

- `ADMINISTRAÇÃO`
- `COMERCIAL`
- `COMPRAS`
- `CONTABILIDADE`
- `DEPARTAMENTO PESSOAL`
- `FINANCEIRO`
- `JURÍDICO`
- `LOGÍSTICA`
- `MARKETING`
- `OPERAÇÕES`
- `PLANEJAMENTO ESTRATÉGICO`
- `RECURSOS HUMANOS`
- `RELAÇÕES PÚBLICAS`
- `TECNOLOGIA DA INFORMAÇÃO`
- `VENDAS`

Também são aceitos os nomes internos do enum, sem acentos e com `_`, como `RECURSOS_HUMANOS` e `TECNOLOGIA_DA_INFORMACAO`.

## Migrations

- `V1__create-table-addresses.sql`: cria a tabela de endereços.
- `V2__add-timestamps-to-addresses.sql`: adiciona timestamps em endereços.
- `V3__create-table-employees.sql`: cria a tabela de funcionários.
- `V4__update_department_enum_values.sql`: normaliza valores antigos do enum.
- `V5__update-department-enum-values.sql`: adiciona salário, data de admissão, data de desativação e atualiza os valores válidos de departamento.

## Endpoints

Base path:

```text
http://localhost:8080/employees
```

| Método | Endpoint | Descrição                             |
| --- | --- |---------------------------------------|
| `POST` | `/employees` | Cria um funcionário                   |
| `GET` | `/employees` | Lista funcionários com paginação      |
| `GET` | `/employees/{id}` | Busca um funcionário pelo ID          |
| `PUT` | `/employees` | Atualiza dados de um funcionário      |
| `DELETE` | `/employees/{id}` | Desativa um funcionário (soft-delete) |

## Criar funcionário

```http
POST /employees
```

Campos obrigatórios:

- `name`
- `email`
- `phone`
- `role`
- `salary`
- `admissionDate`
- `department`
- `address.street`
- `address.neighborhood`
- `address.postalCode`
- `address.city`
- `address.state`

O campo `email` precisa ter formato válido. O campo `salary` precisa ser positivo. Os campos de data (`admissionDate` e `deactivationDate`) usam o formato `dd-MM-yyyy`. O campo `postalCode` aceita os formatos `00000000` ou `00000-000`.

Exemplo de requisição:

```json
{
  "name": "Ana Souza",
  "email": "ana.souza@email.com",
  "phone": "11999999999",
  "role": "Desenvolvedora Backend",
  "salary": 7500.00,
  "admissionDate": "17-05-2026",
  "department": "TECNOLOGIA DA INFORMAÇÃO",
  "address": {
    "street": "Rua das Flores",
    "neighborhood": "Centro",
    "postalCode": "01001-000",
    "number": "100",
    "complement": "Apto 12",
    "city": "São Paulo",
    "state": "SP"
  }
}
```

Resposta: `201 Created`

```json
{
  "id": 1,
  "name": "Ana Souza",
  "email": "ana.souza@email.com",
  "phone": "11999999999",
  "role": "Desenvolvedora Backend",
  "salary": 7500.00,
  "admissionDate": "17-05-2026",
  "deactivationDate": null,
  "department": "TECNOLOGIA DA INFORMAÇÃO",
  "address": {
    "id": 1,
    "street": "Rua das Flores",
    "neighborhood": "Centro",
    "postalCode": "01001-000",
    "number": "100",
    "complement": "Apto 12",
    "city": "São Paulo",
    "state": "SP"
  },
  "active": true
}
```

## Listar funcionários

```http
GET /employees
```

Aceita parâmetros de paginação do Spring Data:

```text
GET /employees?page=0&size=10&sort=name,asc
```

Resposta: `200 OK`

Retorna uma página com funcionários no formato `Page`.

## Buscar funcionário por ID

```http
GET /employees/{id}
```

Exemplo:

```http
GET /employees/1
```

Resposta: `200 OK`

```json
{
  "id": 1,
  "name": "Ana Souza",
  "email": "ana.souza@email.com",
  "phone": "11999999999",
  "role": "Desenvolvedora Backend",
  "salary": 7500.00,
  "admissionDate": "17-05-2026",
  "deactivationDate": null,
  "department": "TECNOLOGIA DA INFORMAÇÃO",
  "address": {
    "id": 1,
    "street": "Rua das Flores",
    "neighborhood": "Centro",
    "postalCode": "01001-000",
    "number": "100",
    "complement": "Apto 12",
    "city": "São Paulo",
    "state": "SP"
  },
  "active": true
}
```

Caso o ID não exista, a aplicação lança uma exceção com a mensagem:

```text
Employee with id {id} not found
```

## Atualizar funcionário

```http
PUT /employees
```

O campo `id` é obrigatório. Os demais campos são opcionais; apenas os campos enviados são atualizados.

Exemplo de requisição:

```json
{
  "id": 1,
  "name": "Ana Souza Silva",
  "phone": "11888888888",
  "role": "Desenvolvedora Java",
  "salary": 9200.00,
  "department": "PLANEJAMENTO ESTRATÉGICO",
  "address": {
    "street": "Avenida Paulista",
    "number": "1500",
    "city": "São Paulo"
  }
}
```

Resposta: `200 OK`

Retorna o funcionário atualizado.

## Desativar funcionário

```http
DELETE /employees/{id}
```

Exemplo:

```http
DELETE /employees/1
```

Resposta: `204 No Content`

Este endpoint não remove o registro do banco. Ele altera o campo `active` para `false` e preenche `deactivationDate` com a data atual.

## Observações

- `email` e `phone` são únicos no banco de dados.
- A relação entre funcionário e endereço é `OneToOne`.
- Ao criar ou atualizar um funcionário, o endereço é salvo junto por cascade.
- A listagem atual usa `repository.findAll(pageable)`, portanto também retorna funcionários desativados.
