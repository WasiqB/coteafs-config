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

import java.beans.IntrospectionException;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.introspector.PropertyUtils;

import com.github.wasiqb.coteafs.config.constants.Constants;
import com.github.wasiqb.coteafs.config.exception.CoteafsConfigFileNotFoundException;
import com.github.wasiqb.coteafs.config.exception.CoteafsConfigNotLoadedException;
import com.google.common.base.CaseFormat;

/**
 * @author wasiq.bhamla
 * @since 09-Jun-2017 4:36:27 PM
 */
public final class ConfigLoader {
	private static final Logger log;

	static {
		log = LogManager.getLogger (ConfigLoader.class);
	}

	/**
	 * @author wasiq.bhamla
	 * @since 09-Jun-2017 4:36:34 PM
	 * @param cls
	 * @return settings
	 */
	public static <T> T settings (final Class <T> cls) {
		log.trace ("Loading settings from Config file...");
		final T settings = loadSettings (cls);
		return settings;
	}

	/**
	 * @author wasiq.bhamla
	 * @since 09-Jun-2017 5:00:19 PM
	 * @param cls
	 * @return settings
	 */
	@SuppressWarnings ("unchecked")
	private static <T> T loadSettings (final Class <T> cls) {
		final String path = System.getProperty (Constants.CONFIG_KEY, Constants.DEFAULT_CONFIG_FILE);
		final URL url = ConfigLoader.class	.getClassLoader ()
											.getResource (path);
		if (url == null) {
			final String msg = "%s not found.";
			throw new CoteafsConfigFileNotFoundException (String.format (msg, path));
		}
		final File file = new File (url.getPath ());
		final String msg = "Started Loading coteafs Settings from location [%s]...";
		log.trace (String.format (msg, path));
		final Constructor ctor = new Constructor (cls);
		final PropertyUtils propertyUtils = new PropertyUtils () {
			@Override
			public Property getProperty (final Class <? extends Object> type, final String name)
					throws IntrospectionException {
				String prop = name;
				if (prop.indexOf ('_') > -1) {
					prop = CaseFormat.LOWER_UNDERSCORE.to (CaseFormat.LOWER_CAMEL, prop);
				}
				return super.getProperty (type, prop);
			}
		};
		ctor.setPropertyUtils (propertyUtils);
		final Yaml yaml = new Yaml (ctor);
		T result = null;
		try (final InputStream in = new FileInputStream (file)) {
			result = (T) yaml.load (in);
		}
		catch (final Exception e) {
			throw new CoteafsConfigNotLoadedException ("Error loading config file.", e);
		}
		log.trace ("coteafs settings loaded successfully...");
		return result;
	}
}