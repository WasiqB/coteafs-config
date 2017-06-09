/**
 * Copyright (c) 2017, Wasiq Bhamla.
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
	private int		port;
	private String	type;
	private String	url;

	/**
	 * @author wasiq.bhamla
	 * @since 09-Jun-2017 6:41:17 PM
	 * @return the port
	 */
	public int getPort () {
		return this.port;
	}

	/**
	 * @author wasiq.bhamla
	 * @since 09-Jun-2017 6:41:17 PM
	 * @return the type
	 */
	public String getType () {
		return this.type;
	}

	/**
	 * @author wasiq.bhamla
	 * @since 09-Jun-2017 6:41:17 PM
	 * @return the url
	 */
	public String getUrl () {
		return this.url;
	}

	/**
	 * @author wasiq.bhamla
	 * @since 09-Jun-2017 6:41:17 PM
	 * @param port
	 *            the port to set
	 */
	public void setPort (final int port) {
		this.port = port;
	}

	/**
	 * @author wasiq.bhamla
	 * @since 09-Jun-2017 6:41:17 PM
	 * @param type
	 *            the type to set
	 */
	public void setType (final String type) {
		this.type = type;
	}

	/**
	 * @author wasiq.bhamla
	 * @since 09-Jun-2017 6:41:17 PM
	 * @param url
	 *            the url to set
	 */
	public void setUrl (final String url) {
		this.url = url;
	}
}