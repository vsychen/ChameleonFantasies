/**
 * Roupa.js
 *
 * @description :: Basic fields of Roupa
 */

module.exports = {
  attributes: {
    tipo: {
      type: 'string',
      enum: ['chapeu', 'parte de cima', 'parte de baixo', 'braços', 'pernas'],
      required: true
    },
    descricao: {
      type: 'string',
      maxLength: 200
    },
    fantasia: {
      model: 'Fantasia',
      required: true
    }
  }
};

