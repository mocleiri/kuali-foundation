package org.kuali.common.util.spring.env.adapter;

public interface EnvAdapter<ModelType, EnvType> {

	ModelType convert(EnvType e);
}
