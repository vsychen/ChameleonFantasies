package br.com.cf.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.validator.routines.RegexValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cf.domain.Fantasia;
import br.com.cf.domain.Roupa;
import br.com.cf.domain.pojos.FantasiaPOJO;
import br.com.cf.exceptions.DataFieldException;
import br.com.cf.repository.FantasiaDAO;

@Service("FantasiaPOJOService")
public class FantasiaServiceImpl implements CustomService<FantasiaPOJO> {
	@Autowired
	private FantasiaDAO fdao;

	@Transactional(rollbackFor = Exception.class)
	public void salvar(FantasiaPOJO pojo) {
		checarInfo(pojo);

		try {
			Fantasia f = new Fantasia(pojo.getNome(), pojo.getCodigo(), pojo.getQuantidade(), pojo.getPrecoCompra(),
					pojo.getPrecoVenda(), null, null, null, null, null);
			f.setPartes(new Roupa(f, 'h', pojo.getDescChapeu()), new Roupa(f, 't', pojo.getDescParteCima()),
					new Roupa(f, 'b', pojo.getDescParteBaixo()), new Roupa(f, 'a', pojo.getDescBracos()),
					new Roupa(f, 's', pojo.getDescSapatos()));
			fdao.salvar(f);
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new RuntimeException("Não foi possível salvar o objeto.");
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public void atualizar(FantasiaPOJO pojo) {
		checarInfo(pojo);

		try {
			Fantasia f = fdao.procurar(pojo.getCodigo());
			f.setNome(pojo.getNome());
			f.setQuantidade(pojo.getQuantidade());
			f.setPrecoCompra(pojo.getPrecoCompra());
			f.setPrecoVenda(pojo.getPrecoVenda());

			List<Roupa> partes = f.getPartes();
			partes.get(0).setDescricao(pojo.getDescChapeu());
			partes.get(1).setDescricao(pojo.getDescParteCima());
			partes.get(2).setDescricao(pojo.getDescParteBaixo());
			partes.get(3).setDescricao(pojo.getDescBracos());
			partes.get(4).setDescricao(pojo.getDescSapatos());
			f.setPartes(partes);

			fdao.atualizar(f);
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new RuntimeException("Não foi possível atualizar o objeto.");
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public void salvarOuAtualizar(FantasiaPOJO pojo) {
		checarInfo(pojo);

		try {
			Fantasia f = fdao.procurar(pojo.getCodigo());
			f.setNome(pojo.getNome());
			f.setQuantidade(pojo.getQuantidade());
			f.setPrecoCompra(pojo.getPrecoCompra());
			f.setPrecoVenda(pojo.getPrecoVenda());

			List<Roupa> partes = f.getPartes();
			partes.get(0).setDescricao(pojo.getDescChapeu());
			partes.get(1).setDescricao(pojo.getDescParteCima());
			partes.get(2).setDescricao(pojo.getDescParteBaixo());
			partes.get(3).setDescricao(pojo.getDescBracos());
			partes.get(4).setDescricao(pojo.getDescSapatos());
			f.setPartes(partes);

			fdao.salvarOuAtualizar(f);
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new RuntimeException("Não foi possível salvar nem atualizar o objeto.");
		}
	}

	public FantasiaPOJO procurar(String codigo) {
		checarCodigo(codigo);
		FantasiaPOJO pojo = new FantasiaPOJO();

		try {
			Fantasia f = fdao.procurar(codigo);
			pojo.setNome(f.getNome());
			pojo.setCodigo(f.getCodigo());
			pojo.setQuantidade(f.getQuantidade());
			pojo.setPrecoCompra(f.getPrecoCompra());
			pojo.setPrecoVenda(f.getPrecoVenda());
			pojo.setDescChapeu(f.getPartes().get(0).getDescricao());
			pojo.setDescParteCima(f.getPartes().get(1).getDescricao());
			pojo.setDescParteBaixo(f.getPartes().get(2).getDescricao());
			pojo.setDescBracos(f.getPartes().get(3).getDescricao());
			pojo.setDescSapatos(f.getPartes().get(4).getDescricao());
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new RuntimeException("Não foi possível achar o objeto.");
		}

		return pojo;
	}

	@Transactional(rollbackFor = Exception.class)
	public void remover(String codigo) {
		checarCodigo(codigo);

		try {
			Fantasia f = fdao.procurar(codigo);
			fdao.deletar(f);
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new RuntimeException("Não foi possível remover o objeto.");
		}
	}

	public List<FantasiaPOJO> listar() {
		List<FantasiaPOJO> lista = new ArrayList<FantasiaPOJO>();
		FantasiaPOJO pojo = new FantasiaPOJO();

		for (Fantasia f : fdao.listar()) {
			pojo.setNome(f.getNome());
			pojo.setCodigo(f.getCodigo());
			pojo.setQuantidade(f.getQuantidade());
			pojo.setPrecoCompra(f.getPrecoCompra());
			pojo.setPrecoVenda(f.getPrecoVenda());
			pojo.setDescChapeu(f.getPartes().get(0).getDescricao());
			pojo.setDescParteCima(f.getPartes().get(1).getDescricao());
			pojo.setDescParteBaixo(f.getPartes().get(2).getDescricao());
			pojo.setDescBracos(f.getPartes().get(3).getDescricao());
			pojo.setDescSapatos(f.getPartes().get(4).getDescricao());
			lista.add(pojo);
		}

		return lista;
	}

	private void checarNome(String nome) {
		if (nome.length() < 2 || nome.length() > 100)
			throw new DataFieldException();
	}

	private void checarCodigo(String codigo) {
		if (!codigo.equals("") && !(new RegexValidator("([a-zA-Z]){2}([0-9]){4}")).isValid(codigo))
			throw new DataFieldException();
	}

	private void checarQuantidade(int quantidade) {
		if (quantidade < 0)
			throw new DataFieldException();
	}

	private void checarPrecos(String precoCompra, String precoVenda) {
		BigDecimal pc = new BigDecimal(precoCompra), pv = new BigDecimal(precoVenda), zero = new BigDecimal("0");
		if (pc.compareTo(zero) < 0 || pv.compareTo(pc) <= 0)
			throw new DataFieldException();
	}

	private void checarInfo(FantasiaPOJO f) {
		checarNome(f.getNome());
		checarCodigo(f.getCodigo());
		checarQuantidade(f.getQuantidade());
		checarPrecos(f.getPrecoCompra(), f.getPrecoVenda());
	}
}