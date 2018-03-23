# Chameleon Fantasies v4

## Descrição
Uma aplicação de catálogo de fantasias.

Tecnologias utilizadas/Versão: 
 * Sails.js [Framework MVC baseado em Node.js]

---
## Downloads

* [Source Files](/chameleonfantasies/v4/v4.zip)

---
## Observações

 * Caso não vá testar no Heroku, é necessário ter MySQL ou PostgreSQL instalado na máquina.
 * Altere os valores configurados em **/config/connections.js**
   * adapter
   * host
   * port
   * database
   * user
   * password
   * ssl (remova este valor)
   * poolSize (caso esteja usando MySQL, remova este valor)

---
## Instruções

#### HerokuApp
1. [App](https://chameleonfantasies.herokuapp.com) ![Heroku](https://heroku-badge.herokuapp.com/?app=chameleonfantasies)

#### Arquivo .zip

1. Caso vá usar MySQL, instale o adaptador para MySQL
    * *npm install sails-mysql --save*
2. Instale o sails localmente
    * *npm install sails*
3. Execute a aplicação
    * *node app.js*

---
## Permissão [License]

The MIT License

Copyright (c) 2018 Victor Sin Yu Chen

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
