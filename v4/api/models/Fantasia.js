/**
 * Fantasia.js
 *
 * @description :: Basic fields of Fantasia
 */

module.exports = {
  attributes: {
    nome: {
      type: 'string',
      required: true,
      minLength: 3,
      maxLength: 50
    },
    pecas: {
      collection: 'Roupa',
      via: 'fantasia'
    },
    quantidade: {
      type: 'integer',
      defaultsTo: 0,
      required: true,
      min: 0
    },
    preco: {
      type: 'string',
      defaultsTo: '0,00',
      required: true,
      regex: /^\d{1,3}(\.\d{3})*,\d{2}$/
    },
    imagem: {
      type: 'string'
    }
  },

  afterDestroy: function(destroyedRecords, cb) {
    Roupa.destroy({fantasia: _.pluck(destroyedRecords, 'id')}).exec(cb);
  }
};

