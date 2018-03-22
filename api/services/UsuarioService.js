/**
 * UsuarioService.js
 *
 * @description :: Server-side logic for managing Usuarios
 */

module.exports = {
  add: function(usuario) {
    sails.log("[UsuarioService.add] Aviso: Adicionando usuário...");

    return new Promise(function(s, f) {
      Usuario.create(usuario).exec(function(e1, r1) {
        if (e1) {
          sails.log("[UsuarioService.add] Erro: " + e1);
          return f({ message:'500' });
        } else if (r1 == null) {
          sails.log("[UsuarioService.add] Erro: Não foi possível cadastrar o usuário.");
          return f({ message:'404' });
        }

        sails.log("[UsuarioService.add] Aviso: Usuário adicionado com sucesso.");
        return s(r1);
      });
    });
  },

  search: function(id) {
    sails.log("[UsuarioService.search] Aviso: Procurando usuário...");

    return new Promise(function(s, f) {
      Usuario.find({ id:id }).exec(function(e1, r1) {
        if (e1) {
          sails.log("[UsuarioService.search] Erro: " + e1);
          return f({ message:'500' });
        } else if (r1.length == 0) {
          sails.log("[UsuarioService.search] Erro: Usuário não encontrado.");
          return f({ message:'404' });
        }

        sails.log("[UsuarioService.search] Aviso: Usuário encontrado.");
        return s(r1[0]);
      });
    });
  },

  edit: function(email, novaSenha) {
    sails.log("[UsuarioService.edit] Aviso: Editando usuario...");

    return new Promise(function(s, f) {
      if (novaSenha == '1234') {
        sails.log("[UsuarioService.edit] Erro: Não foi possível atualizar senha.");
        return f({ message:'405' });
      }

      Usuario.find({ email:email }).exec(function(e1, r1) {
        if (e1) {
          sails.log("[UsuarioService.edit] Erro: " + e1);
          return f({ message:'500' });
        } else if (r1.length == 0) {
          sails.log("[UsuarioService.edit] Erro: Usuário inexistente.");
          return f({ message:'404' });
        }

        var usuario = r1.pop();
        usuario.senha = novaSenha;
        usuario.save(function(e2) {
          if (e2) {
            return f(e2);
          } else {
            sails.log("[UsuarioService.edit] Aviso: Usuário atualizado.");
            return s(usuario);
          }
        });
      });
    });
  },

  editPrimeiroAcesso: function(email, novaSenha) {
    sails.log("[UsuarioService.edit] Aviso: Editando usuario...");

    return new Promise(function(s, f) {
      Usuario.find({ email:email }).exec(function(e1, r1) {
        if (e1) {
          sails.log("[UsuarioService.edit] Erro: " + e1);
          return f({ message:'500' });
        } else if (r1.length == 0) {
          sails.log("[UsuarioService.edit] Erro: Usuário inexistente.");
          return f({ message:'404' });
        }

        var usuario = r1.pop();

        if (usuario.senha == '1234') {
          usuario.senha = novaSenha;
        } else {
          sails.log("[UsuarioService.edit] Erro: Operação não autorizada.");
          return f({ message:'405' });
        }

        usuario.save(function(e2) {
          if (e2) {
            return f(e2);
          } else {
            sails.log("[UsuarioService.edit] Aviso: Usuário atualizado.");
            return s(usuario);
          }
        });
      });
    });
  },

  list: function() {
    sails.log("[UsuarioService.list] Aviso: Procurando usuários...");

    return new Promise(function(s, f) {
      Usuario.find().exec(function(e1, r1) {
        if (e1) {
          sails.log("[UsuarioService.list] Erro: " + e1);
          return f({ message:'500' });
        }

        sails.log("[UsuarioService.list] Aviso: " + r1.length + " usuário(s) encontrado(s).");
        return s(r1);
      });
    });
  },

  remove: function(id) {
    sails.log("[UsuarioService.remove] Aviso: Removendo usuário...");

    return new Promise(function(s, f) {
      Usuario.destroy({ id:id }).exec(function(e1, r1) {
        if (e1) {
          sails.log("[UsuarioService.remove] Erro: " + e1);
          return f({ message:'500' });
        } else if (r1.length == 0) {
          sails.log("[UsuarioService.remove] Erro: Usuário inexistente.");
          return f({ message:'404' });
        }

        sails.log("[UsuarioService.remove] Aviso: Usuário removido.");
        return s(r1);
      });
    });
  },

  login: function(email, password) {
    return new Promise(function(s, f) {
      sails.log("[UsuarioService.login] Aviso: Entrando...");

      Usuario.find({ email:email }).exec(function(e1, r1) {
        if (e1) {
          sails.log("[UsuarioService.login] Erro: " + e1);
          return f({ message:'500' });
        } else if (!r1[0] || r1[0].senha != password) {
          sails.log("[UsuarioService.login] Erro: Usuario e senha não coincidem.");
          return f({ message:'401' });
        }

        sails.log("[UsuarioService.login] Aviso: Bem vindo!");
        return s(r1[0]);
      });
    });
  }
};

