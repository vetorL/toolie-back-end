package com.toolie.back_end.config;

import com.toolie.back_end.aluguel.Aluguel;
import com.toolie.back_end.aluguel.AluguelRepository;
import com.toolie.back_end.ferramenta.Ferramenta;
import com.toolie.back_end.ferramenta.FerramentaRepository;
import com.toolie.back_end.usuario.Usuario;
import com.toolie.back_end.usuario.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Configuration
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner dataLoader(UsuarioRepository usuarioRepository, FerramentaRepository ferramentaRepository, AluguelRepository aluguelRepository) {
        return args -> {

            // Usuários
            Usuario proprietario1 = new Usuario("João", "joao@gmail.com", "1234567890", "Endereço Proprietario", "fotoURL");
            Usuario proprietario2 = new Usuario("Yoel", "yoel@gmail.com", "12345678490", "Endereço Proprietario4", "fotoURL4");

            Usuario locador = new Usuario("Locador", "locador@example.com", "12345", "Rua 1", "http://example.com/documento.jpg");
            Usuario locatario = new Usuario("Locatario", "locatario@example.com", "67890", "Rua 2", "http://example.com/documento2.jpg");

            List<Usuario> usuarios = Arrays.asList(
                    proprietario1,
                    proprietario2,
                    locador,
                    locatario,
                    new Usuario("Jorge", "jorge@gmail.com", "1234567890", "Endereço 1", "url1"),
                    new Usuario("Maria", "maria@gmail.com", "2345678901", "Endereço 2", "url2"),
                    new Usuario("Pedro", "pedro@gmail.com", "3456789012", "Endereço 3", "url3"),
                    new Usuario("Ana", "ana@gmail.com", "4567890123", "Endereço 4", "url4"),
                    new Usuario("Luis", "luis@gmail.com", "5678901234", "Endereço 5", "url5"),
                    new Usuario("Carlos", "carlos@gmail.com", "6789012345", "Endereço 6", "url6"),
                    new Usuario("Lucia", "lucia@gmail.com", "7890123456", "Endereço 7", "url7"),
                    new Usuario("Fernando", "fernando@gmail.com", "8901234567", "Endereço 8", "url8"),
                    new Usuario("Elena", "elena@gmail.com", "9012345678", "Endereço 9", "url9"),
                    new Usuario("Diego", "diego@gmail.com", "1234567891", "Endereço 10", "url10"),
                    new Usuario("Paula", "paula@gmail.com", "1122334455", "Endereço 11", "url11"),
                    new Usuario("Gabriel", "gabriel@gmail.com", "5566778899", "Endereço 12", "url12")
            );

            usuarioRepository.saveAll(usuarios);

            // Ferramentas
            Ferramenta ferramenta1 = new Ferramenta(
                    locador,
                    "Martelo",
                    "Usado",
                    "Martelo de aço de 500g",
                    20,
                    "Centro",
                    Collections.singletonList("https://images.tcdn.com.br/img/img_prod/750752/martelo_unha_27mm_cabo_de_madeira_envernizado_2107_1_8c3672c25b63305e1a7699b459c68e41.jpg"),
                    "Condições adequadas",
                    "Retirada no local",
                    "Construção",
                    5
            );

            List<Ferramenta> ferramentas = Arrays.asList(
                    ferramenta1,
                    new Ferramenta(
                            proprietario2,
                            "Chave de fenda",
                            "Novo",
                            "Chave de fenda Philips",
                            50,
                            "Zona Sul",
                            Collections.singletonList("https://fken.vtexassets.com/arquivos/ids/287724-800-800?v=638556334742100000&width=800&height=800&aspect=true"),
                            "Condições novas",
                            "Entrega disponível",
                            "Construção",
                            4.5
                    ),
                    new Ferramenta(
                            proprietario1,
                            "Furadeira",
                            "Usada",
                            "Furadeira elétrica de alta potência",
                            100,
                            "Zona Norte",
                            Arrays.asList(
                                    "https://cdn.leroymerlin.com.br/products/furadeira_eletrica_black_decker_550w_cd121k50br_162201_246d_600.jpg",
                                    "https://cdn.leroymerlin.com.br/products/furadeira_eletrica2.jpg"
                            ),
                            "Funciona perfeitamente, apenas marcas de uso.",
                            "Envio por motoboy disponível",
                            "Construção",
                            4.8
                    ),
                    new Ferramenta(
                            locador,
                            "Serra Circular",
                            "Quase nova",
                            "Serra circular com lâmina de 7 polegadas",
                            120,
                            "Centro",
                            Collections.singletonList("https://cdn.ferramentaskennedy.com.br/media/catalog/product/cache/23/image/700x700/9df78eab33525d08d6e5fb8d27136e95/s/e/serra_circular.jpg"),
                            "Pouco usada, em excelente estado.",
                            "Retirada no local",
                            "Marcenaria",
                            4.9
                    ),
                    new Ferramenta(
                            proprietario1,
                            "Alicate Universal",
                            "Novo",
                            "Alicate universal de alta resistência",
                            25,
                            "Centro",
                            Collections.singletonList("https://example.com/alicate.jpg"),
                            "Ideal para trabalhos elétricos.",
                            "Retirada no local",
                            "Ferramentas Manuais",
                            4.7
                    ),
                    new Ferramenta(
                            locador,
                            "Parafusadeira",
                            "Usada",
                            "Parafusadeira elétrica com bateria recarregável",
                            90,
                            "Zona Oeste",
                            Collections.singletonList("https://example.com/parafusadeira.jpg"),
                            "Bateria em ótimo estado, acompanha carregador.",
                            "Entrega disponível",
                            "Construção",
                            4.6
                    ),
                    new Ferramenta(
                            proprietario2,
                            "Nível de Bolha",
                            "Novo",
                            "Nível de bolha de 1 metro",
                            15,
                            "Zona Norte",
                            Collections.singletonList("https://example.com/nivel.jpg"),
                            "Perfeito para medições precisas.",
                            "Retirada no local",
                            "Construção",
                            5.0
                    ),
                    new Ferramenta(
                            locatario,
                            "Marreta",
                            "Usada",
                            "Marreta de 1kg para demolições",
                            30,
                            "Zona Sul",
                            Collections.singletonList("https://example.com/marreta.jpg"),
                            "Ideal para trabalhos pesados.",
                            "Retirada no local",
                            "Construção",
                            4.2
                    ),
                    new Ferramenta(
                            locador,
                            "Escada Telescópica",
                            "Quase nova",
                            "Escada telescópica de alumínio de 3 metros",
                            150,
                            "Centro",
                            Collections.singletonList("https://example.com/escada.jpg"),
                            "Fácil transporte e armazenamento.",
                            "Entrega disponível",
                            "Construção",
                            4.8
                    ),
                    new Ferramenta(
                            proprietario1,
                            "Chave Inglesa",
                            "Novo",
                            "Chave inglesa ajustável de 10 polegadas",
                            20,
                            "Zona Leste",
                            Collections.singletonList("https://example.com/chave-inglesa.jpg"),
                            "Ferramenta resistente e ajustável.",
                            "Retirada no local",
                            "Ferramentas Manuais",
                            4.9
                    ),
                    new Ferramenta(
                            proprietario2,
                            "Serrote",
                            "Usado",
                            "Serrote de poda com cabo ergonômico",
                            25,
                            "Zona Sul",
                            Collections.singletonList("https://example.com/serrote.jpg"),
                            "Ótimo para trabalhos de jardinagem.",
                            "Entrega disponível",
                            "Jardinagem",
                            4.4
                    ),
                    new Ferramenta(
                            locatario,
                            "Compressor de Ar",
                            "Usado",
                            "Compressor portátil de 50 litros",
                            200,
                            "Zona Oeste",
                            Collections.singletonList("https://example.com/compressor.jpg"),
                            "Em bom estado, ideal para oficinas.",
                            "Retirada no local",
                            "Oficina",
                            4.5
                    ),
                    new Ferramenta(
                            locador,
                            "Roçadeira",
                            "Novo",
                            "Roçadeira a gasolina de alta performance",
                            300,
                            "Centro",
                            Collections.singletonList("https://example.com/rocadeira.jpg"),
                            "Excelente para limpeza de terrenos.",
                            "Entrega disponível",
                            "Jardinagem",
                            4.9
                    ),
                    new Ferramenta(
                            proprietario1,
                            "Pistola de Pintura",
                            "Quase nova",
                            "Pistola de pintura HVLP com ajuste fino",
                            80,
                            "Zona Norte",
                            Collections.singletonList("https://example.com/pistola-pintura.jpg"),
                            "Ótima para pintura profissional.",
                            "Retirada no local",
                            "Pintura",
                            4.8
                    ),
                    new Ferramenta(
                            locador,
                            "Betoneira",
                            "Usada",
                            "Betoneira elétrica de 120 litros, ideal para misturar concreto.",
                            150.0,
                            "Centro",
                            Collections.singletonList("https://example.com/betoneira.jpg"),
                            "Utilizar em local plano e seco.",
                            "Retirada no local",
                            "Construção",
                            4.5
                    ),
                    new Ferramenta(
                            proprietario2,
                            "Prumo de Pedreiro",
                            "Novo",
                            "Prumo de pedreiro com cordão de 2 metros.",
                            10.0,
                            "Zona Norte",
                            Collections.singletonList("https://example.com/prumo.jpg"),
                            "Manter o cordão livre de sujeira.",
                            "Entrega disponível",
                            "Construção",
                            4.8
                    ),
                    new Ferramenta(
                            proprietario1,
                            "Esquadro de Aço",
                            "Novo",
                            "Esquadro de aço inoxidável para medições precisas.",
                            25.0,
                            "Zona Sul",
                            Collections.singletonList("https://example.com/esquadro.jpg"),
                            "Limpar após o uso.",
                            "Retirada no local",
                            "Construção",
                            5.0
                    ),
                    new Ferramenta(
                            locador,
                            "Martelo Demolidor",
                            "Usado",
                            "Martelo demolidor elétrico de 10kg.",
                            200.0,
                            "Centro",
                            Collections.singletonList("https://example.com/martelo-demolidor.jpg"),
                            "Apenas para uso em superfícies rígidas.",
                            "Retirada no local",
                            "Construção",
                            4.7
                    ),
                    new Ferramenta(
                            proprietario1,
                            "Ponteira para Demolição",
                            "Novo",
                            "Ponteira compatível com martelos demolidores.",
                            15.0,
                            "Zona Oeste",
                            Collections.singletonList("https://example.com/ponteira.jpg"),
                            "Usar com martelo demolidor apropriado.",
                            "Entrega disponível",
                            "Construção",
                            4.9
                    ),
                    new Ferramenta(
                            locador,
                            "Chave Stilson",
                            "Usada",
                            "Chave de cano ajustável de 24 polegadas.",
                            40.0,
                            "Zona Leste",
                            Collections.singletonList("https://example.com/chave-stilson.jpg"),
                            "Lubrificar após o uso.",
                            "Retirada no local",
                            "Encanamento",
                            4.6
                    ),
                    new Ferramenta(
                            proprietario2,
                            "Válvula de Teste de Pressão",
                            "Novo",
                            "Válvula para teste de pressão em encanamentos.",
                            30.0,
                            "Centro",
                            Collections.singletonList("https://example.com/valvula.jpg"),
                            "Seguir as instruções de uso fornecidas.",
                            "Entrega disponível",
                            "Encanamento",
                            4.8
                    ),
                    new Ferramenta(
                            proprietario1,
                            "Cortador de Tubos",
                            "Quase novo",
                            "Cortador de tubos metálicos e PVC.",
                            35.0,
                            "Zona Norte",
                            Collections.singletonList("https://example.com/cortador-tubos.jpg"),
                            "Guardar em local seco.",
                            "Retirada no local",
                            "Encanamento",
                            4.7
                    ),
                    new Ferramenta(
                            locatario,
                            "Desentupidor de Cano",
                            "Novo",
                            "Ferramenta elétrica para desentupimento de canos.",
                            100.0,
                            "Zona Sul",
                            Collections.singletonList("https://example.com/desentupidor.jpg"),
                            "Limpar a ferramenta após o uso.",
                            "Entrega disponível",
                            "Encanamento",
                            4.9
                    ),
                    new Ferramenta(
                            proprietario1,
                            "Chave para Porca de Registro",
                            "Novo",
                            "Chave para ajustar registros de água.",
                            15.0,
                            "Zona Oeste",
                            Collections.singletonList("https://example.com/chave-porca.jpg"),
                            "Manter em local seco.",
                            "Retirada no local",
                            "Encanamento",
                            5.0
                    ),
                    new Ferramenta(
                            proprietario2,
                            "Alicate Descascador de Fios",
                            "Novo",
                            "Alicate para descascar fios de até 6mm.",
                            25.0,
                            "Zona Norte",
                            Collections.singletonList("https://example.com/alicate-descascador.jpg"),
                            "Evitar uso em fios energizados.",
                            "Entrega disponível",
                            "Eletricista",
                            4.9
                    ),
                    new Ferramenta(
                            locador,
                            "Multímetro Digital",
                            "Usado",
                            "Multímetro digital para medição de tensão e corrente.",
                            50.0,
                            "Centro",
                            Collections.singletonList("https://example.com/multimetro.jpg"),
                            "Não usar sob chuva.",
                            "Retirada no local",
                            "Eletricista",
                            4.7
                    ),
                    new Ferramenta(
                            proprietario1,
                            "Detector de Tensão",
                            "Novo",
                            "Detector de tensão sem contato.",
                            20.0,
                            "Zona Sul",
                            Collections.singletonList("https://example.com/detector.jpg"),
                            "Testar antes do uso.",
                            "Entrega disponível",
                            "Eletricista",
                            4.8
                    ),
                    new Ferramenta(
                            locatario,
                            "Furadeira de Impacto",
                            "Usada",
                            "Furadeira de impacto com broca para concreto.",
                            90.0,
                            "Zona Leste",
                            Collections.singletonList("https://example.com/furadeira-impacto.jpg"),
                            "Manter longe de superfícies úmidas.",
                            "Retirada no local",
                            "Eletricista",
                            4.6
                    ),
                    new Ferramenta(
                            proprietario2,
                            "Teste de Tomadas",
                            "Novo",
                            "Dispositivo para testar tomadas elétricas.",
                            15.0,
                            "Zona Oeste",
                            Collections.singletonList("https://example.com/teste-tomadas.jpg"),
                            "Manter em local seco.",
                            "Entrega disponível",
                            "Eletricista",
                            4.9
                    ),
                    new Ferramenta(
                            locador,
                            "Aparador de Cerca Viva",
                            "Usado",
                            "Aparador elétrico de 500W.",
                            120.0,
                            "Centro",
                            Collections.singletonList("https://example.com/aparador.jpg"),
                            "Usar em condições secas.",
                            "Retirada no local",
                            "Jardinagem",
                            4.7
                    ),
                    new Ferramenta(
                            proprietario1,
                            "Pulverizador Manual",
                            "Novo",
                            "Pulverizador manual de 2 litros.",
                            20.0,
                            "Zona Norte",
                            Collections.singletonList("https://example.com/pulverizador.jpg"),
                            "Limpar após o uso.",
                            "Entrega disponível",
                            "Jardinagem",
                            4.8
                    ),
                    new Ferramenta(
                            proprietario2,
                            "Tesoura de Poda",
                            "Quase nova",
                            "Tesoura de poda ergonômica.",
                            30.0,
                            "Zona Sul",
                            Collections.singletonList("https://example.com/tesoura-poda.jpg"),
                            "Lubrificar após o uso.",
                            "Retirada no local",
                            "Jardinagem",
                            4.9
                    ),
                    new Ferramenta(
                            locatario,
                            "Carrinho de Mão",
                            "Usado",
                            "Carrinho de mão com capacidade para 100kg.",
                            50.0,
                            "Zona Oeste",
                            Collections.singletonList("https://example.com/carrinho.jpg"),
                            "Evitar sobrepeso.",
                            "Entrega disponível",
                            "Jardinagem",
                            4.6
                    ),
                    new Ferramenta(
                            locador,
                            "Foice",
                            "Novo",
                            "Foice de aço com cabo de madeira.",
                            35.0,
                            "Zona Leste",
                            Collections.singletonList("https://example.com/foice.jpg"),
                            "Manter o fio afiado.",
                            "Retirada no local",
                            "Jardinagem",
                            4.8
                    )
                    );



            ferramentaRepository.saveAll(ferramentas);

            // Aluguéis
            Date dataInicio = new Date();
            Date dataFim = new Date(dataInicio.getTime() + 86400000L);

            List<Aluguel> alugueis = Arrays.asList(
                    new Aluguel(locador, locatario, ferramenta1, dataInicio, dataFim, "ativo", "pago", "20.0", "retirada no local"),
                    new Aluguel(locador, locatario, ferramenta1, dataInicio, dataFim, "ativo", "pago", "30.0", "envio por motoboy"),
                    new Aluguel(locatario, proprietario1, ferramenta1, dataInicio, dataFim, "concluído", "pago", "25.0", "retirada no local"),
                    new Aluguel(locador, locatario, ferramenta1, dataInicio, dataFim, "cancelado", "não pago", "15.0", "não aplicável"),
                    new Aluguel(proprietario1, locador, ferramenta1, dataInicio, dataFim, "ativo", "pago", "50.0", "envio por motoboy")
            );

            aluguelRepository.saveAll(alugueis);
        };
    }

}
