# kotlin-spring-boot-microsservicos-spring-cloud

🔨 🔧... sistema composto por vários microsserviços que comunicam entre si de forma transparente, escalável e com balanceamento de carga, com autenticação e autorização, usando OAuth e tokens JWT. 

## Setup do Projeto - microsserviços Concluida!👋

* Linguagem de programação: Kotlin
* Tecnologia: Spring Boot
* Gerenciador de dependência: Gradlle

## Estrutura: Concluida!👋

![alt text](https://github.com/wagnersistemalima/microsservicos-java-springboot-springcloud/blob/main/images/imagemMicrosservico.png)

## Visão do projeto: Concluida!👋

* Montar a estrutura do projeto, a comunicação entre os microsserviços, a configuração automatica, escala automatica e balanceamento de carga. 

* desenvolver um sistema com alguns microserviços.

* Serviço de trabalhadores, que estará conectado com um banco de dados, onde iremos manter um cadastro de cada trabalhador.

* Microsserviço de folha de pagamento

* Microsserviços de usuarios, que estará anexado a um banco de dados, com permissao e perfil de acesso para cada usuario.

* Serviço de autorização e autenticação, com protocolo OAuth e tokens JWT, padrão usado por industrias bem populares.

* Os microserviços irão se registrar em um servidor Discovery Eureka.

* API gateway para rotear os microsserviços

* Servidor de configuração.

## Implementação utilizando as ferramentas do ecossistema Spring com Kotlin Concluida!👋

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

# Fase 1: Comunicação simples, Feign, Ribbon. Concluida!👋

* 1.1 Criar projeto trabalhador
* 1.2 Implementar projeto trabalhador
* 1.3 Criar projeto folha-pagamento
* 1.4 Implementar projeto folha-pagamento - cliente Ribbon, fazendo chamada para o serviço de trabalhador onde pode haver varias instancias do serviço trabalhador com balanciamento de cargas.
* 1.6 Feign
* 1.7 Ribbon load balancing

## Fase 2: Eureka server port default 8761 Concluida!👋

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

## API Gateway Zuul Concluida!👋

* porta padrão 8765
* configuração cliente eureka

# Fase 3: Configuração centralizada Concluida!👋

* Quando um microsserviço é levantado, antes de se registrar no Eureka, ele busca as configurações no repositório central de configurações.

## Fase 4: Autenticação e autorização Concluida!👋

* Criar projeto user
* Configurar projeto user com porta aleatoria e cliente eureka
* Configurar api-gateway

![alter text](https://github.com/wagnersistemalima/kotlin-spring-boot-microsservicos-spring-cloud/blob/main/images/autenticacao.png)

## Fase 5: Concluida!👋

* Criar projeto oauth-server
* Configurar projeto oauth-server
* Fazer a comunicação do servidor oauth com o serviço user, quando chegar uma autorização no serviço oauth ele vai ter que chamar o serviço user, para buscar no banco de dados o usuario caso ele exista, então no projeto oauth tera que ter as definiçoes das classes usuario e perfil.
* Login e token jwt parte 1, comunicação entre o cliente app com authorization server, o cliente app envia as credencias e o authorization server devolve um token
* Authorization-server = oauth-server
* Resource-server = api-gateway

![alter text](https://github.com/wagnersistemalima/kotlin-spring-boot-microsservicos-spring-cloud/blob/main/images/token.png)

## Login e geração do Token JWT Concluida!👋

* Source -> Override -> configure(AuthenticationManagerBuilder)

* Source -> Override -> authenticationManager()

* Basic authorization = "Basic " + Base64.encode(client-id + ":" + client-secret)

## Autorização de recursos pelo gateway Zuul

## Configuração de segurança para o servidor de configuração

## Configurando CORS
* Recurso que os navegadores tem para evitar que uma aplicação que está em um dominio diferente possa acessar recursos de uma outra aplicação de dominio diferente da sua.
* Motivos de segurança
* Aqui irei liberar o cors para outra aplicação acessar, pois se trata de um back-end de uma aplicação de microsserviços
* A api cliente ira acessar o ambiente dos microsserviços atravez do portal, api-gateway, então esta api vai liberar o cors
* Teste no navegador:

```
fetch("http://localhost:8765/worker/trabalhadores", {
  "headers": {
    "accept": "*/*",
    "accept-language": "en-US,en;q=0.9,pt-BR;q=0.8,pt;q=0.7",
    "sec-fetch-dest": "empty",
    "sec-fetch-mode": "cors",
    "sec-fetch-site": "cross-site"
  },
  "referrer": "http://localhost:3000",
  "referrerPolicy": "no-referrer-when-downgrade",
  "body": null,
  "method": "GET",
  "mode": "cors",
  "credentials": "omit"
});
```

## Ajustando o servico worker como cliente do servidor de configuração: config-server, ajustando os profiles ativos no repositorio de configuração Concluida!👋
* microsserviços de worker acessando o config-server para conectar o profile do banco de dados
* microsserviço de user, acessando o config-server para conectar o profile do banco de dados
* No arquivo bootstrap.properties configuramos somente o que for relacionado com o servidor de configuração, e também o profile do projeto.
* Configuraçoes centralizadas no repositorio do github
* Atenção: as configurações do bootstrap.properties tem prioridade sobre as do application.properties

## Criando e testando containers Docker, está em construção 🔧🔨

![alter text](https://github.com/wagnersistemalima/kotlin-spring-boot-microsservicos-spring-cloud/blob/main/images/docker.png)

## Docker

* O Docker possibilita o empacotamento de uma aplicação ou ambiente inteiro dentro de um container, e a partir desse momento o ambiente inteiro torna-se portável para qualquer outro Host que contenha o Docker instalado.

* Isso reduz drasticamente o tempo de deploy de alguma infraestrutura ou até mesmo aplicação, pois não há necessidade de ajustes de ambiente para o correto funcionamento do serviço, o ambiente é sempre o mesmo, configure-o uma vez e replique-o quantas vezes quiser.

* Outra facilidade do Docker é poder criar suas imagens (containers prontos para deploy) a partir de arquivos de definição chamados Dockerfiles

## Criando e testando containers Docker

* Criar rede docker para sistema hr

```
docker network create hr-net

```
# Testando perfil dev dos microsserviços user e worker com Postgresql no Docker

```
docker pull postgres:12-alpine

docker run -p 5432:5432 --name hr-worker-pg12 --network hr-net -e POSTGRES_PASSWORD=1234567 -e POSTGRES_DB=db_hr_worker postgres:12-alpine

docker run -p 5433:5432 --name hr-user-pg12 --network hr-net -e POSTGRES_PASSWORD=1234567 -e POSTGRES_DB=db_hr_user postgres:12-alpine
```

![alter text](https://github.com/wagnersistemalima/kotlin-spring-boot-microsservicos-spring-cloud/blob/main/images/container.png)

## Obs
* Alguns container serão fixo, terá nome e porta
* Outros container serão escalaveis, eles se registram no servidor-eureka e o api_gateway vai rotear eles automaticamente resolvendo pelo nome do container 

## Referencias
* Servidor de configuração
* Servidor Eureka
* Banco de dados

## Build do projeto hr-config-server

```
gradle clean build
```

* Criar arquivo Dockerfile na raiz do projeto

```
FROM openjdk:11
VOLUME /tmp
EXPOSE 8888
ADD ./build/libs/config-server-0.0.1-SNAPSHOT.jar hr-config-server.jar
ENTRYPOINT ["java","-jar","/hr-config-server.jar"]
```
* Criar imagem, e criar container hr-config-server

```
docker build -t hr-config-server:v1 .

docker run -p 8888:8888 --name hr-config-server --network hr-net -e GITHUB_USER=wagnersistemalima -e GITHUB_PASS= hr-config-server:v1
```

## Build do projeto hr-eureka-server

```
gradle clean build
```

* Criar arquivo Dockerfile na raiz do projeto

```
FROM openjdk:11
VOLUME /tmp
EXPOSE 8761
ADD ./build/libs/eureka-server-0.0.1-SNAPSHOT.jar hr-eureka-server.jar
ENTRYPOINT ["java","-jar","/hr-eureka-server.jar"]
```
* Criar imagem, e container hr-eureka-server

```
docker build -t hr-eureka-server:v1 .

docker run -p 8761:8761 --name hr-eureka-server --network hr-net hr-eureka-server:v1
```

## Build do projeto worker

```
gradle clean build
```

* Criar arquivo Dockerfile na raiz do projeto

```
FROM openjdk:11
VOLUME /tmp
ADD ./build/libs/microcervissos-0.0.1-SNAPSHOT.jar hr-worker.jar
ENTRYPOINT ["java","-jar","/hr-worker.jar"]
```
* Criar imagem, e container hr-worker. Comando gerado com -P maiusculo faz com que gere um container com porta aleatoria, não será preciso dar nome ao container, pois eles serão alto escalaveis

```
docker build -t hr-worker:v1 .

docker run -P --network hr-net hr-worker:v1
```

## Build do projeto user

```
gradle clean build
```

* Criar arquivo Dockerfile na raiz do projeto

```
FROM openjdk:11
VOLUME /tmp
ADD ./build/libs/user-0.0.1-SNAPSHOT.jar hr-user.jar
ENTRYPOINT ["java","-jar","/hr-user.jar"]
```

* Criar imagem, e container hr-user. Comando gerado com -P maiusculo faz com que gere um container com porta aleatoria, não será preciso dar nome ao container, pois eles serão alto escalaveis

```
docker build -t hr-user:v1 .

docker run -P --network hr-net hr-user:v1
```

## Build do projeto payroll

```
gradle clean build
```

* Criar arquivo Dockerfile na raiz do projeto

```
FROM openjdk:11
VOLUME /tmp
ADD ./build/libs/folha-de-pagamento-0.0.1-SNAPSHOT.jar hr-payroll.jar
ENTRYPOINT ["java","-jar","/hr-payroll.jar"]
```
* Criar imagem, e container hr-payoll. Comando gerado com -P maiusculo faz com que gere um container com porta aleatoria, não será preciso dar nome ao container, pois eles serão alto escalaveis

```
docker build -t hr-payroll:v1 .

docker run -P --network hr-net hr-payroll:v1
```
## Build do projeto oauth

```
gradle clean build
```

* Criar arquivo Dockerfile na raiz do projeto

```
FROM openjdk:11
VOLUME /tmp
ADD ./build/libs/oauth-server-0.0.1-SNAPSHOT.jar hr-oauth.jar
ENTRYPOINT ["java","-jar","/hr-oauth.jar"]
```
* Criar imagem, e container hr-oauth. Comando gerado com -P maiusculo faz com que gere um container com porta aleatoria, não será preciso dar nome ao container, pois eles serão alto escalaveis

```
docker build -t hr-oauth:v1 .

docker run -P --network hr-net hr-oauth:v1
```

## Build do projeto api-gateway

```
gradle clean build
```
* Criar arquivo Dockerfile na raiz do projeto

```
FROM openjdk:11
VOLUME /tmp
EXPOSE 8765
ADD ./build/libs/api-gateway-0.0.1-SNAPSHOT.jar hr-api-gateway-zuul.jar
ENTRYPOINT ["java","-jar","/hr-api-gateway-zuul.jar"]
```
* Criar imagem, e container hr-api-gateway. 

```
docker build -t hr-api-gateway-zuul:v1 .

docker run -p 8765:8765 --name hr-api-gateway-zuul --network hr-net hr-api-gateway-zuul:v1
```

## Para levantar mais instancias de microsserviços, worker, payrrol, oauth, user, é so levantar o container que automaticamente irao se registrar no eureka-server e o serviço da Api-gateway vai fazer o balanceamento de cargas.
* É muito simples escalar varias instancias de um microsserviço, com configuração automatica e escala automatica

```
docker run -P --network hr-net hr-worker:v1
docker run -P --network hr-net hr-user:v1
docker run -P --network hr-net hr-payroll:v1
docker run -P --network hr-net hr-oauth:v1
```
## Quando quiser derrubar uma instancia basta digitar o comando

```
docker stop idContainer
```

![alter text](https://github.com/wagnersistemalima/kotlin-spring-boot-microsservicos-spring-cloud/blob/main/images/eurekaInstancias.png)
