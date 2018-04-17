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

[The MIT License](https://github.com/vsychen/chameleonfantasies/raw/master/LICENSE.md) - Copyright (c) 2018 Victor Sin Yu Chen

[Github Corners](https://github.com/tholman/github-corners/raw/master/license.md) - Copyright (c) 2016 Tim Holman
