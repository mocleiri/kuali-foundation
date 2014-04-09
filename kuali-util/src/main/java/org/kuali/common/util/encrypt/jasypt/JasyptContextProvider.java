package org.kuali.common.util.encrypt.jasypt;

import com.google.common.base.Optional;

public interface JasyptContextProvider {

	Optional<JasyptContext> getJasyptContext();

}
