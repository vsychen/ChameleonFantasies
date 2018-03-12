/**
 * FantasiaController
 *
 * @description :: Controller for managing Fantasias
 */

module.exports = {
  catalogo: function(req, res) {
    FantasiaService.list()
      .then(function(r) {
        res.status(200);

        if (req.wantsJSON) {
          return res.json({ Result:r });
        } else {
          return res.view('catalogo', { fantasias:r });
        }
      })
      .catch(function(e) {
        res.status(500);
        req.addFlash('error', "Erro no servidor. O catálogo não está disponível.");
        return res.redirect('/');
      });
  },

  procurar: function(req, res) {
    FantasiaService.search(+req.param('id'))
      .then(function(r) {
        res.status(200);

        if (req.wantsJSON) {
          return res.json({ Result:r });
        } else {
          return res.view('procurar', { fantasia:r });
        }
      })
      .catch(function(e) {
        if (e.message == '404') {
          res.status(404);
          req.addFlash('error', "A fantasia selecionada não está cadastrada.");
          return res.redirect('all');
        } else {
          res.status(500);
          req.addFlash('error', "Erro no servidor.");
          return res.redirect('all');
        }
      });
  },

  adicionarFantasia: function(req, res) {
    if (!req.session.authenticated) {
      res.status(405);
      req.flash('error', "Esta ação é restrita à administradores.");
      return res.redirect('all');
    }

    var fantasia = { nome:req.param('nome'), preco:req.param('preco'), quantidade:+req.param('quantidade'), imagem:'/images/no_image.png' };
    var pecas = [];
    if (req.param('chapeu') != '') pecas.push({ tipo: 'chapeu', descricao: req.param('chapeu') });
    if (req.param('parteCima') != '') pecas.push({ tipo: 'parte de cima', descricao: req.param('parteCima') });
    if (req.param('parteBaixo') != '') pecas.push({ tipo: 'parte de baixo', descricao: req.param('parteBaixo') });
    if (req.param('bracos') != '') pecas.push({ tipo: 'braços', descricao: req.param('bracos') });
    if (req.param('pernas') != '') pecas.push({ tipo: 'pernas', descricao: req.param('pernas') });

    ArquivoService.upload(req.file('imagem'), '../../assets/images/fantasias', 10000000)
      .then(function(r1) {
        if (r1 != '') {
          fantasia.imagem = '/images/fantasias/' + require('path').basename(r1);
        }

        return FantasiaService.add(fantasia, pecas);
      })
      .then(function(r2) {
        res.status(200);

        if (req.wantsJSON) {
          return res.json({ Result:r2 });
        } else {
          return res.redirect('all');
        }
      })
      .catch(function(e) {
        if (e.message == '404') {
          res.status(404);
          req.addFlash('error', "Não foi possível adicionar a fantasia.");
          return res.redirect('all')
        } else {
          res.status(500);
          req.addFlash('error', "Erro no servidor.");
          return res.redirect('all');
        }
      });
  },

  alterarPreco: function(req, res) {
    if (!req.session.authenticated) {
      res.status(405);
      req.flash('error', "Esta ação é restrita à administradores.");
      return res.redirect('all');
    }

    FantasiaService.edit(+req.param('id'), req.param('preco'))
      .then(function(r) {
        res.status(200);

        if (req.wantsJSON) {
          return res.json({ status:'200' });
        } else {
          return res.redirect('all');
        }
      })
      .catch(function(e) {
        if (e.message == '404') {
          res.status(404);
          req.addFlash('error', "Fantasia não encontrada, não foi possível editar a fantasia.");
          return res.redirect('back')
        } else {
          res.status(500);
          req.addFlash('error', "Erro no servidor.");
          return res.redirect('back');
        }
      });
  },

  removerFantasia: function(req, res) {
    if (!req.session.authenticated) {
      res.status(405);
      req.flash('error', "Esta ação é restrita à administradores.");
      return res.redirect('all');
    }

    FantasiaService.remove(+req.param('id'))
      .then(function(r) {
        res.status(200);

        if (req.wantsJSON) {
          return res.json({ status:'200' });
        } else {
          return res.redirect('all');
        }
      })
      .catch(function(e) {
        if (e.message == '404') {
          res.status(404);
          req.addFlash('error', "Fantasia não encontrada, não foi possível remover a fantasia.");
          return res.redirect('back')
        } else {
          res.status(500);
          req.addFlash('error', "Erro no servidor.");
          return res.redirect('back');
        }
      });
  }
};

