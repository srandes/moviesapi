# moviesapi

API RESTful para possibilitar a leitura da lista de indicados e vencedores
da categoria Pior Filme do Golden Raspberry Awards.

# Rodando o projeto 

Projeto criado utilizado o gerenciador de dependências maven, para compila-lo basta executar comando:

mvn clean package -DskipTest

O fat jar do spring será gerado na pasta target\moviesapi-0.0.1-SNAPSHOT.jar

Após o passo anterior, executar:

java -jar moviesapi-0.0.1-SNAPSHOT.jar

# Swagger 

A pagina do swagger com informações da API pode ser acessada em:

http://localhost:8080/api/v1/swagger-ui/index.html


# Rodando os testes

Executar o comando do maven:

mvn clean package


# Arquivo de carga de dados

O arquivo de carga de dados movielist.csv foi version em: src\main\resources

