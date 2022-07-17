package com.zensar.dto;

public class PanelMember {

	private Integer employeeId;
	private String name;
	private String type;
	private String location;

	public PanelMember(Integer employeeId, String name, String type, String location) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.type = type;
		this.location = location;
	}

	public PanelMember() {
		super();

	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "PanelMemberEntity [employeeId=" + employeeId + ", name=" + name + ", type=" + type + ", location="
				+ location + "]";
	}

	@Override
	public boolean equals(Object obj) {
		PanelMember panelMember = (PanelMember) obj;
		if (this.name.equals(panelMember.getName())) {
			return true;
		}
		return false;
	}
}
