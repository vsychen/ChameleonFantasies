package br.com.cf.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CargoValidator implements ConstraintValidator<Cargo, String> {

	@Override
	public boolean isValid(String cargo, ConstraintValidatorContext cxt) {
		return cargo.equals("admin") || cargo.equals("cashier") || cargo.equals("stock");
	}
}