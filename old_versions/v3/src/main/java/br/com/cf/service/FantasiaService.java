package br.com.cf.service;

import br.com.cf.domain.pojos.FantasiaPOJO;

public interface FantasiaService extends CustomService<FantasiaPOJO> {

	public void comprarFantasias(String codigo, int quantidade);

	public void venderFantasias(String codigo, int quantidade);

	public void venderFantasias(String codigo, String cpf, int quantidade);

	public void mudarPrecos(String codigo, String precoCompra, String precoVenda);

}