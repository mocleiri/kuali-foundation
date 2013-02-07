package org.kuali.core.db.torque.pojo;

import java.util.Arrays;
import java.util.List;

import org.kuali.core.db.torque.service.ImpexContext;
import org.springframework.beans.BeanUtils;

public class ImpexUtils {

	public static ImpexContext clone(ImpexContext source) {
		ImpexContext target = new ImpexContext();
		BeanUtils.copyProperties(source, target);
		return target;
	}

	public static ImpexContext clone(ImpexContext source, String include) {
		return clone(source, Arrays.asList(include));
	}

	public static ImpexContext clone(ImpexContext source, List<String> includes) {
		ImpexContext clone = clone(source);
		clone.setTableIncludes(includes);
		clone.setViewIncludes(includes);
		clone.setSequenceIncludes(includes);
		return clone;
	}

}
