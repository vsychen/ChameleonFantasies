/**
 * UsuarioController
 *
 * @description :: Controller for managing Usuarios
 */

module.exports = {
  login: function(req, res) {
    if (req.session.authenticated) {
      res.status(405);
      req.flash('error', "Já há um usuário logado.");
      return res.redirect('all');
    }

    UsuarioService.login(req.param('email'), req.param('senha'))
      .then(function(r) {
        req.session.authenticated = true;
        req.session.User = r;
        res.status(200);

        if (req.wantsJSON) {
          return res.json({ status:'200' });
        } else {
          return res.redirect('all');
        }
      }, function(e) {
        if (e.message == '401') {
          res.status(401);
          req.flash('error', "Usuário e senha não coincidem.");
          return res.redirect('back');
        } else {
          res.status(500);
          req.flash('error', "Erro no servidor.");
          return res.redirect('back');
        }
      });
  },

  primeiroAcesso: function(req, res) {
    if (req.session.authenticated) {
      res.status(405);
      req.flash('error', "Já há um usuário logado.");
      return res.redirect('all');
    } else if (req.param('senha') != req.param('conf_senha')) {
      res.status(400);
      req.flash('error', "Os campos de senha não conferem.");
      return res.redirect('firstaccess');
    }

    UsuarioService.editPrimeiroAcesso(req.param('email'), req.param('senha'))
      .then(function(r) {
        req.session.authenticated = true;
        req.session.User = r;
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
          req.flash('error', "E-mail não cadastrado.");
          return res.redirect('back');
        } else if (e.message == '405') {
          res.status(405);
          req.flash('error', "A senha padrão já foi alterada.");
          return res.redirect('all');
        } else {
          res.status(500);
          req.flash('error', "Erro no servidor.");
          return res.redirect('back');
        }
      });
  },

  logout: function(req, res) {
    if (!req.session.authenticated) {
      res.status(405);
      req.flash('error', "Não há usuário logado.");
      return res.redirect('all');
    }

    req.session.authenticated = false;
    req.session.User = null;

    if (req.wantsJSON) {
      return res.json({ status:'200' });
    } else {
      return res.redirect('all');
    }
  },

  cadastrarUsuario: function(req, res) {
    if (!req.session.authenticated) {
      res.status(405);
      req.flash('error', "Esta ação é restrita à administradores.");
      return res.redirect('all');
    }

    ArquivoService.upload(req.file('imagem'), '../../assets/images/usuarios', 10000000)
      .then(function(r1) {
        return UsuarioService.add({
          nome:req.param('nome'),
          cpf:req.param('cpf'),
          email:req.param('email'),
          senha:'1234',
          avatar:(r1 == '') ? '/images/no_image.png' : '/images/usuarios/' + require('path').basename(r1)
        });
      })
      .then(function(r2) {
        res.status(200);

        if (req.wantsJSON) {
          return res.json({ status:'200' });
        } else {
          return res.redirect('all');
        }
      }, function(e) {
        req.flash('error', "Erro no servidor.");
        return res.redirect('all');
      });
  },

  procurarUsuario: function(req, res) {
    if (!req.session.authenticated) {
      res.status(405);
      req.flash('error', "Esta ação é restrita à administradores.");
      return res.redirect('all');
    }

    UsuarioService.search(+req.session.User.id)
      .then(function(r) {
        res.status(400);

        if (req.wantsJSON) {
          return res.json({ Result:r });
        } else {
          return res.view('perfil', { perfil:r });
        }
      })
      .catch(function(e) {
        if (e.message == '404') {
          res.status(404);
          req.flash('error', "Usuário inexistente.");
          return res.redirect('all');
        } else {
          res.status(500);
          req.flash('error', "Erro no servidor.");
          return res.redirect('all');
        }
      });
  },

  mudarSenha: function(req, res) {
    if (!req.session.authenticated) {
      res.status(405);
      req.flash('error', "Esta ação é restrita à administradores.");
      return res.redirect('all');
    } else if (req.param('senhaNova') != req.param('conf_senhaNova')) {
      res.status(400);
      req.flash('error', "Os campos de senha não conferem.");
      return res.redirect('profile');
    }

    UsuarioService.edit(req.param('email'), req.param('senhaNova'))
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
          req.flash('error', "Usuario inexistente.");
          return res.redirect('back');
        } else if (e.message == '405') {
          res.status(405);
          req.flash('error', "Não é possível alterar a senha para a senha padrão.");
          return res.redirect('back');
        } else {
          res.status(500);
          req.flash('error', "Erro no servidor.");
          return res.redirect('back');
        }
      });
  },

  listarUsuarios: function(req, res) {
    if (!req.session.authenticated) {
      res.status(405);
      req.flash('error', "Esta ação é restrita à administradores.");
      return res.redirect('all');
    }

    UsuarioService.list()
      .then(function(r) {
        res.status(200);

        if (req.wantsJSON) {
          return res.json({ status:'200' });
        } else {
          return res.view('listaUsuarios', { usuarios:r });
        }
      })
      .catch(function(e) {
        res.status(500);
        req.flash('error', "Erro no servidor.");
        return res.redirect('all');
      });
  },

  removerUsuario: function(req, res) {
    if (!req.session.authenticated) {
      res.status(405);
      req.flash('error', "Esta ação é restrita à administradores.");
      return res.redirect('all');
    } else if (req.param('id') == req.session.User.id) {
      res.status(400);
      req.flash('error', "Não é possível remover o usuário atualmente logado.");
      return res.redirect('back');
    }

    UsuarioService.remove(+req.param('id'))
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
          req.addFlash('error', "Usuário não encontrado, não foi possível remover o usuário.");
          return res.redirect('back')
        } else {
          res.status(500);
          req.addFlash('error', "Erro no servidor.");
          return res.redirect('back');
        }
      });
  }
};

