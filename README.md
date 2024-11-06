# toolie-back-end

Back end do aplicativo Toolie para a disciplina Resolução de Problemas II

## Rodar a API

Para rodar a API na sua máquina local acesse o diretório em
que ela está instalada e insira o seguinte comando no terminal:

> ./mvnw spring-boot:run

_Exemplo:_

![Comando no terminal para rodar a API](comando_rodar_api.png)

## Esquema do banco de dados

Para a criação dessa API nos baseamos no seguinte esquema:
![Esquema do banco de dados](esquema-bd.jpeg)

## Endpoints

### _Entidade Usuario_

- GET /usuarios

Retorna todos os usuarios do banco de dados, incluindo todos os seus campos.

- GET /usuarios/{id}

Retorna os dados do usuário com o id especificado.

### _Entidade Ferramenta_

- GET /ferramentas

Retorna todas as ferramentas do banco de dados, incluindo todos os seus campos.

- GET /ferramentas/{id}

Retorna os dados da ferramenta com o id especificado.
Caso o id especificado não exista no banco de dados o código 404 é retornado.

- GET /ferramentas?q=exemplo+de+query

Mecanismo de pesquisa simples de ferramentas.
Retorna todas as ferramentas do banco de dados que correspondem com a query.

- GET /ferramentas?proprietarioId={id}

Retorna todas as ferramentas pertencentes ao usuário com o id especificado.
