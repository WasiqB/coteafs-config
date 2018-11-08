/**
w * Copyright (c) 2017, Wasiq Bhamla.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.wasiqb.coteafs.config;

/**
 * @author wasiq.bhamla
 * @since 09-Jun-2017 6:40:36 PM
 */
public class ServiceSetting {
	private int		apiPort;
	private String	apiType;
	private String	apiUrl;

	/**
	 * @author wasiq.bhamla
	 * @since 27-Jun-2017 7:15:29 PM
	 * @return the apiPort
	 */
	public int getApiPort () {
		return this.apiPort;
	}

	/**
	 * @author wasiq.bhamla
	 * @since 27-Jun-2017 7:15:29 PM
	 * @return the apiType
	 */
	public String getApiType () {
		return this.apiType;
	}

	/**
	 * @author wasiq.bhamla
	 * @since 27-Jun-2017 7:15:29 PM
	 * @return the apiUrl
	 */
	public String getApiUrl () {
		return this.apiUrl;
	}

	/**
	 * @author wasiq.bhamla
	 * @since 27-Jun-2017 7:15:29 PM
	 * @param apiPort
	 *            the apiPort to set
	 */
	public void setApiPort (final int apiPort) {
		this.apiPort = apiPort;
	}

	/**
	 * @author wasiq.bhamla
	 * @since 27-Jun-2017 7:15:29 PM
	 * @param apiType
	 *            the apiType to set
	 */
	public void setApiType (final String apiType) {
		this.apiType = apiType;
	}

	/**
	 * @author wasiq.bhamla
	 * @since 27-Jun-2017 7:15:29 PM
	 * @param apiUrl
	 *            the apiUrl to set
	 */
	public void setApiUrl (final String apiUrl) {
		this.apiUrl = apiUrl;
	}
}