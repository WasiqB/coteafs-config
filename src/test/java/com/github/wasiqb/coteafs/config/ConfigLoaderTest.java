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

import static com.google.common.truth.Truth.assertThat;

import org.testng.annotations.Test;

import com.github.wasiqb.coteafs.config.error.CoteafsConfigFileNotFoundError;
import com.github.wasiqb.coteafs.config.error.CoteafsConfigNotLoadedError;
import com.github.wasiqb.coteafs.config.loader.ConfigLoader;

/**
 * @author wasiq.bhamla
 * @since 09-Jun-2017 6:39:43 PM
 */
public class ConfigLoaderTest {
	/**
	 * @author wasiq.bhamla
	 * @since 09-Jun-2017 6:44:40 PM
	 */
	@Test
	public void testConfigLoaderWithServiceSettings () {
		final ServiceSetting setting = ConfigLoader	.settings ()
													.load (ServiceSetting.class);
		assertThat (setting.getApiUrl ()).isEqualTo ("http://localhost");
		assertThat (setting.getApiPort ()).isEqualTo (8080);
		assertThat (setting.getApiType ()).isEqualTo ("SOAP");
	}

	/**
	 * @author wasiq.bhamla
	 * @since 09-Jun-2017 6:48:05 PM
	 */
	@Test (expectedExceptions = CoteafsConfigNotLoadedError.class)
	public void testConfigWhenFileIsIncorrect () {
		ConfigLoader.settings ()
					.withKey ("coteafs.failed.setting")
					.withDefault ("/test-config-malformed.yaml")
					.load (ServiceSetting.class);
	}

	/**
	 * @author wasiq.bhamla
	 * @since 09-Jun-2017 6:47:13 PM
	 */
	@Test (expectedExceptions = CoteafsConfigFileNotFoundError.class)
	public void testConfigWhenFileNotExsits () {
		ConfigLoader.settings ()
					.withDefault ("/sconfig.yaml")
					.load (ServiceSetting.class);
	}
}