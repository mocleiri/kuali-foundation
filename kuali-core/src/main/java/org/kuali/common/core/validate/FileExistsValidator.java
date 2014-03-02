package org.kuali.common.core.validate;

import java.io.File;

import javax.validation.ConstraintValidatorContext;

import org.kuali.common.core.validate.annotation.Exists;

public class FileExistsValidator extends AbstractExistsValidator<File> {

	@Override
	public void initialize(Exists constraintAnnotation) {

	}

	@Override
	public boolean isValid(File file, ConstraintValidatorContext context) {
		if (file == null) {
			return true;
		} else {
			return validate(file.exists(), file.getAbsolutePath(), context);
		}
	}

}
