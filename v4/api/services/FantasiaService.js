/**
 * FantasiaService.js
 *
 * @description :: Server-side logic for managing Fantasias
 */

module.exports = {
  add: function(fantasia, pecas) {
    sails.log("[FantasiaService.add] Aviso: Adicionando fantasia...");

    return new Promise(function(s, f) {
      Fantasia.create(fantasia).exec(function(e1, r1) {
        var addPeca = function(peca) {
          peca.fantasia = r1.id;
  
          return Roupa.create(peca).exec(function(e2, r2) {
            if (e2) {
              return Promise.reject(e2);
            } else {
              return Promise.resolve(r2);
            }
          });
        }

        if (e1) {
          sails.log("[FantasiaService.add] Erro: " + e1);
          return f({ message:'500' });
        } else if (r1 == null) {
          sails.log("[FantasiaService.add] Erro: Não foi possível adicionar a fantasia");
          return f({ message:'404' });
        }

        var addPecas = pecas.map(addPeca);

        Promise.all(addPecas)
          .then(function() {
            sails.log("[FantasiaService.add] Aviso: Fantasia adicionada com sucesso.");
            return s(r1);
          })
          .catch(function(e2) {
            sails.log("[FantasiaService.add] Erro: " + e2);
            return f({ message:'500' });
          });
      });
    });
  },

  search: function(id) {
    sails.log("[FantasiaService.search] Aviso: Procurando fantasia...");

    return new Promise(function(s, f) {
      Fantasia.find({ id:id }).populate('pecas').exec(function(e, r) {
        if (e) {
          sails.log("[FantasiaService.search] Erro: " + e);
          return f({ message:'500' });
        } else if (r.length == 0) {
          sails.log("[FantasiaService.search] Erro: Fantasia não encontrada.");
          return f({ message:'404' });
        }
        
        sails.log("[FantasiaService.search] Aviso: Fantasia encontrada.");
        return s(r[0]);
      });
    });
  },

  list: function() {
    sails.log("[FantasiaService.list] Aviso: Procurando fantasias...");

    return new Promise(function(s, f) {
      Fantasia.find().populate('pecas').exec(function(e, r) {
        if (e) {
          sails.log("[FantasiaService.list] Erro: " + e);
          return f({ message:'500' });
        }

        sails.log("[FantasiaService.list] Aviso: " + r.length +  " fantasia(s) encontrada(s).");
        return s(r);
      });
    });
  },

  edit: function(id, novoPreco) {
    sails.log("[FantasiaService.edit] Aviso: Editando fantasia...");

    return new Promise(function(s, f) {
      Fantasia.find({ id:id }).populate('pecas').exec(function(e1, r1) {
        if (e1) {
          sails.log("[FantasiaService.edit] Erro: " + e1);
          return f({ message:'500' });
        } else if (r1.length == 0) {
          sails.log("[FantasiaService.edit] Erro: Fantasia inexistente.");
          return f({ message:'404' });
        }

        var fantasia = r1.pop();
        fantasia.preco = novoPreco;
        fantasia.save(function(e2) {
          if (e2) {
            sails.log("[FantasiaService.edit] Erro: " + e2);
            return f({ message:'500' });
          } else {
            sails.log("[FantasiaService.edit] Aviso: Fantasia atualizada.");
            return s(fantasia);
          }
        });
      })
    })
  },

  remove: function(id) {
    sails.log("[FantasiaService.remove] Aviso: Removendo fantasia...");

    return new Promise(function(s, f) {
      Fantasia.destroy({ id:id }).exec(function(e1, r1) {
        if (e1) {
          sails.log("[FantasiaService.remove] Erro: " + e1);
          return f({ message:'500' });
        } else if (r1.length == 0) {
          sails.log("[FantasiaService.remove] Erro: Fantasia inexistente.");
          return f({ message:'404' });
        }

        sails.log("[FantasiaService.remove] Aviso: Fantasia removida.");
        return s(r1);
      });
    });
  }
};

