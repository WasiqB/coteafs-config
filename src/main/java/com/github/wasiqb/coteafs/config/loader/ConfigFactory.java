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
package com.github.wasiqb.coteafs.config.loader;

import java.io.File;

import org.apache.commons.configuration2.CompositeConfiguration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.JSONConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.SystemConfiguration;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.YAMLConfiguration;
import org.apache.commons.configuration2.builder.ReloadingFileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.FileBasedBuilderParameters;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.lang3.StringUtils;

/**
 * @author wasiq.bhamla
 * @since Jul 14, 2018 5:34:20 PM
 */
public class ConfigFactory {
	private static CompositeConfiguration	config;
	private static final ConfigFactory		instance	= new ConfigFactory ();
	private static String					key;
	private static String					value;

	static {
		key = "coteafs.config";
		value = "/test-config.properties";
	}

	/**
	 * @author wasiq.bhamla
	 * @since Jul 15, 2018 4:35:58 PM
	 * @return instance
	 */
	public static ConfigFactory newInstance () {
		return instance;
	}

	private static ReloadingFileBasedConfigurationBuilder <FileBasedConfiguration> getConfigBuilder (
			final String path) {
		final File file = new File (path);
		final String fileName = file.getName ();
		final FileBasedBuilderParameters params = new Parameters ().fileBased ()
				.setReloadingDetectorFactory (new DefaultReloadingDetectorFactory ())
				.setFile (file);
		ReloadingFileBasedConfigurationBuilder <FileBasedConfiguration> builder = null;
		if (fileName.endsWith ("properties")) {
			builder = new ReloadingFileBasedConfigurationBuilder <FileBasedConfiguration> (
					PropertiesConfiguration.class);
		}
		else if (fileName.endsWith ("xml")) {
			builder = new ReloadingFileBasedConfigurationBuilder <FileBasedConfiguration> (
					XMLConfiguration.class);
		}
		else if (fileName.endsWith ("json")) {
			builder = new ReloadingFileBasedConfigurationBuilder <FileBasedConfiguration> (
					JSONConfiguration.class);
		}
		else {
			builder = new ReloadingFileBasedConfigurationBuilder <FileBasedConfiguration> (
					YAMLConfiguration.class);
		}
		builder.configure (params);
		return builder;
	}

	/**
	 * @author wasiq.bhamla
	 * @since Jul 14, 2018 7:17:35 PM
	 * @param configKey
	 * @return string
	 */
	public String getString (final String configKey) {
		return getString (configKey, StringUtils.EMPTY);
	}

	/**
	 * @author wasiq.bhamla
	 * @since Jul 14, 2018 7:17:45 PM
	 * @param configKey
	 * @param defaultValue
	 * @return string
	 */
	public String getString (final String configKey, final String defaultValue) {
		return config.getString (configKey, defaultValue);
	}

	/**
	 * @author wasiq.bhamla
	 * @since Jul 15, 2018 3:13:45 PM
	 * @return config
	 */
	public ConfigFactory load () {
		final String path = System.getProperty (key, value);
		final ReloadingFileBasedConfigurationBuilder <FileBasedConfiguration> builder = getConfigBuilder (
				path);
		try {
			config = new CompositeConfiguration ();
			config.addConfiguration (new SystemConfiguration ());
			config.addConfiguration (builder.getConfiguration ());
		}
		catch (final ConfigurationException e) {
			e.printStackTrace ();
		}
		return instance;
	}

	/**
	 * @author wasiq.bhamla
	 * @since Jul 15, 2018 3:13:35 PM
	 * @param def
	 * @return config
	 */
	public ConfigFactory withDefault (final String def) {
		value = def;
		return instance;
	}

	/**
	 * @author wasiq.bhamla
	 * @since Jul 15, 2018 3:13:20 PM
	 * @param configKey
	 * @return config
	 */
	public ConfigFactory withKey (final String configKey) {
		key = configKey;
		return instance;
	}
}