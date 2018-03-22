/**
 * Usuario.js
 *
 * @description :: Basic fields of Usuario
 */

module.exports = {

  attributes: {
    nome: {
      type: 'string',
      required: true,
      minLength: 6,
      maxLength: 50
    },
    cpf: {
      type: 'string',
      required: true,
      regex: /^\d{3}\.\d{3}\.\d{3}\-\d{2}$/
    },
    email: {
      type: 'string',
      required: true,
      email: true
    },
    senha: {
      type: 'string',
      required: true,
      minLength: 4,
      maxLength: 20
    },
    avatar: {
      type: 'string',
    }
  }
};

