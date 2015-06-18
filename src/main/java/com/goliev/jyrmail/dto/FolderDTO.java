package com.goliev.jyrmail.dto;

public class FolderDTO {

	

	private long directoryId;
	private String name;
	
	public FolderDTO() {

	}

	public FolderDTO(String name) {
		this.name = name;
	}

	public long getDirectoryId() {
		return directoryId;
	}

	public void setDirectoryId(long directoryId) {
		this.directoryId = directoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "FolderDTO [directoryId=" + directoryId + ", name=" + name + "]";
	}

	

}
