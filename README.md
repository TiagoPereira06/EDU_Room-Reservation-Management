# ISEL - LEIC

## Software Laboratory, 2019/2020, Spring semester

  

### Students:

  

* Tiago Pereira - 43592

* Denise Rodrigues - 44881

 ># Aspetos importantes

Achou-se relevante salientar o funcionamento do processamento de erros e valores retornados pelo status code no projeto. Para tal foi adicionalmete criado um diagrama de classes que representa a hierarquia das exceções, localizado na pasta **docs** do projeto.

A tabela abaixo apresenta os valores de status code e respetivo significado, usados essencialmete na última fase do projeto.

|                |StatusCode Value                       |Meaning                         |
|----------------|-------------------------------|-----------------------------|
|OK|200           |Pedido bem sucedido.      |
| SeeOther         |303| O servidor envia esta resposta ao cliente para ir buscar o recurso a outro URI com um GET request.|
|BadRequest       |400|Quando o cliente não constroi um pedido válido.|
|Conflict|409           |Quando o pedido contém um argumento de conflitua com um elemento do servidor.|
|NotFound     |404|Quando o servidor não consegue encontrar o pedido ou resultado.|
|InternalServerError    |500| Indica que o servidor se encontra com problemas em ligar-se à base de dados.|

 

 

 
