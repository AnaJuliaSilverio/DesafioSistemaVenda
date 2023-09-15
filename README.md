# Sistema de Gerenciamento de Vendas💻
## Link do vídeo
https://drive.google.com/file/d/1HrNRGcx6quI3KQYe0-Lvm_tLnsMEAYDY/view?usp=sharing
## Tecnologias Utilizadas 

- **Spring Boot:** O framework Spring Boot é usado para construir a aplicação Java de forma rápida e simplificada.
- **PostgreSQL:** O banco de dados PostgreSQL é utilizado para armazenar os dados dos livros.
- **Flyway:** Permite que os desenvolvedores gerenciem a evolução do banco de dados de maneira confiável, automatizada e incremental.
## Mapeamento 
- ![img.png](src%2Fmain%2Fresources%2Fimg.png)
## Querys personalizadas
- Pesquise os itens que foram vendidos acima de 10,00<br />
`SELECT DISTINCT sp.product.name 
FROM SalesProducts sp WHERE sp.quantitySold * sp.product.price > 10`

- Altere o valor do VALOR_TOTAL (para zero) de todos os
registros onde este campo é nulo.<br />
`UPDATE Product p SET p.quantity = COALESCE(p.quantity, 0) WHERE p.quantity IS NULL`

- Pesquise o salário dos vendedores e ordene o resultado do
maior salário para o menor.<br />
`SELECT s FROM Seller s ORDER BY s.salary DESC` 

- Pesquisar quantos usuários tem o email zup.com.br<br/>
`SELECT COUNT(*) FROM Client c WHERE c.email LIKE '%zup.com.br'`

 encontradas, formatos de data incorretos e nossa exceção personalizada para status dos empréstimos.

## Entidades

#### Entidade Client
A classe `Client` é uma entidade JPA que representa um cliente e é mapeada para a tabela `client` no banco de dados PostgreSQL.

#### Entidade Seller
A classe `Seller` é uma entidade JPA que representa um vendedor e é mapeada para a tabela `seller` no banco de dados PostgreSQL.

#### Entidade Product
A classe `Product` é uma entidade JPA que representa um produto e é mapeada para a tabela `product` no banco de dados PostgreSQL.

#### Entidade Sale
A classe `Sale` é uma entidade JPA que representa uma venda e é mapeada para a tabela `sale` no banco de dados PostgreSQL.
#### Entidade SalesProducts
A classe `SalesProducts` é uma entidade JPA que representa que é resultado do relacionamento
N:N entre sale e products ,e é mapeada para a tabela `sales_products` no banco de dados PostgreSQL.

## Relacionamentos

### Relacionamento Product,Sale e SalesProducts
Um produto pode estar relacionada a várias vendas e uma venda pode ter vários produtos. Por isso,
foi necessário criar uma tabela auxiliar, que além de armazenar as pks de produto e venda, também um
tem atributo para guardar a quantidade de cada produto vendido.

### Relacionamento Sale,Client
Uma venda pode ter apenas um cliente mas um cliente pode ter várias vendas. Por isso, a fk foi 
movida para a tabela Sale.

### Relacionamento Sale,Seller
Uma venda pode ter apenas um vendedor mas um vendedor pode ter várias vendas. Por isso, a fk foi
movida para a tabela Sale.

## DTOs
Os DTOs são objetos de transferência de dados utilizados para comunicação entre a camada de controle e a camada de serviço. O DTO `ExceptionResponseDTO` é usado para exceções.

## Service
São responsáveis pela lógica de negócios relacionada às entidades. Eles realizam a criação, querys personalizadas, recuperação e remoção de entidades, utilizando o ModelMapper para mapear entre modelos e DTOs.

## Repository
Os repositórios são interfaces que estendem JpaRepository do Spring Data JPA, permitindo a interação com as tabelas no banco de dados. Essa interação facilita o acesso e a manipulação dos dados.
