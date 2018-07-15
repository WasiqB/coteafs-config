/**
 * Copyright (c) 2018-2020, Wasiq Bhamla.
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

import static com.google.common.truth.Truth.assertThat;

import org.testng.annotations.Test;

import com.github.wasiqb.coteafs.config.loader.ConfigFactory;

/**
 * @author wasiq.bhamla
 * @since Jul 15, 2018 4:30:35 PM
 */
public class ConfigFactoryTest {
	/**
	 * @author wasiq.bhamla
	 * @since Jul 15, 2018 4:41:34 PM
	 */
	@Test
	public void testYamlConfigFile () {
		final ConfigFactory config = ConfigFactory.newInstance ()
				.withDefault ("test-config.yaml")
				.load ();
		assertThat (config.getString ("service.api_url")).isEqualTo ("http://localhost");
		assertThat (config.getString ("service.api_port")).isEqualTo ("8080");
		assertThat (config.getString ("service.api_type")).isEqualTo ("SOAP");
	}

	/**
	 * @author wasiq.bhamla
	 * @since Jul 15, 2018 5:17:03 PM
	 */
	@Test
	public void testYamlConfigFileWithSystemProperties () {
		final ConfigFactory config = ConfigFactory.newInstance ()
				.withDefault ("test-config.yaml")
				.load ();
		System.setProperty ("test1", "dynamic");
		assertThat (config.getString ("service.api_url")).isEqualTo ("http://localhost");
		assertThat (config.getString ("service.api_port")).isEqualTo ("8080");
		assertThat (config.getString ("service.api_type")).isEqualTo ("SOAP");
		assertThat (config.getString ("test1")).isEqualTo ("dynamic");
	}
}