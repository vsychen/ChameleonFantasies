package br.com.cf.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.RegexValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cf.domain.Funcionario;
import br.com.cf.domain.pojos.FuncionarioPOJO;
import br.com.cf.exceptions.DataFieldException;
import br.com.cf.repository.FuncionarioDAO;

@Service("FuncionarioService")
public class FuncionarioServiceImpl implements CustomService<FuncionarioPOJO> {
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

	private void checarNome(String nome) {
		if (nome.length() < 5 || nome.length() > 100)
			throw new DataFieldException();
	}

	private void checarCpf(String cpf) {
		if (!cpf.equals("") && !(new RegexValidator("(([0-9]){3}.){2}([0-9]){3}-([0-9]){2}")).isValid(cpf))
			throw new DataFieldException();
	}

	private void checarEmail(String email) {
		if (!EmailValidator.getInstance().isValid(email))
			throw new DataFieldException();
	}

	private void checarTelefone(String telefone) {
		if (!(new RegexValidator("\\(([0-9]{2})\\)([0-9]){5}-([0-9]){4}")).isValid(telefone))
			throw new DataFieldException();
	}

	private void checarCargo(String cargo) {
		if (!cargo.equals("admin") && !cargo.equals("cashier") && !cargo.equals("stock"))
			throw new DataFieldException();
	}

	private void checarSalario(String salario) {
		if ((new BigDecimal(salario)).compareTo(new BigDecimal("1000")) < 0)
			throw new DataFieldException();
	}

	private void checarInfo(FuncionarioPOJO f) {
		checarNome(f.getNome());
		checarCpf(f.getCpf());
		checarEmail(f.getEmail());
		checarTelefone(f.getTelefone());
		checarCargo(f.getCargo());
		checarSalario(f.getSalario());
	}
}