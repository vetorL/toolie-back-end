# Toolie - Back-End API

Este repositório contém o back-end da API Toolie, desenvolvida para a disciplina **Resolução de Problemas II**. A API é construída usando **Spring Boot** e fornece funcionalidades para o aluguel de ferramentas entre usuários.

A API está atualmente disponível na seguinte URL: https://toolie-api.onrender.com. 

> **Nota**: Caso a API tenha ficado inativa por muito tempo é capaz que ela não responda por alguns minutos. 
> É só esperar que ela eventualmente volta a funcionar.

## Rodando a API

Para rodar a API localmente, siga os passos abaixo:

1. Navegue até o diretório onde a API está instalada.
2. No terminal, execute o comando para rodar a API com o Maven Wrapper:

```bash
./mvnw spring-boot:run
```

> **Nota**: O comando acima executa o projeto Spring Boot e inicia o servidor localmente.

## Esquema do Banco de Dados

A API foi estruturada de acordo com o seguinte esquema de banco de dados:

![Esquema do banco de dados](esquema-bd.jpeg)

## Endpoints Disponíveis

### **Entidade Usuário**

#### **GET /api/v1/usuarios**

Retorna uma lista de todos os usuários cadastrados.

```json
[
  {
    "id": 1,
    "nome": "João",
    "email": "joao@gmail.com",
    "telefone": "1234567890",
    "endereco": "Endereço Proprietario",
    "fotoDocumentoURL": "fotoURL",
    "statusConta": "VERIFICACAO_PENDENTE",
    "flagLocador": false,
    "flagLocatario": false
  },
  {
    "id": 2,
    "nome": "Yoel",
    "email": "yoel@gmail.com",
    "telefone": "12345678490",
    "endereco": "Endereço Proprietario4",
    "fotoDocumentoURL": "fotoURL4",
    "statusConta": "VERIFICACAO_PENDENTE",
    "flagLocador": false,
    "flagLocatario": false
  }
]
```

#### **GET /api/v1/usuarios/{id}**

Retorna os detalhes de um usuário específico pelo ID.

```json
{
  "id": 5,
  "nome": "Jorge",
  "email": "jorge@gmail.com",
  "telefone": "1234567890",
  "endereco": "Endereço 1",
  "fotoDocumentoURL": "url1",
  "statusConta": "VERIFICACAO_PENDENTE",
  "flagLocador": false,
  "flagLocatario": false
}
```

---

### **Entidade Ferramenta**

#### **GET /ferramentas**

Retorna uma lista de todas as ferramentas cadastradas.

```json
[
  {
    "id": 10,
    "proprietario_id": 1,
    "tipo_ferramenta": "Furadeira",
    "nome": "Furadeira Bosch",
    "estado_de_uso": "Excelente",
    "descricao": "Furadeira com potência de 700W, ideal para uso doméstico.",
    "preco_aluguel": 10.0,
    "disponibilidade": true,
    "localizacao": "Rua ABC, 123",
    "fotos": ["imagem1.jpg", "imagem2.jpg"],
    "condicoes_uso": "Usar com cuidado",
    "opcoes_entrega": "Retirada no local"
  },
  {
    "id": 11,
    "proprietario_id": 2,
    "tipo_ferramenta": "Esmerilhadeira",
    "nome": "Esmerilhadeira Dewalt",
    "estado_de_uso": "Bom",
    "descricao": "Ideal para cortes em metais e outros materiais rígidos.",
    "preco_aluguel": 15.0,
    "disponibilidade": true,
    "localizacao": "Rua XYZ, 456",
    "fotos": ["imagem3.jpg", "imagem4.jpg"],
    "condicoes_uso": "Usar com óculos de proteção",
    "opcoes_entrega": "Envio por motoboy"
  }
]
```

#### **GET /ferramentas/{id}**

Retorna os detalhes de uma ferramenta específica pelo ID.

```json
{
  "id": 10,
  "proprietario_id": 1,
  "tipo_ferramenta": "Furadeira",
  "nome": "Furadeira Bosch",
  "estado_de_uso": "Excelente",
  "descricao": "Furadeira com potência de 700W, ideal para uso doméstico.",
  "preco_aluguel": 10.0,
  "disponibilidade": true,
  "localizacao": "Rua ABC, 123",
  "fotos": ["imagem1.jpg", "imagem2.jpg"],
  "condicoes_uso": "Usar com cuidado",
  "opcoes_entrega": "Retirada no local"
}
```

