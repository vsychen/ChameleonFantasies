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

import br.com.cf.domain.Funcionario;
import br.com.cf.domain.pojos.FuncionarioPOJO;
import br.com.cf.exceptions.DataFieldException;
import br.com.cf.repository.FuncionarioDAO;

@Service("FuncionarioService")
public class FuncionarioServiceImpl implements FuncionarioService {
	@Autowired
	private FuncionarioDAO fdao;

	@Transactional(rollbackFor = Exception.class)
	public void salvar(FuncionarioPOJO pojo) {
		checarInfo(pojo);

		try {
			Funcionario f = new Funcionario(pojo.getNome(), pojo.getCpf(), pojo.getEmail(), pojo.getTelefone(),
					pojo.getCargo(), pojo.getSalario());
			fdao.salvar(f);
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new RuntimeException("Não foi possível salvar o objeto.");
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public void atualizar(FuncionarioPOJO pojo) {
		checarInfo(pojo);

		try {
			Funcionario f = fdao.procurar(pojo.getCpf());
			f.setNome(pojo.getNome());
			f.setEmail(pojo.getEmail());
			f.setTelefone(pojo.getTelefone());
			f.setCargo(pojo.getCargo());
			f.setSalario(pojo.getSalario());
			fdao.atualizar(f);
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new RuntimeException("Não foi possível atualizar o objeto.");
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public void salvarOuAtualizar(FuncionarioPOJO pojo) {
		checarInfo(pojo);

		try {
			Funcionario f = fdao.procurar(pojo.getCpf());
			f.setNome(pojo.getNome());
			f.setEmail(pojo.getEmail());
			f.setTelefone(pojo.getTelefone());
			f.setCargo(pojo.getCargo());
			f.setSalario(pojo.getSalario());
			fdao.salvarOuAtualizar(f);
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new RuntimeException("Não foi possível salvar nem atualizar o objeto.");
		}
	}

	public FuncionarioPOJO procurar(String cpf) {
		checarCpf(cpf);
		FuncionarioPOJO pojo = new FuncionarioPOJO();

		try {
			Funcionario f = fdao.procurar(cpf);
			pojo.setNome(f.getNome());
			pojo.setCpf(f.getCpf());
			pojo.setEmail(f.getEmail());
			pojo.setTelefone(f.getTelefone());
			pojo.setCargo(f.getCargo());
			pojo.setSalario(f.getSalario());
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
			Funcionario f = fdao.procurar(cpf);
			fdao.deletar(f);
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new RuntimeException("Não foi possível remover o objeto.");
		}
	}

	public List<FuncionarioPOJO> listar() {
		List<FuncionarioPOJO> lista = new ArrayList<FuncionarioPOJO>();
		FuncionarioPOJO pojo = new FuncionarioPOJO();

		for (Funcionario f : fdao.listar()) {
			pojo.setNome(f.getNome());
			pojo.setCpf(f.getCpf());
			pojo.setEmail(f.getEmail());
			pojo.setTelefone(f.getTelefone());
			pojo.setCargo(f.getCargo());
			pojo.setSalario(f.getSalario());
			lista.add(pojo);
		}

		return lista;
	}

	private void checarCpf(String cpf) {
		FuncionarioPOJO pojo = new FuncionarioPOJO();
		pojo.setCpf(cpf);

		Set<ConstraintViolation<FuncionarioPOJO>> constraintViolations = Validation.buildDefaultValidatorFactory()
				.getValidator().validate(pojo);

		constraintViolations.iterator().forEachRemaining(new Consumer<ConstraintViolation<FuncionarioPOJO>>() {
			@Override
			public void accept(ConstraintViolation<FuncionarioPOJO> t) {
				if (constraintViolations.iterator().next().getMessage().equals("CPF inválido"))
					throw new DataFieldException();
			}
		});
	}

	private void checarInfo(FuncionarioPOJO pojo) {
		Set<ConstraintViolation<FuncionarioPOJO>> constraintViolations = Validation.buildDefaultValidatorFactory()
				.getValidator().validate(pojo);

		if (!constraintViolations.isEmpty())
			throw new DataFieldException();
	}
}