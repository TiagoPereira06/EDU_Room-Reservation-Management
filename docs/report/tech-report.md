![Isel-Logo](https://isel.pt/media/assets/default/images/logo-isel.png)

*Licenciatura em Engenharia Informática e de Computadores*

# Projeto de Laboratório de Software 
2019/2020 Verão

## Relatório Técnico
Tiago Pereira: nº43592
Denise Rodrigues: nº44881



## Introdução

O projeto do Laboratório de Software é composto pela análise, design e implementação de um sistema de informação para gerenciar salas e a reserva das mesmas.
O seu desenvolvimento é dividido em 4 fases, nesta primeira fase foi definido tanto o domínio como a funcionalidade e a interação com o sistema de informações é feita através da execução de comandos em consola.
Se o aplicativo for executado sem argumentos entra em modo interativo, lendo as linhas de entrada e executando os comandos correspondentes, esta ação é interrompida através do comando EXIT.
Caso seja chamado com argumentos estes serão interpretados como um comando, após a execução desse comando a aplicação finaliza.
Cada comando é definido usando a seguinte estrutura genérica:  
>**{method} {path} {parameters}**

- Method define o tipo de ação a realizar, podendo ser GET ou POST;

- Path define o local em que o comando é executado;

- Parameters definem uma sequência de pares nome-valor, separados por &;

Esta fase tem como principais requisitos a possibilidade de podermos tanto criar novas instâncias de room, booking, label e user, bem como obter informação relativa a essas entidades.

## Modelação da base de dados
>## Modelação Conceptual
Foi implementado um modelo entidade-associação para a informação gerida pelo sistema.

Destacam-se os seguintes aspetos deste modelo:

É composto pelas entidades e relações apresentadas abaixo, cada uma com pelo menos uma chave primária.

- **rooms** ( name , location, capacity, description)
PK = name
- **bookings** (bid, reservationOwner, roomName, beginTime, endTime)
PK = bid
- **labels** (name)
PK = name
- **users** (email, username)
PK = email
- **roomLabels** (roomName, label)
PK = (roomName, label)

Achou-se relevante criar a relação roomLabels, que representasse a ligação entre room e as labels associadas, sendo assim temos roomName como chave estrangeira de ROOMS (name) e label como chave estrangeira de LABELS (name);

Adicionou-se também a bookings o atributo **bid**, que representa o identificador de cada booking e tendo por isso sido escolhido para chave primária.

**O modelo conceptual apresenta ainda as seguintes restrições:**

 1. Relativamente a ROOMS

Temos room como um espaço físico que uma ou mais pessoas podem usar, assim sendo a cardinalidade entre ROOMS e USERS é de N-N;

 2. Relativamente a bookings

Temos booking como reserva de um room por um determinado intervalo de tempo. Tanto o valor do tempo de início como de fim da reserva devem ser múltiplos de 10 e a duração mínima da reserva deve ser de 10 minutos;

 3. Relativamente a LABELS

Cada label representa uma característica que pode ser associada aos rooms, a mesma label pode estar associada a 0 ou mais rooms e cada room pode ter 0 ou mais labels, representando uma cardinalidade N-N;

 4. Relativamente a USERS

Um user é uma pessoa que pode reservar rooms (um ou mais).

>## Modelação Física
Após a realização do modelo EA seguiu-se a implementação do modelo físico do sistema, contemplando todas as restrições possíveis de garantir.

**Destacam-se os seguintes aspetos deste modelo:**

Foram desenvolvidos os scripts CREATETABLES e INSERTS, o primeiro contendo instruções CREATE TABLE e adicionalmente instruções DROP TABLE para criação e remoção do modelo respetivamente.

O comando DROP TABLE é composto pela cláusula IF EXISTS, o que implica remoção da tabela apenas caso esta exista, bem como dos seus dados.
Depois de termos as tabelas construídas foi possível passar a inserir dados na BD, para isso foi criado o script INSERTS.

## Organização do Software
>## Processamento de Comandos
A execução de cada tipo de comando é tratada por um componente diferente.
>Um comando é composto por method, path e um conjunto de parameters

É importante ter em conta que existe uma diferença entre path e path template, o path do comando é definido pelo path template, que nos fornece uma forma de extrair os valores do parâmetro de um caminho que satisfaça o predicado.

Temos a interface **CommandHandler** e consecutivamente uma classe de implementação por comando.
Esta interface, composta pelos métodos execute() e description() , é implementada por todas as classes Get e Post de user, room, label e booking.

É em `execute()` que recebemos o pedido do comando (CommandRequest), é também aqui que é estabelecida conexão e definida a instrução preparedStatement a ser executada no contexto dessa conexão.

Em `description()` é descrito o tipo de informação a ser retornada.

>## Encaminhamento dos Comandos

É usado o **Router** para localizar o comando adequado de acordo com o método e path do pedido (findRoute).

Através do router é ainda possível a aplicação registar novos handlers e os métodos e paths associados (addRoute).

O método `checkPathMatch()`, é responsável por verificar se os methods e paths introduzidos pelo utilizador correspondem aos de Key, caso haja é retornada uma instância de routeResult composta pelo handler e uma lista de parameters.

>Podemos então dizer que o router decide o handler, que pode ou não lidar diretamente com a base de dados.

>## Gestão de Ligações
Este ponto tem como base 3 conceitos essenciais:

 1. A **interface de conexão**, que representa uma conexão com o RDBMS;

 2. A **interface PreparedStatement**, que representa uma instrução (query, inserção, remoção ) a ser executada no contexto da conexão;

 3. O **resultado** da execução da instância de PreparedStatement, uma instância ResulSet, que permite acesso programático a um conjunto de linhas da tabela.

**Relativamente ao estabelecimento de conexão:**

Havia duas formas de obter uma instância de Connection, neste projeto foi usada uma implementação concreta de **DataSource**.

Uma instância de DataSource permite-nos aceder ao método getConnection, usado para providenciar a conexão, sendo assim, para obter a instância DataSource foi criada diretamente uma instância de uma classe que implementasse DataSource, PGSimpleDataSource foi a classe escolhida.

De seguida, por meio do método `setURL()`, definiram-se as propriedades necessárias.
No fim é sempre fechada a conexão através da chamada ao método close(), todos os recursos (statements, etc) são também fechados.

**Relativamente à interface PreparedStatement:**

As instâncias de PreparedStatement foram obtidas por meio de um método de Connection, da seguinte forma:

>PreparedStatement statement = connection.preparedStatement (queryString);

É fornecida uma queryString a cada PreparedStatement, essa query pode conter marcadores de parâmetros representados por “?”.

Antes de ser executado o statement este deve receber o valor do parâmetro para os marcadores através dos métodos setX(int parameterIndex, … ), que por sua vez contém parameterIndex como primeiro parâmetro, definindo a posição do parâmetro a ser atribuído.

Por fim, quando o statement estiver pronto para ser executado, é chamado o método executeQuery() e retornado um CommandResult com o resultado.
É importante referir que estes pontos formam tidos em conta e postos em prática na implementação de todas as classes GetX e PostX que implementassem CommandHandler.

**Acesso a dados**

Foram criadas as seguintes classes para ajudar no acesso aos dados:

|                |Handler                         |Get                         |
|----------------|-------------------------------|-----------------------------|
|Booking|BookingHandler            |GetBooking GetBookingByID GetBookingByOwner         |
|Label         |LabelHandler           |GetLabel |
|Room         |RoomHandler|GetRoom, GetRoomById GetRoomByLabel |
|User       |UserHandler|GetUser, GerUserById|

---------
|                |Post                         |Put/Delete                         |
|----------------|-------------------------------|-----------------------------|
|Booking|PostBooking            |PutBooking DeleteBooking        |
|Label         |PostHandler           | |
|Room         |PostRoom| |
|User       |PostUser||

Foi implementado nas classes **GET** e **POST** o método **execute()**, que estabelecida a conexão nos permite processar o comando e executar as queries. Aqui podemos aceder e ir buscar dados às tabelas (Get) , bem como inserir nova informação (Post).

Na fase 2 foram adicionados novos comados **PUT** e **DELETE**, que permitem atualizar dados já existentes na tabela ou remove-los.

Nos Handlers de cada classe temos ainda a implementação de alguns queries de verificação que achamos relevantes para o bom funcionamento do sistema.
O nome atribuído a estes métodos é bastante claro, não deixando dúvidas acerca daquilo que se quer validar, como por exemplo:

>`checkIfRoomIsAvailable()`, `checkIfLabelAlreadyExists()`, `checkIfEmailAlreadyExists()`…_

Dependendo do valor retornado a nova informação pode ser inserida na BD ou não, caso não seja possível, é então lançada uma exceção SQL com a mensagem do respetivo erro.

Em **PostBooking** é lançada exceção caso o room especificado não esteja disponível ou caso o tempo de duração da reserva não contemple os requisitos ( mínimo de 10 minutos).
Caso não haja problemas é adicionado a commandResult informação da nova reserva dado o bid, através de getNextBookingId() ;

Em **PostLabel** pretendemos criar uma nova label, no entanto é lançada exceção cajo essa label já exista;
Quando criamos um novo room é adicionada informação a roomLabels, essa relação é estabelecida em RoomHandler, através dos métodos getRoomLabels() e inserLabelsRoom().
É adicionada a commandResult informação sobre o novo room, no entanto, caso label não seja válida é lançada exceção.

Quanto ao **User**, é lançada exceção caso o email especificado já exista, senão é apenas adicionada ao commandResult informação sobre a nova inserção.

>## Alterações a ter em conta na fase 2
Os packages pertencentes a handler na fase 1 passam agora a conter um novo package result, com a implementação dos métodos columns() e description().
Note-se que o resultado é sempre adicionado a uma lista.

## Booking 

A classe **BookingHandler** passa a conter 3 novos métodos `getBeginTime()`, `getDuration()`, checkDuration(), necessários à implementação do novo comando PUT Booking.

Em **PutBooking** é criada uma query de update que recebe begintime, endtime, reservationowner e atualiza o booking identificado por bid caso o room esteja disponível e a duração dentro dos requisitos.

É retornado um **PostBookingResult** que recebe a lista bookingResult contendo bookingId.
Já através em **DeleteBooking** é possível remover um booking dado o bid através da query de delete.

## Rooms

Foi adicionado a GetRooms o método **executeGetCommands()** onde:

 1. Começamos por verificar begin e duration, caso haja dados relativamente a esses parâmetros é retornado em result uma lista de rooms disponíveis a partir de beginTime e com a dada duração, senão é retornada a lista de todos os rooms com as respetivas labels;
 
 2. Caso seja indicada a capacidade é retornada a lista de rooms que aceitam no mínimo o número de pessoas indicado em capacity, ou seja, rooms com capacity inferior são removidos da lista;
 
 3. Dada uma label removemos da lista os rooms cuja label não corresponda a esta.
Para tal foram implementados em RoomHandler os métodos: `getAvailableRooms()` e `getAllRoomsWithLabels()`;

## Time e Option

Foram adicionados novos comandos nesta fase;

**GET/time** que nos permite obter o tempo atual e  **OPTION/** que retorna uma lista dos comandos disponíveis dado getRoutes(), que nos apresenta os routes com as respetivas descrições.

## Request

Foi adicionado ao **CommandRequest** o método `getHeader()` e ao enumerado Method os valores `OPTION`, `DELETE` e `PUT`;

Foram ainda adicionadas as seguintes classes:

 1. Enumerado **HeaderType** com os valores ACCEPT e FILENAME;
 2. **HeaderValue**, que retorna o valor de header;
 3. **Header**, contendo um hashmap e um método `addPair()` que permite adicionar ao hashmap, isto porque a componente header é composta por uma sequência de pares nome-valor, onde cada par é separado por ‘|’ e ‘:’ entre o par. A analise do header é feita em App através de `checkHeader()`;
 
## Output

Podemos ter duas formas de representação output, **textplain** ou **html**, logo foi implementado:

 1. Um **enumerado** com os valores PLAIN e HTML;
 
 2. Uma interface **OutputInterface** com dois métodos `printToConsole()` e `printToFile()` a serem invocados em UserInterface;
 
 3. Classe **PlainTextOutput** que permite devolver um formato igual ao apresentado na fase 1, contém implementação de `printToConsole()` e `printToFile()`;
 
 4. Classe **HTMLOutput** contém implementação de `printToConsole()` e `printToFile()`, é ainda definido o formato html através dos métodos `headerFormat()`, `titleFormat()`, `footerFormat()` e `tableResultFormat()` que chama ainda `getRows()` e `getColumns()`.

## UserInterface

Tendo em conta que:
- Todos os comandos Get devem suportar o header accept, caso não seja inserido deve ser usado texto/plain;
-   Comandos Get devem ainda suportar file-name header, que define a localização do ficheiro para representação do output, caso o header não seja referido a representação é escrita no standard output;

Foram implementados os métodos `show()` e `askForCommand()` invocados mais tarde em App.

É feita a análise do header, para ver se contém accept e verificação de filename;
Caso haja filename é chamado printToFile(filename), senão é chamado printToConsole();

Se outputType for PLAIN é retornado PlainTextOutput, senão é retornado HTMLOutput.

## Processamento de erros
Visto que há possibilidade de serem lançadas exceções durante o programa foi necessário garantir um correto processamento de erros.

>A classe App é então responsável pelo tratamento das exceções.

Podemos estar, por exemplo, perante um pedido cujo método não corresponda a nenhum dos valores aceites (`GET`, `POST`, `PUT`, `DELETE` ou `EXIT`) e nesse caso é lançada exceção com a mensagem “Request not found”. Caso tal não aconteça prosseguimos então com o processamento do pedido, no entanto durante o processamento podem ainda ocorrer outras exceções.

Essas exceções são capturadas e tratadas de forma a ser retornada a mensagem de erro correspondente.

## Nota Adicional
Foram criadas as classes **RouterGetsTests**, **RouterPostTests** e **Phase2Tests** com o objetivo de testar pedidos e comprovar o bom funcionamento do programa.

>## Alterações a ter em conta na fase 3

Para esta fase foram necessárias alterações de forma a tratarmos da entrega de um servidor HTTP, capaz de lidar com todos os comandos GET desenvolvidos na primeira e na segunda fases. 
Este servidor usa o protocolo HTTP  para receber solicitações de comando e retornar as suas respostas. 
>O conteúdo da resposta pode usar os formatos text/plain e text/html.

## Listen 
  
>Foi requisitado um novo comando **LISTEN /**, que tem como objetivo iniciar o servidor HTTP. Este comando recebe um parâmetro de porta contendo a porta TCP na qual o servidor deve escutar solicitações.

Para tal adicionou-se ao enumerado Method a opção LISTEN e foi implementada a classe **HttpServlet** responsável por receber o porto, iniciar o servidor e esperar por um comando http, retornando um **HttpServletView**, como apresentado no código abaixo.


``Code Example1 - HTTPServlet``

````java
public ResultView execute(CommandRequest commandRequest, Connection connection) throws Exception {  
	Server server = new Server(8080);  
	ServletHandler handler = new ServletHandler();
	server.setHandler(handler);  
	handler.addServletWithMapping(new ServletHolder(new Servlet()), "/*");  
	server.start();  
	String status = "Server started";  
	log.info(status); 
	return new HttpServletView();  
}
````


## Output e HyperMedia
  
>Quando a resposta é representada no formato HTML e contém uma lista, cada item inclui um link para os detalhes do item.

>As respostas contêm ainda os links necessários para garantir a navegabilidade entre todos os recursos, conforme definido no gráfico apresentado no enunciado.
 
 Foi alterada a estrutura da user interface de forma a cumprir com os requisitos do enunciado, passando então a estar presente em cada package de handler uma classe view (como apresentado na tabela abaixo), responsável pela definição da representação html.
>Sendo possível nesta fase o utilizador tirar partido do programa através do browser, por intermédio do servidor.

|                |Get                        |Post                         |
|----------------|-------------------------------|-----------------------------|
|Booking|GetBookingView GetBookingByIdView GetBookingByOwnerView GetBookingByRoomView           |PostBookingView        |
|Label         |GetLabelView GetLabelByIdView           |PostLabelView |
|Room         |GetRoomView GetRoomByIdView GetRoomsByLabelView SearchRoomView| PostRoomView|
|User       |GetUserView GetUserByIdView|PostUserView|
|Time     |TimeView||

-------
|                |Put                         |Delete                         |
|----------------|-------------------------------|-----------------------------|
|Booking|PutBookingView            |DeleteBookingView        |

Abaixo encontram-se exemplos da implementação de algumas classes referentes à definição do output html correspondente.

`'Code Example1 - GetLabelView'`

````java
public String htmlOutput(){  
    return html(
	    head(
		    title(text(name()))
		    ),  
		body(
			h1(text(name())),  
			setTable(),  
			homeButton()  
            )  
    ).build();  
}

private Element setTable() {  
    return Element.table(  
            tr(th(text("Name"))),  
			tableData()  
	).addAttribute("border", "1");  
  }

private LinkedList<Node> tableData() {  
    LinkedList<Node> list = new LinkedList<>();  
 for (Label label : model) {  
        String name = label.getName();  
  list.add(  
                tr(  
                        td(anchor(text(name)).addAttribute("href", String.format("/labels/%s", name)))  
                )  
        );  
  }  
    return list;  
}
````


`Code Example2 - GetBookingByIdView`
````java
public String htmlOutput() {  
	return html(
		head(
			title(text(name()))  
		),
		body(
			h1(text(name())),
			dl(listFormat()),
			button("Bookings By Room", String.format("/rooms/%s/bookings", room)),  
			homeButton()
		)
	).build();  
}

private List<Node> listFormat() {  
	LinkedList<Node> listItems = new LinkedList<>();  
	listItems.add(li(bold((text("Id:")))));  
	listItems.add(dd(text(id)));  
	listItems.add(li(bold((text("Owner:")))));  
	listItems.add(dd(anchor(text(owner))
		.addAttribute("href", String.format("/users/%s",owner))));  
	listItems.add(li(bold((text("Room:")))));  
	listItems.add(dd(anchor(text(room))
		.addAttribute("href", String.format("/rooms/%s", room))));  
	listItems.add(li(bold((text("Begin:")))));  
	listItems.add(dd(text(begin)));  
	listItems.add(li(bold((text("End:")))));  
	listItems.add(dd(text(end)));  
	return listItems;  
}
````

Essas classes recorrem ao uso de elementos definidos em **Element**, classe responsável pela implementação da estrutura base html (versão 3) presente no package result.html de handler. 
Abaixo apresenta-se um excerto desse código.


`Code Example3 - Element`
````java
public Element(String name) {  
    this.name = name;  
}  
  
public static Element html(Node... node) {  
    return new Element("html").addNode(node);  
}  
  
public static Element head(Node... node) {  
    return new Element("head").addNode(node);  
}  
  
public static Element title(Node... node) {  
    return new Element("title").addNode(node);  
}  
  
public static Element body(Node... node) {  
    return new Element("body").addNode(node);  
}  
  
public static Element h1(Node... node) {  
    return new Element("h1").addNode(node);  
}

public static Element li(Node node) {  
    return new Element("li").addNode(node);  
}
public static Node text(String txt) {  
    return new TextNode(txt);  
}

public String build() {  
	StringBuilder sb = new StringBuilder();  
	sb.append("<");  
	sb.append(name);  
	for (Attribute attr : attributes) {  
		sb.append(" ");  
		sb.append(attr);  
	}
	sb.append(">");  
	for (Node node : nodes) {
		sb.append(node.build());
	}
	sb.append("</");  
	sb.append(name);  
	sb.append(">");  
	return sb.toString();  
}
````

>A inclusão da biblioteca html permite-nos gerar vistas com maior flexibilidade;

## HTTP

Foram adicionadas as classes **Servlet** ,responsável pelo processamento do comando http recebido e ordem de execução da tarefa e **StatusCode** , que contém os princípais códigos correspondentes ao estado do pedido http.

## Index

Funciona como index da aplicação servidor, retorna um **IndexView** que contém a descrição da representação output (html).

## ServerInterface

Criou-se a classe **ServerInterface** que imprime no output o respetivo resultado html do pedido, associando à resposta o código de estado 200 para sucesso ou 404 para NotFound.
Continua, no entanto a ser possível a forma de interação com o programa usada até à fase anterior por meio da **LocalInterface**.

>## Alterações a ter em conta na fase 4

O principal objetivo desta fase é adicionar suporte a comandos com método POST na interface HTTP, para tal foi necessário acrescentar as seguintes alterações:

 1. Recurso **/rooms/create**;
 2. Recurso **/rooms/{rid}/booking/create** ;
 3. Recurso **/users/create**;
 4. Recurso **/labels/create**;
 
>Permitindo os métodos GET e POST, que retornam uma representação HTML e criam uma nova sala, respetivamente. 

Quando bem sucedido redireciona para os respetivos detalhes e caso ocorra um erro retorna um formato HTML com a devida informação de erro.

Cada uma destas formas envia, quando submetida, um POST request para o resource path associado.
>Por exemplo: 
>A representação retornada num pedido **get/rooms/create** deve conter um formato com todos os campos necessários para criar um room;
>Quando submetido deve ser enviado um pedido POST para **/rooms/create** contendo os campos no body;
>Se o pedido POST for bem sucedido e for criado um novo room a resposta de ser 303 See Other com a localização do header a apontar para o room criado.
>
## Lógica de Implementação
Abaixo apresenta-se a implementação da classe PostLabelFromResult referente ao recurso 4, enumerado anteriormente. Foi gerado código semelhante para a realização dos restantes recursos com a descrição da representação html.
````java
public PostLabelFormResult(PostParameters postParameters) {  
    this.postParameters = postParameters;  
 this.error = !postParameters.isValid();  
}  
  
  
@Override  
public String name() {  
    return "Label Creator";  
}  
  
@Override  
public String htmlOutput() {  
    return html(  
            head(  
                    title(text(name())),  
  nav(setNavBar())  
            ),  
  body(  
                    h1(text(name())),  
  form(  
                            div(addInput("Name", "text")),  
  input().addAttribute("type", "submit").addAttribute("value", "Create!")  
  
                    ).addAttribute("method", "post")  
                            .addAttribute("action", "/labels/create")  
            )  
  
    ).build();  
}  
  
private Node[] addInput(String label, String inputType) {  
    List<Node> nodes = new ArrayList<>();  
  // ERRO NESTE LABEL ? -> SIM = != NULL  
  String errorMsg = postParameters.getErrorByParameterName(label.toLowerCase());  
 if (errorMsg == null) {  
        nodes.add(label(text(label + " ")).addAttribute("for", label.toLowerCase()));  
  } else {  
        nodes.add(label(text(label.concat(" -> ").concat(errorMsg + " ")))  
                .addAttribute("for", label.toLowerCase())  
                .addAttribute("style", "color:red"));  
  }  
    Element input = input();  
  input  
            .addAttribute("type", inputType)  
            .addAttribute("name", label.toLowerCase())  
            .addAttribute("id", label.toLowerCase())  
            .addAttribute("required", "true");  
  
 if (error) {  
        // COMO EXISTE ERRO, INPUT PREENCHIDO  
  String value = postParameters.getParameterValue(label.toLowerCase());  
  input.addAttribute("value", value);  
  }  
    nodes.add(input);  
  nodes.add(br());  
 return nodes.toArray(new Node[0]);  
}  
  
private Node setNavBar() {  
    return homeButton();  
}  
  
  
@Override  
public String plainOutput() {  
    return "Only Available on HTML Support";  
}
````


## Dependency Management
Nesta fase o projeto deixa de depender dos JARs alojadas localmente e passa a usar o sistema de dependency 
management do Grandle.

## Servlet 

Apresenta-se abaixo um excerto do código contendo alterações na classe servlet relativamente à fase anterior, dando agora suporte a comandos com o método POST no contexto HTTP.
````java
public void doPost(HttpServletRequest req, HttpServletResponse resp) {  
    serverInterface = new ServerInterface(resp);  
  processPost(req);  
}

private void processPost(HttpServletRequest req) {  
    String method = req.getMethod();  
  String requestUri = req.getRequestURI();  
  String parameters = getBodyParameters(req.getParameterMap());  
  log.info(String.format("Incoming Request: ME->%s||URI->%s||PARAM->%s", method, requestUri, parameters));  
  String[] rawTask = {method, requestUri, parameters};  
 try {  
        delegateTask(rawTask);  
  } catch (Exception e) {  
        serverInterface.showError(e);  
  }  
}
````

## Error and Status Code 
Nesta última fase do trabalho é dada ainda mais importância ao processamento de erros e valores retornados pelo status code.

Na interface StatusCode estão presentes os principais códigos:

 - OK (200) - indica um pedido bem sucedido;
 - Created (201) - indica um pedido bem sucedido e é  criado um novo recurso como resultado disso, resposta típica após um POST request;
 - SeeOther (303) - o servidor envia esta resposta ao cliente para ir buscar o recurso a outro URI com um GET request;
 - BadRequest (400) - quando o servidor não consegue entender o pedido;
 - NotFound (404) - quando o servidor não consegue encontrar o pedido;
 - InternalServerError (500) - indica que o servidor se deparou com uma situação que não consegue suportar;
 
Temos ainda as classes **AppError**, que tira partido dos status code enumerados acima e **AppException**, da qual extendem **CommandException** e  **RouterException**.
À classe CommandException temos associadas:
 - **InternalDataBaseException**
 - **ConflictArgumentException**
 - **MissingArgumentException**
 - **ResultNotFoundException**
 - **ServerException**
 - **InvalidArgumentException**
 - **ResultNotFoundException**
 
Com **InvalidArgumentException** e **MissingArgumentException** associadas ao tratamento de erros gerados pelos métodos POST/GET  e handlers para o primeiro e POST/PUT/DELETE para o segundo, com as respetivas mensagens "Invalid Argument" e "Missing Arguments".

Já RouterException é lançado em CommandRequest e CommandRouter com a mensagem "Request Not Found";
E assim sucessivamente para as restantes classes de exceções, às quais estão associadas as respetivas mensagens de erro.

 
