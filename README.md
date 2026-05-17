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


As migrations do Flyway criam as tabelas `addresses` e `employees` automaticamente ao iniciar a aplicação.

## Modelo da aplicação

Um funcionário possui:

- `id`
- `name`
- `email`
- `phone`
- `role`
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

Departamentos aceitos:

- `ESTAGIÁRIO`
- `TRAINEE`
- `ANALISTA_JUNIOR`
- `ANALISTA_PLENO`
- `ANALISTA_SENIOR`

## Endpoints

Base path:
http://localhost:8080/employees


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
- `department`
- `address.street`
- `address.neighborhood`
- `address.postalCode`
- `address.city`
- `address.state`

O campo `email` precisa ter formato válido. O campo `postalCode` aceita os formatos `00000000` ou `00000-000`.

Exemplo de requisição:

```json
{
  "name": "Ana Souza",
  "email": "ana.souza@email.com",
  "phone": "11999999999",
  "role": "Desenvolvedora Backend",
  "department": "ANALISTA_JUNIOR",
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
  "department": "ANALISTA_JUNIOR",
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
  "department": "ANALISTA_JUNIOR",
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
  "department": "ANALISTA_PLENO",
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

Este endpoint não remove o registro do banco. Ele altera o campo `active` do funcionário para `false`.

## Observações

- `email` e `phone` são únicos no banco de dados.
- A relação entre funcionário e endereço é `OneToOne`.
- Ao criar ou atualizar um funcionário, o endereço é salvo junto por cascade.
- A listagem atual usa `repository.findAll(pageable)`, portanto também retorna funcionários desativados.
