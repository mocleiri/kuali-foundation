package org.kuali.common.jdbc.vendor.spring;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.kuali.common.jdbc.vendor.model.Vendor;
import org.kuali.common.jdbc.vendor.model.VendorBase;
import org.kuali.common.jdbc.vendor.model.Vendors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseVendorConfig {

	@Bean
	public Map<Vendor, VendorBase> vendorBaseMap() {
		VendorBase oracle = new VendorBase(Vendor.ORACLE, Vendors.Oracle.DBA, Vendors.Oracle.DRIVER);
		VendorBase mysql = new VendorBase(Vendor.MYSQL, Vendors.MySql.DBA, Vendors.MySql.DRIVER);
		Map<Vendor, VendorBase> map = new HashMap<Vendor, VendorBase>();
		map.put(Vendor.ORACLE, oracle);
		map.put(Vendor.MYSQL, mysql);
		return Collections.unmodifiableMap(map);
	}

}
