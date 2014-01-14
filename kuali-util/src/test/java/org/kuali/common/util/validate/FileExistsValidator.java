package org.kuali.common.util.validate;

import java.io.File;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FileExistsValidator implements ConstraintValidator<Exists, File> {

	@Override
	public void initialize(Exists constraintAnnotation) {
	}

	@Override
	public boolean isValid(File file, ConstraintValidatorContext context) {
		if (file == null) {
			return true;
		} else {
			return file.exists();
		}
	}

}
