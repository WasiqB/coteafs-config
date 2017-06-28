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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import org.testng.annotations.Test;

import com.github.wasiqb.coteafs.config.constants.Constants;
import com.github.wasiqb.coteafs.config.exception.CoteafsConfigFileNotFoundException;
import com.github.wasiqb.coteafs.config.exception.CoteafsConfigNotLoadedException;
import com.github.wasiqb.coteafs.config.loader.ConfigLoader;

/**
 * @author wasiq.bhamla
 * @since 09-Jun-2017 6:39:43 PM
 */
public class ConfigLoaderTest {
	/**
	 * @author wasiq.bhamla
	 * @since 28-Jun-2017 4:05:51 PM
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testConfigLoaderInitialization () throws InstantiationException, IllegalAccessException,
			NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		final Class <ConfigLoader> clazz = ConfigLoader.class;
		final Constructor <ConfigLoader> constructor = clazz.getDeclaredConstructor ();
		assertThat (Modifier.isPrivate (constructor.getModifiers ()))	.named ("ctor modifier")
																		.isTrue ();
		constructor.setAccessible (true);
		constructor.newInstance ();
		constructor.setAccessible (false);
	}

	/**
	 * @author wasiq.bhamla
	 * @since 09-Jun-2017 6:44:40 PM
	 */
	@Test (alwaysRun = true)
	public void testConfigLoaderWithServiceSettings () {
		final ServiceSetting setting = ConfigLoader.settings (ServiceSetting.class);
		assertThat (setting.getApiUrl ()).isEqualTo ("http://localhost");
		assertThat (setting.getApiPort ()).isEqualTo (8080);
		assertThat (setting.getApiType ()).isEqualTo ("SOAP");
	}

	/**
	 * @author wasiq.bhamla
	 * @since 09-Jun-2017 6:48:05 PM
	 */
	@Test (expectedExceptions = CoteafsConfigNotLoadedException.class)
	public void testConfigWhenFileIsIncorrect () {
		System.setProperty (Constants.CONFIG_KEY, "test-config-malformed.yaml");
		ConfigLoader.settings (ServiceSetting.class);
	}

	/**
	 * @author wasiq.bhamla
	 * @since 09-Jun-2017 6:47:13 PM
	 */
	@Test (expectedExceptions = CoteafsConfigFileNotFoundException.class)
	public void testConfigWhenFileNotExsits () {
		System.setProperty (Constants.CONFIG_KEY, "config.yaml");
		ConfigLoader.settings (ServiceSetting.class);
	}
}