## README
 ># Aspetos importantes

Achou-se relevante salientar o funcionamento do processamento de erros e valores retornados pelo status code no projeto. Para tal foi adicionalmete criado um diagrama de classes que representa a hierarquia das exceções, localizado na pasta **docs** do projeto.

A tabela abaixo apresenta os valores de status code e respetivo significado, usados essencialmete na última fase do projeto.

|                |StatusCode Value                       |Meaning                         |
|----------------|-------------------------------|-----------------------------|
|OK|200           |Pedido bem sucedido      |
|Created         |201           |Pedido bem sucedido, é  criado um novo recurso como resultado disso; Resposta típica após um POST request; |
| SeeOther         |303| O servidor envia esta resposta ao cliente para ir buscar o recurso a outro URI com um GET request;|
|BadRequest       |400|Quando o servidor não consegue entender o pedido;|
|NotFound     |404|Quando o servidor não consegue encontrar o pedido;|
|InternalServerError    |500| Indica que o servidor se deparou com uma situação que não consegue suportar;|

 

 

 