#### **GET /ferramentas?q={query}**

Busca ferramentas com base em uma query fornecida.

```json
[
  {
    "id": 10,
    "proprietario_id": 1,
    "tipo_ferramenta": "Furadeira",
    "nome": "Furadeira Bosch",
    "estado_de_uso": "Excelente",
    "descricao": "Furadeira com potência de 700W, ideal para uso doméstico.",
    "preco_aluguel": 10.0,
    "disponibilidade": true,
    "localizacao": "Rua ABC, 123",
    "fotos": ["imagem1.jpg", "imagem2.jpg"],
    "condicoes_uso": "Usar com cuidado",
    "opcoes_entrega": "Retirada no local"
  }
]
```

#### **GET /ferramentas?proprietarioId={id}**

Retorna todas as ferramentas de um determinado proprietário.

```json
[
  {
    "id": 10,
    "proprietario_id": 1,
    "tipo_ferramenta": "Furadeira",
    "nome": "Furadeira Bosch",
    "estado_de_uso": "Excelente",
    "descricao": "Furadeira com potência de 700W, ideal para uso doméstico.",
    "preco_aluguel": 10.0,
    "disponibilidade": true,
    "localizacao": "Rua ABC, 123",
    "fotos": ["imagem1.jpg", "imagem2.jpg"],
    "condicoes_uso": "Usar com cuidado",
    "opcoes_entrega": "Retirada no local"
  },
  {
    "id": 12,
    "proprietario_id": 1,
    "tipo_ferramenta": "Serra Elétrica",
    "nome": "Serra Dewalt",
    "estado_de_uso": "Bom",
    "descricao": "Ideal para cortes precisos em madeira.",
    "preco_aluguel": 20.0,
    "disponibilidade": true,
    "localizacao": "Rua ABC, 123",
    "fotos": ["imagem3.jpg"],
    "condicoes_uso": "Usar com cuidado",
    "opcoes_entrega": "Retirada no local"
  }
]
```

#### **GET /api/v1/usuarios/{id}/ferramentas-alugadas**

Retorna uma lista de ferramentas que o usuário (locatário) já alugou.

```json
[
  {
    "id": 1,
    "tipoFerramenta": "Martelo",
    "dataInicio": "2024-11-10",
    "dataFim": "2024-11-11",
    "statusAluguel": "devolvida",
    "fotoFerramentaURL": "https://picsum.photos/200"
  },
  {
    "id": 2,
    "tipoFerramenta": "Marreta",
    "dataInicio": "2023-11-01",
    "dataFim": "2023-11-03",
    "statusAluguel": "em uso",
    "fotoFerramentaURL": "https://picsum.photos/200"
  }
]
```

---

### **Endpoints em Construção**

#### **GET /usuarios/{id}/ferramentas-em-aluguel**

Retorna as ferramentas que um locador tem alugadas.

```json
[
  {
    "id": 3,
    "nome": "Esmerilhadeira",
    "locatario": "Maria Silva",
    "data_inicio": "2023-11-05",
    "data_fim": "2023-11-10",
    "status": "em uso"
  }
]
```

#### **POST /alugueis**

Realiza o aluguel de uma ferramenta.

```json
{
  "id": 1,
  "ferramenta_id": 10,
  "locador_id": 3,
  "locatario_id": 8,
  "data_inicio": "2023-11-15",
  "data_fim": "2023-11-20",
  "status": "pendente",
  "metodo_entrega": "retirada no local"
}
```

#### **POST /alugueis/{id}/devolver**

Realiza a devolução de uma ferramenta.

```json
{
  "id": 1,
  "ferramenta_id": 10,
  "locador_id": 3,
  "locatario_id": 8,
  "status": "devolvido",
  "data_devolucao": "2023-11-20"
}
```

#### **POST /avaliacoes**

Avalia um usuário após o aluguel de uma ferramenta.

```json
[
  {
    "id": 1,
    "avaliador_id": 8,
    "avaliado_id": 3,
    "ferramenta_id": 10,
    "nota": 5,
    "comentario": "Ótimo locador, super recomendo!",
    "data_avaliacao": "2023-11-20"
  }
]
```

---

## Tecnologias Utilizadas

- **Spring Boot**: Framework para desenvolvimento de aplicações Java.