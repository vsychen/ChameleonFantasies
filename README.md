# Chameleon Fantasies v4

## Descrição
Uma aplicação de catálogo de fantasias.

Tecnologias utilizadas/Versão: 
 * Sails.js [Framework MVC baseado em Node.js]

---
## Instruções

#### HerokuApp
1. [App](https://chameleonfantasies.herokuapp.com) ![Heroku](https://heroku-badge.herokuapp.com/?app=chameleonfantasies)
2. Credenciais:
    * E-mail: *admin@cf.com*
    * Senha: *admin*

#### Arquivo .zip

1. Instale as dependencias do app
    1. *npm install*
2. Caso vá usar o MySQL
    1. Instale o módulo do MySQL: *npm install sails-mysql --save*
    2. Vá em *config/connections.js*, comente a configuração do PostgreSQL e descomente a configuração do MySQL
3. Execute a aplicação
    1. *node app.js*

---
## Downloads

* [Código Fonte](https://github.com/vsychen/chameleonfantasies/raw/master/v4.zip)
* [Módulos](https://github.com/vsychen/chameleonfantasies/raw/master/node_modules.zip), se necessário

---
## Observações
#### Caso não vá testar no Heroku

 * É necessário ter MySQL ou PostgreSQL instalado na máquina.
 * Caso queira, altere os valores configurados em **/config/connections.js**
   * adapter
   * host
   * port
   * database
   * user
   * password
 * Altere os valores configurados em **/config/models.js**
   * migrate (altere para 'alter')

---
## Permissão [License]

The MIT License

Copyright (c) 2018 Victor Sin Yu Chen

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
