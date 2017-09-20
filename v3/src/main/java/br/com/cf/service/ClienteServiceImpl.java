package br.com.cf.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.validator.routines.RegexValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cf.domain.Cliente;
import br.com.cf.domain.pojos.ClientePOJO;
import br.com.cf.exceptions.DataFieldException;
import br.com.cf.repository.ClienteDAO;

@Service("ClienteService")
public class ClienteServiceImpl implements CustomService<ClientePOJO> {
	@Autowired
	private ClienteDAO cdao;

	@Transactional(rollbackFor = Exception.class)
	public void salvar(ClientePOJO pojo) {
		checarInfo(pojo);

		try {
			Cliente c = new Cliente(pojo.getNome(), pojo.getCpf(), pojo.getGastos());
			cdao.salvar(c);
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new RuntimeException("Não foi possível salvar o objeto.");
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public void atualizar(ClientePOJO pojo) {
		checarInfo(pojo);

		try {
			Cliente c = cdao.procurar(pojo.getCpf());
			c.setNome(pojo.getNome());
			c.setGastos(pojo.getGastos());
			cdao.atualizar(c);
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new RuntimeException("Não foi possível atualizar o objeto.");
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public void salvarOuAtualizar(ClientePOJO pojo) {
		checarInfo(pojo);

		try {
			Cliente c = cdao.procurar(pojo.getCpf());
			c.setNome(pojo.getNome());
			c.setGastos(pojo.getGastos());
			cdao.salvarOuAtualizar(c);
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new RuntimeException("Não foi possível salvar nem atualizar o objeto.");
		}
	}

	public ClientePOJO procurar(String cpf) {
		checarCpf(cpf);
		ClientePOJO pojo = new ClientePOJO();

		try {
			Cliente c = cdao.procurar(cpf);
			pojo.setNome(c.getNome());
			pojo.setCpf(c.getCpf());
			pojo.setGastos(c.getGastos());
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new RuntimeException("Não foi possível achar o objeto.");
		}

		return pojo;
	}

	@Transactional(rollbackFor = Exception.class)
	public void remover(String cpf) {
		checarCpf(cpf);

		try {
			Cliente c = cdao.procurar(cpf);
			cdao.deletar(c);
		} catch (RuntimeException re) {
			throw new RuntimeException("Não foi possível remover o objeto.");
		}
	}

	public List<ClientePOJO> listar() {
		List<ClientePOJO> lista = new ArrayList<ClientePOJO>();
		ClientePOJO pojo = new ClientePOJO();

		for (Cliente c : cdao.listar()) {
			pojo.setNome(c.getNome());
			pojo.setCpf(c.getCpf());
			pojo.setGastos(c.getGastos());
			lista.add(pojo);
		}

		return lista;
	}

	private void checarNome(String nome) {
		if (nome.length() < 5 || nome.length() > 100)
			throw new DataFieldException();
	}

	private void checarCpf(String cpf) {
		if (!cpf.equals("") && !(new RegexValidator("(([0-9]){3}.){2}([0-9]){3}-([0-9]){2}")).isValid(cpf))
			throw new DataFieldException();
	}

	private void checarGastos(String gastos) {
		if ((new BigDecimal(gastos)).signum() == -1)
			throw new DataFieldException();
	}

	private void checarInfo(ClientePOJO pojo) {
		checarNome(pojo.getNome());
		checarCpf(pojo.getCpf());
		checarGastos(pojo.getGastos());
	}
}