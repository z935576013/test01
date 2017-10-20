package com.merak.lzpt.constants;

public enum AuthorityConstants {

	DEFAULT_AUTHORITY("DEFAULT");

	private String authorityCode;

	private AuthorityConstants(String authorityCode) {
		this.authorityCode = authorityCode;
	}

	/**
	 * @return the authorityCode
	 */
	public String getAuthorityCode() {
		return authorityCode;
	}

	/**
	 * @param authorityCode
	 *            the authorityCode to set
	 */
	public void setAuthorityCode(String authorityCode) {
		this.authorityCode = authorityCode;
	}

}
