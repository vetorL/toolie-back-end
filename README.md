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

## Endpoints Funcionando

### _Entidade Usuario_

GET /usuarios
[
{
"id": 1, (int)
"nome": "Ana Souza", (String)
"email": "ana@example.com", (String)
"telefone": "123456789", (String)
"endereco": "Rua ABC, 123", (String)
"status_conta": "verificado", (String)
"avaliacao": 4.8 (BigDecimal)
},
{
"id": 2,
"nome": "Carlos Dias",
"email": "carlos@example.com",
"telefone": "987654321",
"endereco": "Rua XYZ, 456",
"status_conta": "pendente",
"avaliacao": 4.5
}
]

GET /usuarios/{id}
{
"id": 1, (int)
"nome": "Ana Souza", (String)
"email": "ana@example.com", (String)
"telefone": "123456789", (String)
"endereco": "Rua ABC, 123", (String)
"status_conta": "verificado", (String)
"avaliacao": 4.8 (BigDecimal)
}

### _Entidade Ferramenta_

GET /ferramentas
[
{
"id": 10, (int)
"proprietario_id": 1, (int)
"tipo_ferramenta": "Furadeira", (String)
"nome": "Furadeira Bosch", (String)
"estado_de_uso": "Excelente", (String)
"descricao": "Furadeira com potência de 700W, ideal para uso doméstico.", (String)
"preco_aluguel": 10.0, (BigDecimal)
"disponibilidade": true, (Boolean)
"localizacao": "Rua ABC, 123", (String)
"fotos": ["imagem1.jpg", "imagem2.jpg"], (String)
"condicoes_uso": "Usar com cuidado", (String)
"opcoes_entrega": "Retirada no local" (String)
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

GET /ferramentas/{id}
{
"id": 10, (int)
"proprietario_id": 1, (int)
"tipo_ferramenta": "Furadeira", (String)
"nome": "Furadeira Bosch", (String)
"estado_de_uso": "Excelente",
"descricao": "Furadeira com potência de 700W, ideal para uso doméstico.",
"preco_aluguel": 10.0, (BigDecimal )
"disponibilidade": true, (Boolean)
"localizacao": "Rua ABC, 123",
"fotos": ["imagem1.jpg", "imagem2.jpg"], (String)
"condicoes_uso": "Usar com cuidado", (String)
"opcoes_entrega": "Retirada no local" (String)
}

GET /ferramentas?q=exemplo+de+query
[
{
"id": 10, (int)
"proprietario_id": 1, (int)
"tipo_ferramenta": "Furadeira", (String)
"nome": "Furadeira Bosch", (String)
"estado_de_uso": "Excelente", (String)
"descricao": "Furadeira com potência de 700W, ideal para uso doméstico.", (String)
"preco_aluguel": 10.0, (BigDecimal)
"disponibilidade": true, (Boolean)
"localizacao": "Rua ABC, 123", (String)
"fotos": ["imagem1.jpg", "imagem2.jpg"],
"condicoes_uso": "Usar com cuidado", (String)
"opcoes_entrega": "Retirada no local" (String)
}
]

GET /ferramentas?proprietarioId={id}
[
{
"id": 10, (int)
"proprietario_id": 1, (int)
"tipo_ferramenta": "Furadeira", (String)
"nome": "Furadeira Bosch", (String)
"estado_de_uso": "Excelente", (String)
"descricao": "Furadeira com potência de 700W, ideal para uso doméstico.", (String)
"preco_aluguel": 10.0, (BigDecimal)
"disponibilidade": true, (Boolean)
"localizacao": "Rua ABC, 123", (String)
"fotos": ["imagem1.jpg", "imagem2.jpg"], (String)
"condicoes_uso": "Usar com cuidado", (String)
"opcoes_entrega": "Retirada no local" (String)
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

## Endpoints Em Construção

GET /usuarios/{id}/ferramentas-alugadas -> Ferramentas que o usuário (locatário) já alugou.
[
{
"id": 1, (int)
"nome": "Furadeira", (String)
"data_inicio": "2023-10-10", (LocalDate)
"data_fim": "2023-10-12", (LocalDate)
"status": "devolvida" (String)
},
{
"id": 2,
"nome": "Marreta",
"data_inicio": "2023-11-01",
"data_fim": "2023-11-03",
"status": "em uso"
}
]

GET /usuarios/{id}/ferramentas-em-aluguel ->Ferramentas de um locador que estão alugadas.
[
{
"id": 3, (int)
"nome": "Esmerilhadeira", (String)
"locatario": "Maria Silva", (String)
"data_inicio": "2023-11-05", (LocalDate)
"data_fim": "2023-11-10", (LocalDate)
"status": "em uso" (String)
}
]

POST /alugueis -> Aluguel de uma ferramenta
{
"id": 1, (int)
"ferramenta_id": 10, (int)
"locador_id": 3, (int)
"locatario_id": 8, (int)
"data_inicio": "2023-11-15", (LocalDate)
"data_fim": "2023-11-20", (LocalDate)
"status": "pendente", (String)
"metodo_entrega": "retirada no local" (String)
}

POST /alugueis/{id}/devolver -> Devolver ferramenta/ferramentas
{
"id": 1,
"ferramenta_id": 10,
"locador_id": 3,
"locatario_id": 8,
"status": "devolvido",
"data_devolucao": "2023-11-20"
}

POST /avaliacoes -> Avalia usuário
[
{
"id": 1, (int)
"avaliador_id": 8, (int)
"avaliado_id": 3, (int)
"ferramenta_id": 10, (int)
"nota": 5, (BigDecimal)
"comentario": "Ótimo locador, super recomendo!", (String)
"data_avaliacao": "2023-11-20" (LocalDate)
}
]
