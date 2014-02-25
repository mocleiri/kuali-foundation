package org.kuali.common.devops.cache;

import static org.apache.commons.io.FileUtils.touch;
import static org.kuali.common.util.base.Exceptions.illegalState;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.kuali.common.http.model.HttpRequestResult;
import org.kuali.common.http.model.HttpWaitResult;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;

public class UrlFileCache extends PersistentCache<File, HttpWaitResult> {

	private final ObjectMapper mapper = getObjectMapper();

	@Override
	public void store(File file, HttpWaitResult result) {
		try {
			touch(file);
			mapper.writer().withDefaultPrettyPrinter().writeValue(file, result);
		} catch (IOException e) {
			throw illegalState(e);
		}
	}

	@Override
	public HttpWaitResult load(File file) {
		try {
			return mapper.readValue(file, HttpWaitResult.class);
		} catch (IOException e) {
			throw illegalState(e);
		}
	}

	protected ObjectMapper getObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new GuavaModule());
		mapper.addMixInAnnotations(HttpWaitResult.class, MixIn.class);
		return mapper;
	}

	private abstract class MixIn {
		@JsonIgnore
		private List<HttpRequestResult> requestResults;

	}

}
