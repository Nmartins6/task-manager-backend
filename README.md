# Desafio Técnico - Sistema de Gerenciamento de Tarefas Abstrato

Esta é uma aplicação web para gerenciamento de listas e itens, permitindo aos usuários criar e gerenciar listas de tarefas, adicionar, editar, remover itens e destacar itens importantes. A aplicação foi desenvolvida com Spring Boot e utiliza PostgreSQL para persistência de dados.

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- Maven
- PostgreSQL

## Requisitos

- JDK
- Maven
- PostgreSQL

## Configuração do Banco de Dados

- Certifique-se de que o PostgreSQL esteja instalado e em execução em sua máquina.
- Crie um banco de dados chamado `taskmanager`.
- Configure as credenciais do banco de dados no arquivo `application.properties` localizado em `src/main/resources/application.properties`

## Clonando o Repositório

Para clonar o repositório, execute o seguinte comando:

```
git clone git@github.com:Nmartins6/task-manager-backend.git
```

## Executando o Projeto
Navegue até o diretório do projeto e execute os seguintes comandos:

```
cd task-manager-backend

mvn spring-boot:run
```

> [!NOTE]
> A aplicação será iniciada em http://localhost:8080

## Repositório do Frontend
O repositório do frontend pode ser encontrado aqui: [Frontend do desafio](https://github.com/Nmartins6/task-manager-frontend)