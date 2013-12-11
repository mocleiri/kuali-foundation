package org.kuali.common.util.spring.env.annotation;

public interface EnvAdapter<ModelType, EnvType> {

	ModelType convert(EnvType e);
}
