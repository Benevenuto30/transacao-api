# Transação API

## Descrição

Esta é uma API REST desenvolvida em Spring Boot para gerenciar transações e fornecer estatísticas relacionadas. A aplicação permite criar transações, deletar todas as transações e consultar estatísticas baseadas em um intervalo de tempo.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.4.1**
- **Spring Web**
- **Spring Boot Actuator**
- **SpringDoc OpenAPI** (para documentação Swagger)
- **Lombok**
- **Gradle** (para gerenciamento de dependências e build)

## Pré-requisitos

- JDK 17 instalado
- Gradle (ou usar o wrapper incluído no projeto)

## Como Executar

1. Clone o repositório ou navegue até o diretório do projeto.

2. Execute o comando para buildar e rodar a aplicação:
   ```
   ./gradlew bootRun
   ```
   Ou no Windows:
   ```
   gradlew.bat bootRun
   ```

3. A aplicação estará rodando na porta padrão 8080.

## Endpoints da API

### Transações

- **POST /transacao**
  - Descrição: Cria uma nova transação.
  - Corpo da Requisição: `TransactionRequestDTO` (JSON)
    ```json
    {
      "valor": 100.50,
      "dataHora": "2023-10-01T12:00:00Z"
    }
    ```
  - Respostas:
    - 201: Transação criada com sucesso.
    - 422: Campos não atendem os requisitos.
    - 400: Erro de requisição.
    - 500: Erro interno.

- **DELETE /transacao**
  - Descrição: Deleta todas as transações.
  - Respostas:
    - 200: Transações deletadas com sucesso.
    - 400: Erro de requisição.
    - 500: Erro interno.

### Estatísticas

- **GET /estatistica**
  - Descrição: Retorna estatísticas das transações.
  - Parâmetros de Query:
    - `intervaloBusca` (opcional, padrão: 60): Intervalo em segundos para buscar transações.
  - Resposta: `StatisticsResponseDTO` (JSON)
    ```json
    {
      "count": 10,
      "sum": 1000.0,
      "avg": 100.0,
      "min": 50.0,
      "max": 200.0
    }
    ```
  - Respostas:
    - 200: Busca realizada com sucesso.
    - 400: Erro de requisição.
    - 500: Erro interno.

## Documentação Swagger

A documentação interativa da API está disponível via Swagger UI em:
```
http://localhost:8080/swagger-ui/index.html
```

Além disso, o endpoint OpenAPI JSON está em:
```
http://localhost:8080/v3/api-docs
```

## Actuator Endpoints

A aplicação expõe endpoints do Spring Boot Actuator para monitoramento:

- **Health**: `http://localhost:8080/actuator/health`
- **Info**: `http://localhost:8080/actuator/info`
- **Metrics**: `http://localhost:8080/actuator/metrics`

Estes endpoints também estão configurados para aparecer no Swagger.

## Configuração

As configurações estão no arquivo `src/main/resources/application.yml`. Inclui configurações para actuator e springdoc.

## Testes

Para executar os testes:
```
./gradlew test
```

## Estrutura do Projeto

- `src/main/java/com/benevenuto/transacao_api/`
  - `TransacaoApiApplication.java`: Classe principal da aplicação.
  - `controller/`: Controladores REST.
  - `business/service/`: Serviços de negócio.
  - `dtos/`: Data Transfer Objects.
  - `infrastructure/exceptions/`: Exceções customizadas.

- `src/main/resources/`: Recursos da aplicação, incluindo `application.yml`.

- `src/test/`: Testes unitários e de integração.

