# kotlin-spring-boot-microsservicos-spring-cloud

🔨 🔧... sistema composto por vários microsserviços que comunicam entre si de forma transparente, escalável e com balanceamento de carga, com autenticação e autorização, usando OAuth e tokens JWT. 

## Setup do Projeto - microsserviços

* Linguagem de programação: Kotlin
* Tecnologia: Spring Boot
* Gerenciador de dependência: Gradlle

## Estrutura:

![alt text](https://github.com/wagnersistemalima/microsservicos-java-springboot-springcloud/blob/main/images/imagemMicrosservico.png)

## Visão do projeto:

* Montar a estrutura do projeto, a comunicação entre os microsserviços, a configuração automatica, escala automatica e balanceamento de carga. 

* desenvolver um sistema com alguns microserviços.

* Serviço de trabalhadores, que estará conectado com um banco de dados, onde iremos manter um cadastro de cada trabalhador.

* Microsserviço de folha de pagamento

* Microsserviços de usuarios, que estará anexado a um banco de dados, com permissao e perfil de acesso para cada usuario.

* Serviço de autorização e autenticação, com protocolo OAuth e tokens JWT, padrão usado por industrias bem populares.

* Os microserviços irão se registrar em um servidor Discovery Eureka.

* API gateway para rotear os microsserviços

* Servidor de configuração.

## Implementação utilizando as ferramentas do ecossistema Spring com Java

* Banco de dados H2: banco de dados em memoria, para testes

* Ferramenta Postman: Para testar as requisições e criar um ambiente de produção

* JPA : biblioteca padrão de persistência de dados no java, baseado no mapeamento objeto relacional

* API Gateway Zuul :  lida com todas as solicitações e executa o roteamento dinâmico de aplicativos de microsserviço. Funciona como porta de entrada para todos os pedidos. Também é conhecido como Edge Server. Zuul é construído para permitir roteamento dinâmico, monitoramento, resiliência e segurança. A partir de um ponto especifico o gateway será responsavel por encontrar a melhor instancia de cada microsserviços para atender a requisição, inclusive a mesma autenticação que for feita no gateway vai servir para acessar todos microsserviços.

* Oauth : é um padrão aberto para autorização, comumente utilizado para permitir que os usuários da Internet possam fazer logon em sites de terceiros usando suas contas do Google, Facebook, Microsoft, Twitter, etc.—mas, sem expor suas senhas.

* JWT (JSON WEB TOKENS): É um método RCT 7519 padrão da indústria para realizar autenticação entre duas partes por meio de um token assinado que autentica uma requisição web. Esse token é um código em Base64 que armazena objetos JSON com os dados que permitem a autenticação da requisição.

* Feign: Uma maneira elegante de criar clientes HTTP em Java

* Netflix Ribbon: É uma biblioteca em nuvem que fornece o balanceamento de carga do lado do cliente . Ele interage automaticamente com o Netflix Service Discovery (Eureka) porque é um membro da família Netflix. A faixa de opções fornece principalmente algoritmos de balanceamento de carga do lado do cliente. É um balanceador de carga do lado do cliente que fornece controle sobre o comportamento do cliente HTTP e TCP . O ponto importante é que quando usamos Feign , a fita também se aplica.

* Discovery Server Eureka: Em uma arquitetura típica de microsserviço, temos muitos pequenos aplicativos implantados separadamente e eles geralmente precisam se comunicar uns com os outros. Especificamente, quando dizemos serviço ao cliente , queremos dizer um serviço que precisa fazer chamadas REST para algum outro serviço final.

* Hystrix: é uma biblioteca de tolerância a falhas e latência projetada para isolar pontos de acesso em sistemas remotos, serviços e bibliotecas de terceiros, parar falhas em cascata e habilitar a resiliência em sistemas distribuídos complexos onde a falha é inevitável.

# Status do Projeto = Está em construção 🔧

# Fase 1: Comunicação simples, Feign, Ribbon. Concluida!👋

* 1.1 Criar projeto trabalhador
* 1.2 Implementar projeto trabalhador
* 1.3 Criar projeto folha-pagamento
* 1.4 Implementar projeto folha-pagamento - cliente Ribbon, fazendo chamada para o serviço de trabalhador onde pode haver varias instancias do serviço trabalhador com balanciamento de cargas.
* 1.6 Feign
* 1.7 Ribbon load balancing

## Fase 2: Eureka server port default 8761

* Criar projeto trabalhador-eureka-server
* Servidor onde os microsserviços, vão se registrar, utilizando portas aleatorias.
* O servidor Eureka vai tratar de registrar todas as instancias 
* Quando for preciso chamar um microserviço, a chamada vai ser apenas pelo nome, sem a necessidade de colocar localização de porta. Vai ser automatico, com balanciamento de carga
* Configurar projeto: Incluir a dependencia

```
implementation("org.glassfish.jaxb:jaxb-runtime:2.3.3-b01")

```
![alt text](https://github.com/wagnersistemalima/kotlin-spring-boot-microsservicos-spring-cloud/blob/main/images/dashboardEurekaServer2.png)

## Random port para serviço worker
* nome da aplicação , id da instancia, e um valor aleatorio

```
server.port=${PORT:0}

eureka.instance.instance-id=${spring.application.name}:${spring.application.instance_id:${random.value}}
```

## API Gateway Zuul

* porta padrão 8765
