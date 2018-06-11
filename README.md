# README #

Estes documento README tem como objetivo fornecer as informações necessárias para realização do projeto Empresas.

### O QUE FAZER ? ###

* Você deve fazer o checkout deste repositório, criar o código e ao finalizar realizar o commit e solicitar um pull request, nós iremos avaliar e retornar por email o resultado do seu teste.

### ESCOPO DO PROJETO ###

* Deve ser criado um aplicativo Android utilizando linguagem nativa(Java) com as seguintes especificações:
* Login e acesso de Usuário já registrado
	* Para o login usamos padrões OAuth 2.0. Na resposta de sucesso do login a api retornará 3 custom headers (access-token, client, uid);
	* Para ter acesso as demais APIS precisamos enviar esses 3 custom headers para a API autorizar a requisição;
* Listagem de Empresas
* Detalhamento de Empresas

### Informações Importantes ###

* Layout e recortes disponíveis no Zeplin (http://zeplin.io)
Login - teste_ioasys
Senha - ioasys123

* Integração disponível a partir de uma collection para Postman (https://www.getpostman.com/apps) disponível neste repositório.

* Independente de onde conseguiu chegar no teste é importante disponibilizar seu fonte para analisarmos.

### Dados para Teste ###

* Servidor: http://54.94.179.135:8090
* Versão da API: v1
* Usuário de Teste: testeapple@ioasys.com.br
* Senha de Teste : 12341234

### Dicas ###

* Para requisição sugerimos usar a biblioteca Retrofit
* Para download e cache de imagens use a biblioteca Glide
* Para parse de Json use a biblioteca GSON