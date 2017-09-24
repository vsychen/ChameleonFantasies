package br.com.cf.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cf.domain.Cliente;
import br.com.cf.domain.Fantasia;
import br.com.cf.domain.Roupa;
import br.com.cf.domain.pojos.ClientePOJO;
import br.com.cf.domain.pojos.FantasiaPOJO;
import br.com.cf.exceptions.DataFieldException;
import br.com.cf.exceptions.InexistentObjectException;
import br.com.cf.repository.ClienteDAO;
import br.com.cf.repository.FantasiaDAO;

@Service("FantasiaService")
public class FantasiaServiceImpl implements FantasiaService {
	@Autowired
	private ClienteDAO cdao;
	@Autowired
	private FantasiaDAO fdao;

	@Transactional(rollbackFor = Exception.class)
	public void salvar(FantasiaPOJO pojo) {
		checarInfo(pojo);

		try {
			Fantasia f = new Fantasia(pojo.getNome(), pojo.getCodigo(), pojo.getQuantidade(), pojo.getPrecoCompra(),
					pojo.getPrecoVenda());
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

	@Transactional(rollbackFor = Exception.class)
	public void comprarFantasias(String codigo, int quantidade) {
		checarCodigo(codigo);

		if (quantidade <= 0)
			throw new DataFieldException();

		try {
			Fantasia f = fdao.procurar(codigo);

			if (f.getNome().equals(""))
				throw new InexistentObjectException(f.getClass().getName());

			f.setQuantidade(f.getQuantidade() + quantidade);
			fdao.atualizar(f);
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new RuntimeException("Não é possível comprar a fantasia selecionada.");
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public void venderFantasias(String codigo, int quantidade) {
		checarCodigo(codigo);

		if (quantidade <= 0)
			throw new DataFieldException();

		try {
			Fantasia f = fdao.procurar(codigo);

			if (f.getNome().equals(""))
				throw new InexistentObjectException(f.getClass().getName());
			if (f.getQuantidade() < quantidade)
				throw new RuntimeException("Quantidade indisponível.");

			f.setQuantidade(f.getQuantidade() - quantidade);
			fdao.atualizar(f);
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new RuntimeException("Não é possível vender a fantasia selecionada.");
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public void venderFantasias(String codigo, String cpf, int quantidade) {
		checarCpf(cpf);
		checarCodigo(codigo);

		if (quantidade <= 0)
			throw new DataFieldException();

		try {
			Cliente c = cdao.procurar(cpf);
			Fantasia f = fdao.procurar(codigo);

			if (c.getNome().equals(""))
				throw new InexistentObjectException(c.getClass().getName());
			else if (f.getNome().equals(""))
				throw new InexistentObjectException(f.getClass().getName());
			if (f.getQuantidade() < quantidade)
				throw new RuntimeException("Quantidade indisponível.");

			c.setGastos(new BigDecimal(f.getPrecoVenda()).multiply(new BigDecimal(quantidade)).stripTrailingZeros()
					.toPlainString());
			f.setQuantidade(f.getQuantidade() - quantidade);
			cdao.atualizar(c);
			fdao.atualizar(f);
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new RuntimeException("Não é possível vender a fantasia selecionada.");
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public void mudarPrecos(String codigo, String precoCompra, String precoVenda) {
		checarCodigo(codigo);

		if (precosInvalidos(precoCompra, precoVenda))
			throw new DataFieldException();

		try {
			Fantasia f = fdao.procurar(codigo);

			if (f.getNome().equals(""))
				throw new InexistentObjectException(f.getClass().getName());

			f.setPrecoCompra(precoCompra);
			f.setPrecoVenda(precoVenda);
			fdao.atualizar(f);
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new RuntimeException("Não foi possível mudar os preços da fantasia selecionada.");
		}
	}

	private void checarCpf(String cpf) {
		ClientePOJO pojo = new ClientePOJO();
		pojo.setCpf(cpf);

		Set<ConstraintViolation<ClientePOJO>> constraintViolations = Validation.buildDefaultValidatorFactory()
				.getValidator().validate(pojo);

		constraintViolations.iterator().forEachRemaining(new Consumer<ConstraintViolation<ClientePOJO>>() {
			@Override
			public void accept(ConstraintViolation<ClientePOJO> t) {
				if (constraintViolations.iterator().next().getMessage().equals("CPF inválido"))
					throw new DataFieldException();
			}
		});
	}

	private void checarCodigo(String codigo) {
		if (codigo.equals(""))
			return;

		FantasiaPOJO pojo = new FantasiaPOJO();
		pojo.setCodigo(codigo);

		Set<ConstraintViolation<FantasiaPOJO>> constraintViolations = Validation.buildDefaultValidatorFactory()
				.getValidator().validate(pojo);

		constraintViolations.iterator().forEachRemaining(new Consumer<ConstraintViolation<FantasiaPOJO>>() {
			@Override
			public void accept(ConstraintViolation<FantasiaPOJO> t) {
				if (constraintViolations.iterator().next().getMessage().equals("Código inválido"))
					throw new DataFieldException();
			}
		});
	}

	private boolean precosInvalidos(String precoCompra, String precoVenda) {
		BigDecimal pc = new BigDecimal(precoCompra), pv = new BigDecimal(precoVenda);
		return pc.compareTo(new BigDecimal("0")) <= 0 || pv.compareTo(pc) <= 0;
	}

	private void checarInfo(FantasiaPOJO pojo) {
		Set<ConstraintViolation<FantasiaPOJO>> constraintViolations = Validation.buildDefaultValidatorFactory()
				.getValidator().validate(pojo);

		if (!constraintViolations.isEmpty() || precosInvalidos(pojo.getPrecoCompra(), pojo.getPrecoVenda()))
			throw new DataFieldException();
	}
}