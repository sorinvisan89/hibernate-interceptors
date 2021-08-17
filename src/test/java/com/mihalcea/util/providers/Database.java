package com.mihalcea.util.providers;

import com.mihalcea.util.ReflectionUtils;

public enum Database {
	HSQLDB(HSQLDBDataSourceProvider.class),
	POSTGRESQL(PostgreSQLDataSourceProvider.class),
	MYSQL(MySQLDataSourceProvider.class);

	private Class<? extends DataSourceProvider> dataSourceProviderClass;

	Database(Class<? extends DataSourceProvider> dataSourceProviderClass) {
		this.dataSourceProviderClass = dataSourceProviderClass;
	}

	public DataSourceProvider dataSourceProvider() {
		return ReflectionUtils.newInstance(dataSourceProviderClass.getName());
	}
}
