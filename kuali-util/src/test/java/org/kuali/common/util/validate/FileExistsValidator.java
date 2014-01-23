package org.kuali.common.util.validate;

import java.io.File;

import javax.validation.ConstraintValidatorContext;

public class FileExistsValidator extends AbstractExistsValidator<File> {

	@Override
	public void initialize(Exists constraintAnnotation) {
	}

	@Override
	public boolean isValid(File file, ConstraintValidatorContext context) {
		if (file == null) {
			return true;
		} else {
			boolean valid = file.exists();
			doValidCheck(valid, file.getAbsolutePath(), context);
			return valid;
		}
	}

}
