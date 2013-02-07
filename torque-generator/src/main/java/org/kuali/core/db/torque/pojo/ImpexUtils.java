package org.kuali.core.db.torque.pojo;

import org.kuali.core.db.torque.service.ImpexContext;
import org.springframework.beans.BeanUtils;

public class ImpexUtils {

	public static ImpexContext clone(ImpexContext source) {
		ImpexContext target = new ImpexContext();
		BeanUtils.copyProperties(source, target);
		return target;
	}

}
