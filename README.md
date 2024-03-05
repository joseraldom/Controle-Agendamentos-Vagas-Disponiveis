# Controle de Agendamentos para Vagas Disponíveis

A aplicação consiste na marcação de agendamentos mediante a requisitos para o mesmo.
Sendo possível fazer o cadastro de Vagas para que sejam preenchidas futuramente, Solicitantes que por algum motivo precisa de um angendamento e Agendamentos que pode escolher uma data e verificar se a mesma tem disponbilidade.
A consulta de Agendmentos também será possível mediante um filtro de período.

## Funcionalidades

### Cadastros de Vagas
O cadastro de Vagas irá receber um período vigente (Data Inicial e Data Final) e a Quantidade de vagas disponíveis para o mesmo período.

### Cadastro de Solicitantes
O cadastro será feita de forma simples, recebendo apenas o Nome Solicitante.

### Cadastro de Agendamentos
O agendamento poderá ser cadastrado informado uma Data, um Número de agendamento, o Motivo e o Solicitante deve ser incluído ao cadastro.

### Consulta de Agendamentos
Um período poderá ser informado para consulta de Agendamentos.

## Acesso
O acesso será feito via Menu Superior, trazendo as telas das funcionalidades. Todas as telas virão com uma lista dos dados cadastrados, tendo um botão superior para fazer um novo cadastro.

## Validações
Uma série de validações foram implementadas para o funcionamento correto da aplicação. Ao cadastrar, todos os campo deverão ser preenchidos, caso contrário não poderá ser feita a inclusão, exibindo alertas do problema encontrado.
Em campos que possuem entrada de Data, foram feitas validações para que, por exemplo, vagas não sejam repitidas, um agendamento sejá feito em uma data retroativa ou a Data Final seja antes da Data Inicial.

## Arquitetura
A aplicação foi contruida em uma stack baseada em Java. Sendo seu back-end em Spring boot com JPA / Hibernate para persistencia de dados e o front-end feito com JSF e Primefaces.

## Configurações

### Configuração de Banco
No arquivo "application.properties" informe em %CAMINHO_BANCO_HSQL_LOCAL% o
caminho completo utilizado para acesso ao banco de dados:
spring.datasource.url=jdbc:hsqldb:file:%CAMINHO_BANCO_HSQL_LOCAL
%;hsqldb.lock_file=false <br />
Por exemplo: C:\\Projetos\\Teste-Pratico-Desenvolvedor-Java\\banco\\agenda

### Instalação e Inicialização
Ao acessar a raiz da aplicação via terminal, deve ser rodado o comando **mvn install -DskipTests** para a geração do .jar, em seguida rodar o comando **java -jar -Dserver.port=9494 target/Teste-Pratico-Desenvolvedor-Java-0.0.1-
SNAPSHOT.jar**. <br /> A plicação deve ser acessada via browser pelo caminho *http://localhost:9494*.
