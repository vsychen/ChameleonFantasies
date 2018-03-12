# Chameleon Fantasies v3

## Descrição
Uma aplicação para gerenciamento de clientes, fantasias e funcionários.

Tecnologias utilizadas/Versão: 
 * Sprint Tool Suite [Eclipse Oxygen (4.7.0)]
 * Maven [Apache Maven 3.5.0]
 * Java [Java Development Kit 1.8]
 * Spring MVC [Spring Framework 4.3.5]
 * Hibernate [Hibernate 4.3.8]
 * JSP/JSTL [Java Standard Tag Library 1.2]
 * JUnit [JUnit 4.12]

---
## Casos de Uso

![Casos de Uso]()

## Diagrama de Componentes do Sistema

![Diagrama de Componentes do Sistema]()

## Downloads

* [Source Files](/v3.zip)

---
## Instruções

1. Para executar a aplicação utilize
    * *mvn clean install jetty:run*
2. Para executar apenas a aplicação utilize
    * *mvn clean install jetty:run -DskipTests*
3. Para executar apenas os testes utilize
    * *mvn clean test*

---
## Observações

Lembrar de configurar o MySQL na máquina antes de qualquer outra ação. Os valores configurados no hibernate.properties são:
 * Username: **cf**
 * Password: **1234**
 * Url: **mysql://localhost:3306/chameleonfantasies**

Caso seja decidido algum outro valor para algum dos três campos, alterar o arquivo **hibernate.properties**