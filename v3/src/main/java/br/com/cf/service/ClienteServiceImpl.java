package br.com.cf.service;

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
import br.com.cf.domain.pojos.ClientePOJO;
import br.com.cf.exceptions.DataFieldException;
import br.com.cf.repository.ClienteDAO;

@Service("ClienteService")
public class ClienteServiceImpl implements ClienteService {
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

	private void checarInfo(ClientePOJO pojo) {
		Set<ConstraintViolation<ClientePOJO>> constraintViolations = Validation.buildDefaultValidatorFactory()
				.getValidator().validate(pojo);

		if (!constraintViolations.isEmpty())
			throw new DataFieldException();
	}
}