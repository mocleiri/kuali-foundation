package org.kuali.common.util.encrypt.jasypt.provider;

import org.kuali.common.util.encrypt.jasypt.JasyptContext;

import com.google.common.base.Optional;

public interface JasyptContextProvider {

	Optional<JasyptContext> getJasyptContext();

}
