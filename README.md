# Customer Admin

API para gestão de clientes que faz uso de serviços externos para consulta da localização do usuário e determinação da temperatura mínima e máxima a partir dos dados encontrados.


## Ferramentas usadas
Spring Boot e Maven foram as principais ferramentas usadas na construção desta API, devido à simplicidade que proporcionam na compilação e empacotamento do projeto, bem como na inicialização da API. Além disso foram usadas libraries complementares, tais como:
- Project Lombook para melhor leitura de código devido simplificação dos getters e setters, gerados em nível de compilação.
- Spring Data para conexão com o MongoDB, usado para persistência dos dados dos clientes.

A IDE utilizada foi o InteliJ por ser muito lightweight (em comparação com o Eclipse, por exemplo).

## Setup
Antes de tudo é necessário ter uma instância do MongoDB instalada localmente ou em algum servidor, de preferência executando na porta 17017. É possíel utilizar o Docker para esse mesmo fim; para um passo-a-passo sobre o assunto, [recomendo a leitura deste artigo](https://medium.com/@renato.groffe/docker-nosql-executando-o-mongodb-e-o-redis-a-partir-de-containers-3c143e920f09).

Com o serviço em execução, é necessário criar uma collection chamada "customer", usando o comando a seguir:
```
db.createCollection(“customer”);
```

## Rotas/endpoints (como usar)

Consultar todos os clientes cadastrados:
```
[GET] /customer
```

Consultar um cliente por ID:
```
[GET] /customer/{id}
```

Cadastrar um novo cliente:
```
[POST] /customer
```

Alterar os dados de um cliente por ID
```
[PUT] /customer/{id}
```

Remove um cliente por ID:
```
[DELETE] /customer/{id}
```


## Execução, testes e empacotamento

Para execução dos testes, execute o comando:
```
mvn test
```

Para empacotamento, execute o comando:
```
mvn clean package
```

Para subir uma instância da API, dirija-se à pasta "/target" e execute o comando a seguir:
```
java -jar <arquivo_compilado>.jar
```
OBS: atente-se às configurações de ambiente (application.properties); caso seja necessário, pode-se copiar o arquivo correspondente às configurações para a mesma pasta do arquivo .jar a ser executado.


## Considerações finais

Visando um cenário produtivo, a API em si precisa ser hospedada em um servidor Java como Apache, JBoss, Websphere, Glassfish (...). É possível ter essa solução on-premises, mas devido ao alto custo de gerenciamento, recomento uma solução cloud que dê suporte a cloud services (AWS Beanstalk, Azure App Service, etc) ou até mesmo conteinerização (Docker), maximizando o poder de gestão de capacity sem a preocupação com os recursos subjacentes (SO, Hardware, Redes, etc).

Recomendo em conjunto uma solução de CI/CD como Jenkins, Gitlab CI ou Microsoft TFS, dependendo da solução de infraestrutura escolhida.
