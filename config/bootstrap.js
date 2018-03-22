/**
 * Bootstrap
 * (sails.config.bootstrap)
 *
 * An asynchronous bootstrap function that runs before your Sails app gets lifted.
 * This gives you an opportunity to set up your data model, run jobs, or perform some special logic.
 *
 * For more information on bootstrapping your app, check out:
 * http://sailsjs.org/#!/documentation/reference/sails.config/sails.config.bootstrap.html
 */

module.exports.bootstrap = function(cb) {
  sails.config.appName = "Chameleon Fantasies";
  sails.path = require('path');

  sails.on('lifted', function() {
    // CF needs at least one admin
    UsuarioService.list()
      .then(function(r) {
        if (r.length == 0) {
          UsuarioService.add({ nome:'Administrador', cpf:'000.000.000-00', email:'admin@cf.com', senha:'admin', avatar:'' });
        }
      })
      .catch(function(e) {
        sails.log(e);
      });

      // Sample Catalog
      FantasiaService.list()
        .then(function(r) {
          if (r.length == 0) {
            // Harry Potter com Capa da Invisibilidade
            FantasiaService.add({ nome:'Harry Potter com Capa da Invisibilidade', preco:'500,00', quantidade:10, imagem:'/images/no_image.png' }, []);

            // Batman - image @from [http://clipart-library.com/images/6iro6bpAT.gif]
            FantasiaService.add({ nome:'Batman', preco:'1.000,00', quantidade:7, imagem:'/images/fantasias/sample_batman.png' },
              [{ tipo:'chapeu', descricao:'Máscara preta' },
               { tipo:'parte de cima', descricao:'Suéter cinza com símbolo de morcego e capa' },
               { tipo:'parte de baixo', descricao:'Calça cinza e preta com cinto' },
               { tipo:'bracos', descricao:'Luvas pretas' },
               { tipo:'pernas', descricao:'Botas pretas' }]);

            // Mulher Maravilha - image @from [https://i.pinimg.com/originals/e6/1a/01/e61a019f2a74c71c0a192db7e8d885dc.jpg]
            FantasiaService.add({ nome:'Mulher Maravilha', preco:'800,00', quantidade:6, imagem:'/images/fantasias/sample_wonderwoman.png' },
              [{ tipo:'chapeu', descricao:'Tiara dourada' },
               { tipo:'parte de cima', descricao:'Top vermelho e dourado com capa azul' },
               { tipo:'parte de baixo', descricao:'Saia azul com estampa de estrelas' },
               { tipo:'bracos', descricao:'Braceletes cinza' },
               { tipo:'pernas', descricao:'Botas vermelho e brancas' }]);

            // Super Homem - image @from [https://i.pinimg.com/originals/a5/fb/34/a5fb34354a92f7681663a625e72b8af4.jpg]
            FantasiaService.add({ nome:'Super Homem', preco:'700,00', quantidade:9, imagem:'/images/fantasias/sample_superman.png' },
              [{ tipo:'parte de cima', descricao:'Suéter azul e vermelho com capa vermelha' },
               { tipo:'parte de baixo', descricao:'Calça azul e vermelha' },
               { tipo:'pernas', descricao:'Botas vermelhas' }]);

            // Volverine - image @from [https://i.pinimg.com/originals/5c/af/5c/5caf5c01554f9dfb30c75f5287d98c6a.jpg]
            FantasiaService.add({ nome:'Volverine', preco:'800,00', quantidade:3, imagem:'/images/fantasias/sample_wolverine.png' },
              [{ tipo:'chapeu', descricao:'Máscara amarela e preta' },
               { tipo:'parte de cima', descricao:'Suéter amareo, azul e preto' },
               { tipo:'parte de baixo', descricao:'Calça amarela e azul' },
               { tipo:'bracos', descricao:'Luvas azuis com enfeites cinza' },
               { tipo:'pernas', descricao:'Botas azuis' }]);

            // Homem de Ferro - image @from [https://i.pinimg.com/originals/60/af/20/60af2077698a6a57fd819390d502da09.jpg]
            FantasiaService.add({ nome:'Homem de Ferro', preco:'1.200,00', quantidade:1, imagem:'/images/fantasias/sample_ironman.png' },
              [{ tipo:'chapeu', descricao:'Máscara vermelha e dourada' },
               { tipo:'parte de cima', descricao:'Suéter vermelho e dourado' },
               { tipo:'parte de baixo', descricao:'Calça dourada e vermelha' },
               { tipo:'bracos', descricao:'Luvas vermelhas' },
               { tipo:'pernas', descricao:'Botas vermelhas' }]);

            // Hulk - image @from [https://i.pinimg.com/736x/3e/e7/da/3ee7da93c43c088b88fe47b6246f95a0--hulk-logo-printable-hulk-.jpg]
            FantasiaService.add({ nome:'Hulk', preco:'300,00', quantidade:6, imagem:'/images/fantasias/sample_hulk.png' },
              [{ tipo:'parte de baixo', descricao:'Calça roxa rasgada' }]);

            // Capitão América - image @from [https://i.pinimg.com/736x/51/6f/ed/516fed52b03c4569633428fdd7a7f259--captain-america-wallpaper-captain-america-art.jpg]
            FantasiaService.add({ nome:'Capitão América', preco:'800,00', quantidade:6, imagem:'/images/fantasias/sample_captamerica.png' },
              [{ tipo:'chapeu', descricao:'Máscara azul' },
               { tipo:'parte de cima', descricao:'Suéter azul, vermelho e branco' },
               { tipo:'parte de baixo', descricao:'Calça azul' },
               { tipo:'bracos', descricao:'Luvas marrons' },
               { tipo:'pernas', descricao:'Botas azuis' }]);

            // Viúva Negra - image @from [https://i.pinimg.com/originals/5f/a1/34/5fa134dabed878e23f729685354a5139.png]
            FantasiaService.add({ nome:'Viúva Negra', preco:'700,00', quantidade:4, imagem:'/images/fantasias/sample_blackwidow.png' },
              [{ tipo:'parte de cima', descricao:'Suéter preto' },
               { tipo:'parte de baixo', descricao:'Calça preta' },
               { tipo:'bracos', descricao:'Luvas pretas' },
               { tipo:'pernas', descricao:'Botas pretas' }]);
          }
        })
        .catch(function(e) {
          sails.log(e);
        });
  });

  // It's very important to trigger this callback method when you are finished
  // with the bootstrap!  (otherwise your server will never lift, since it's waiting on the bootstrap)
  cb();
};
