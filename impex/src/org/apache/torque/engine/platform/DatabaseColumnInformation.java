package org.apache.torque.engine.platform;

public class DatabaseColumnInformation {

	protected String name;
	protected Integer sqlType;
	protected Integer size;
	protected Integer decimalDigits;
	protected Integer nullType;
	protected String defValue;
	protected String comment;
	
	protected String exportColumnType;
	protected String exportSize;
	protected String exportScale;
	protected String exportDefaultValue;
	
	protected boolean notNull;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSqlType() {
		return sqlType;
	}
	public void setSqlType(Integer sqlType) {
		this.sqlType = sqlType;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Integer getDecimalDigits() {
		return decimalDigits;
	}
	public void setDecimalDigits(Integer decimalDigits) {
		this.decimalDigits = decimalDigits;
	}
	public Integer getNullType() {
		return nullType;
	}
	public void setNullType(Integer nullType) {
		this.nullType = nullType;
	}
	public String getDefValue() {
		return defValue;
	}
	public void setDefValue(String defValue) {
		this.defValue = defValue;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getExportColumnType() {
		return exportColumnType;
	}
	public void setExportColumnType(String exportColumnType) {
		this.exportColumnType = exportColumnType;
	}
	public String getExportSize() {
		return exportSize;
	}
	public void setExportSize(String exportSize) {
		this.exportSize = exportSize;
	}
	public String getExportScale() {
		return exportScale;
	}
	public void setExportScale(String exportScale) {
		this.exportScale = exportScale;
	}
	public boolean isNotNull() {
		return notNull;
	}
	public void setNotNull(boolean notNull) {
		this.notNull = notNull;
	}
	public String getExportDefaultValue() {
		return exportDefaultValue;
	}
	public void setExportDefaultValue(String exportDefaultValue) {
		this.exportDefaultValue = exportDefaultValue;
	}
	
}
