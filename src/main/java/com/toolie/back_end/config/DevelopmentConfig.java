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
                                    "https://img.lojadomecanico.com.br/IMAGENS/46/448/186803/1628086010251.JPG",
                                    "https://www.dutramaquinas.com.br/shared/img/produto/alta/482451_furadeira_parafusadeira_3_8_a_bateria_12v_com_kit_de_brocas_e_bits_bpf_12k3.webp"
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
                            Collections.singletonList("https://www.silvaselantes.com.br/media/catalog/product/cache/1/image/600x600/9df78eab33525d08d6e5fb8d27136e95/d/w/dwe560b2_1.png"),
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
                            Collections.singletonList("https://img.lojadomecanico.com.br/IMAGENS/2/468/1812/Alicate-Universal-Profissional-de-8-Pol-gedore-8280-200-iox1.JPG"),
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
                            Collections.singletonList("https://elastobor.vtexassets.com/arquivos/ids/261736/PARAFUSADEIRA-FURADEIRA-VONDER-3-8-PFV012-BATERIA-BIVOLT.jpg?v=638200154901200000"),
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
                            Collections.singletonList("https://img.lojadomecanico.com.br/IMAGENS/46/448/130800/1569260399408.JPG"),
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
                            Collections.singletonList("https://img.lojadomecanico.com.br/IMAGENS/2/524/126087/Marreta-Oitavada-1000g-com-Cabo-de-Madei-sparta-10905551.JPG"),
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
                            Collections.singletonList("https://images.tcdn.com.br/img/img_prod/625353/escada_telescopica_aluminio_0_80x3_20m_10_degraus_005121_16871_1_3b99f9a9779da721e6e6b078c7261761.jpg"),
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
                            Collections.singletonList("https://www.apmaquinaseferramentas.com.br/public-images/product/zoom-3662-AA92C.jpg"),
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
                            Collections.singletonList("https://img.lojadomecanico.com.br/IMAGENS/31/274/20506/Serrote-Profissional-26-pol-tramontina-432420261.JPG"),
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
                            Collections.singletonList("https://img.lojadomecanico.com.br/IMAGENS/21/159/116480/1572956418719.JPG"),
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
                            Collections.singletonList("https://cdn.awsli.com.br/2500x2500/1921/1921752/produto/130889289/ae49b3c53e.jpg"),
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
                            Collections.singletonList("https://img.lojadomecanico.com.br/IMAGENS/24/244/19725/Pistola-de-Pintura-HVLP-600-ml-e-Bico-14-steula-bc75-141.JPG"),
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
                            Collections.singletonList("https://img.lojadomecanico.com.br/IMAGENS/31/284/147416/1596113228463.JPG"),
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
                            Collections.singletonList("https://static.superproatacado.com.br/storage/1000/prumo-pedreiro-700gr-economico-895024-kala-1582750242.jpg"),
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
                            Collections.singletonList("https://img.lojadomecanico.com.br/IMAGENS/31/270/94065/Esquadro-em-Aco-10-Pol-com-Cabo-PVC-momfort-6160101.JPG"),
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
                            Collections.singletonList("https://static.felap.com.br/public/felap/imagens/produtos/martelo-demolidor-encaixe-sextavado-30kg-220v-d25980-b2-dewalt-66267214cc0fe.jpg"),
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
                            Collections.singletonList("https://momfort.com.br/wp-content/uploads/2022/06/PONTEIRA-DE-ACO-STANDARD-3.jpg"),
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
                            Collections.singletonList("https://img.lojadomecanico.com.br/IMAGENS/2/248/92398/Chave-para-Tubo-tipo-Stillson-de-12-Pol--nove-54-3513000312-1.JPG"),
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
                            Collections.singletonList("https://cdn.awsli.com.br/600x700/2587/2587765/produto/212675222fc85cb5d9f.jpg"),
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
                            Collections.singletonList("https://d8vlg9z1oftyc.cloudfront.net/arsystem/image/product/d207b5df387c835d7b3da0c3060b707120201211112624/850/cortador-de-tubos-com-escareador_7694.jpg"),
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
                            Collections.singletonList("https://down-br.img.susercontent.com/file/5e575999c6f5b23c4345d451a31d5ec8"),
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
                            Collections.singletonList("https://img.lojadomecanico.com.br/IMAGENS/48/634/257437488/chaveparap-idany-6002184147.JPG"),
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
                            Collections.singletonList("https://toolsbr.fbitsstatic.net/img/p/stanley-alicate-descascador-de-fios-175881/363433.jpg?w=1080&h=1080&v=202411111340&qs=ignore"),
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
                            Collections.singletonList("https://images.tcdn.com.br/img/img_prod/632258/multimetro_digital_dt830b_1537_1_d7a76410b3c774a93002757bd7625292_20240109102843.jpg"),
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
                            Collections.singletonList("https://img.lojadomecanico.com.br/IMAGENS/19/189/121594/Detector-de-Tensao-DTV1210-12V-a-1000V-A-vonder-38701210001.JPG"),
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
                            Collections.singletonList("https://img.lojadomecanico.com.br/IMAGENS/21/221/18641/1653585349626.JPG"),
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
                            Collections.singletonList("https://images-americanas.b2w.io/produtos/7339775083/imagens/teste-de-tomada-vonder-identificador-de-tensao-para-tomadas-110-ou-220-volts/7339775083_1_xlarge.jpg"),
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
                            Collections.singletonList("https://www.agrotama.com.br/upload/novo_produtos/aparadorpodadordecercavivaeletrico600wcorte16mm_102055805.jpg"),
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
                            Collections.singletonList("https://www.sigmatools.com.br/site/wp-content/uploads/2021/09/1000x1000.png"),
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
                            Collections.singletonList("https://cdn.awsli.com.br/2500x2500/2374/2374926/produto/210441668/tesoura-cerca-viva-e-grama-78330125-tramontina-6-awmpgu.jpg"),
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
                            Collections.singletonList("https://chatuba.vtexassets.com/arquivos/ids/168796/Carrinho-de-Mao-Extraforte-Cinza-65L-Tramontina.jpg?v=637420122594670000"),
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
                            Collections.singletonList("https://images.tcdn.com.br/img/img_prod/940470/foice_com_cabo_de_madeira_30cm_2637_1_dcadf6d3d1ad7c41e001fde6d2d7af90.jpg"),
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
