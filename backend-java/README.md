# Biblioteca Backend - Spring Boot

Este projeto é o backend da aplicação de biblioteca, feito em Java com Spring Boot.

## Como rodar localmente

1. Certifique-se de ter Java 17+ instalado.
2. Execute `./mvnw spring-boot:run` para rodar a API.

## Endpoints
- `GET /books` - Lista todos os livros
- `POST /books` - Cria um novo livro
- `GET /books/{id}` - Detalha um livro
- `PUT /books/{id}` - Atualiza um livro
- `DELETE /books/{id}` - Remove um livro

## Testes
Execute `./mvnw test` para rodar os testes.

## Docker
Veja o arquivo `Dockerfile` para buildar a imagem do backend.
