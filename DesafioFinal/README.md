# Desafio final - Locadora de automóveis

> Data para entrega do desafio: 31/10/2020

O desafio tinha um prazo para entrega, no qual iniciou às 18:30 e poderia ser entregue até às 23:59 do mesmo dia (31/10/2020).


## Desafio

Uma locadora de automóveis deseja continuar o desenvolvimento de um sistema de locação de veículos.
A função "listar carros" já está pronta e você foi contratado para desenvolver as demais funções.
Ficando a sua escolha o design das telas.

Para cliente:

* Cadastrar cliente
* Listar cliente

Para aluguéis de carro:

A locadora só permite que um cliente alugue um carro por vez e um mesmo carro só poderá ser alugado para outro cliente no 
momento que ele encontra-se disponível novamente.

Com base nesta regra, desenvolva a funcionalidade de aluguéis de carros. O relacionamento entre carro e cliente ainda não 
foi feito e ficará a seu cargo escolher a melhor solução.


## Regras do sistema

**Regras para cadastro do cliente [Front-end]**

Campo **Nome**:

	* Campo obrigatório;
	* Mínimo de 3 caracteres;
	* Máximo de 100 caracteres;
	* Permite apenas letras e espaços.

Campo **CPF**:

	* Campo obrigatório;
	* Permite apenas números.

Campo **Endereço** (CEP, Logradouro, Complemento, Bairro, Cidade e UF):

	* Obrigatório preenchimento de CEP, bairro, cidade e UF;
	* Deve estar integrado com um serviço de consulta de CEP (Sugestão: http://viacep.com.br);
	* O usuário pode alterar os dados que retornam do serviço de consulta.

Campo **E-mail**:

	* Deve ser um e-mail válido.

Campo **Conatato**:

	* Deve ser um telefone residêncial ou celular válido.

<br>

**Regras para cadastro do cliente [Back-end]**

Para cada cliente, aplicar as seguintes regras:

* Não permitir dois clientes com mesmo CPF;
* O nome não deve ter mais que 100 carecteres ou menos que 3.

<br>

**Regras para aluguéis de carros [Front-end]**

* Não permitir aluguel sem cliente;
* Exibir somente carros disponíveis para aluguel.

<br>

**Regras para aluguéis de carros [Back-end]**

* Não permitir que um cliente alugue mais de um carro;
* Não permitir que o mesmo carro seja alugado por mais de um cliente simultaneamente.


## Desafio bônus de implementação

[] Teste de unidade no front-end e back-end com 100% de cobertura.

[] Evoluir a funcionalidade de "listar carros" para diferenciar carros disponíveis dos carros alugados e exibir o atual locatário.

[] Evoluir a funcionalidade de "listar clientes" para indicar se ele tem um carro alugado e qual modelo.

[x] Criar a funcionalidade "exibir histórico do cliente" e apresentar o histórico de todos os carros que o cliente já alugou.



