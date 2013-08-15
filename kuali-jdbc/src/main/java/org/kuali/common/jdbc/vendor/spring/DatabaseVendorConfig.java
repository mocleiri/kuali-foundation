package org.kuali.common.jdbc.vendor.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.common.jdbc.vendor.model.DatabaseVendor;
import org.kuali.common.jdbc.vendor.model.Vendor;
import org.kuali.common.jdbc.vendor.model.VendorBase;
import org.kuali.common.jdbc.vendor.model.Vendors;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SpringServiceConfig.class })
public class DatabaseVendorConfig {

	private static final String VENDOR_KEY = "db.vendor";

	@Autowired
	EnvironmentService env;

	@Bean
	public Vendor databaseVendorEnum() {
		String vendor = env.getString(VENDOR_KEY);
		return Vendor.valueOf(vendor.toUpperCase());
	}

	@Bean
	public DatabaseVendor databaseVendor() {
		VendorBase base = vendorBaseMap().get(databaseVendorEnum());
		return new DatabaseVendor(null, null, null, null, null);
	}

	@Bean
	public Map<Vendor, VendorBase> vendorBaseMap() {
		VendorBase oracle = new VendorBase(Vendor.ORACLE, Vendors.Oracle.DBA, Vendors.Oracle.DRIVER);
		VendorBase mysql = new VendorBase(Vendor.MYSQL, Vendors.MySql.DBA, Vendors.MySql.DRIVER);
		Map<Vendor, VendorBase> map = new HashMap<Vendor, VendorBase>();
		map.put(Vendor.ORACLE, oracle);
		map.put(Vendor.MYSQL, mysql);
		return Collections.unmodifiableMap(map);
	}

	@Bean
	public Map<Vendor, List<String>> vendorSqlKeysMap() {
		List<String> oracle = CollectionUtils.combineStrings(getAdminKeys(), getLiquibaseKeys(), getOracleKeys());
		List<String> mysql = CollectionUtils.combineStrings(getAdminKeys(), getLiquibaseKeys());
		Map<Vendor, List<String>> map = new HashMap<Vendor, List<String>>();
		map.put(Vendor.ORACLE, getPrefixedKeys(Vendor.ORACLE, oracle));
		map.put(Vendor.MYSQL, getPrefixedKeys(Vendor.MYSQL, mysql));
		return Collections.unmodifiableMap(map);
	}

	protected List<String> getPrefixedKeys(Vendor vendor, List<String> keys) {
		List<String> prefixedKeys = new ArrayList<String>();
		for (String key : keys) {
			String prefixedKey = vendor.getCode() + "." + key;
			prefixedKeys.add(prefixedKey);
		}
		return Collections.unmodifiableList(prefixedKeys);
	}

	protected List<String> getAdminKeys() {
		return Collections.unmodifiableList(Arrays.asList("validate", "create", "drop"));
	}

	protected List<String> getLiquibaseKeys() {
		return Collections.unmodifiableList(Arrays.asList("liquibase.create", "liquibase.create"));
	}

	protected List<String> getOracleKeys() {
		return Collections.unmodifiableList(Arrays.asList("killAndDrop", "killAndDrop.rds", "schemaStats"));
	}

}
