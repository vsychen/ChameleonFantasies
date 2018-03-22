/**
 * FileService.js
 *
 * @description :: Server-side logic for managing Files (Photos)
 */

module.exports = {
  upload: function(file, path, maxBytes) {
    sails.log("[ArquivoService.upload] Aviso: Salvando imagem...");

    return new Promise(function(s, f) {
      file.upload({ dirname:path, maxBytes:maxBytes }, function(e1, uploaded) {
        if (e1) {
          sails.log("[ArquivoService.upload] Erro: " + e1);
          return f({ message:'500' });
        }

        if (uploaded.length == 0) {
          sails.log("[ArquivoService.upload] Aviso: Nenhuma imagem foi salva.");
          return s('');
        } else {
          sails.log("[ArquivoService.upload] Aviso: Imagem salva.");
          return s(uploaded[0].fd);
        }
      });
    });
  }
};

