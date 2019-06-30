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

import static com.github.wasiqb.coteafs.error.util.ErrorUtil.fail;

import java.io.InputStream;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.introspector.PropertyUtils;

import com.github.wasiqb.coteafs.config.error.ConfigNotSupportedError;
import com.github.wasiqb.coteafs.config.error.CoteafsConfigFileNotFoundError;
import com.github.wasiqb.coteafs.config.error.CoteafsConfigNotLoadedError;
import com.google.common.base.CaseFormat;

/**
 * @author wasiq.bhamla
 * @since 09-Jun-2017 4:36:27 PM
 */
public class ConfigLoader {
	/**
	 * @author wasiq.bhamla
	 * @since Jul 29, 2017 7:23:21 PM
	 * @return instance.
	 */
	public static ConfigLoader settings () {
		return new ConfigLoader ();
	}

	private String	key;
	private String	value;

	/**
	 * @author wasiq.bhamla
	 * @since 27-Jun-2017 7:22:17 PM
	 */
	private ConfigLoader () {
		this.key = "coteafs.config";
		this.value = "/test-config.yaml";
	}

	/**
	 * @author wasiq.bhamla
	 * @since 09-Jun-2017 4:36:34 PM
	 * @param cls
	 * @return settings
	 */
	public <T> T load (final Class <T> cls) {
		return loadSettings (cls);
	}

	/**
	 * @author wasiq.bhamla
	 * @since Jul 29, 2017 7:20:50 PM
	 * @param def
	 * @return instance
	 */
	public ConfigLoader withDefault (final String def) {
		this.value = def;
		return this;
	}

	/**
	 * @author wasiq.bhamla
	 * @since Jul 29, 2017 7:14:50 PM
	 * @param configKey
	 * @return instance
	 */
	public ConfigLoader withKey (final String configKey) {
		this.key = configKey;
		return this;
	}

	/**
	 * @author wasiq.bhamla
	 * @since 09-Jun-2017 5:00:19 PM
	 * @param cls
	 */
	private <T> T loadSettings (final Class <T> cls) {
		final String path = System.getProperty (this.key, this.value);
		if (!path.toLowerCase ()
			.endsWith ("yaml")
			&& !path.toLowerCase ()
				.endsWith ("yml")) {
			fail (ConfigNotSupportedError.class,
				"This config file is not supported. Config file should be Yaml format only.");
		}
		try (final InputStream in = getClass ().getResourceAsStream (path)) {
			if (in != null) {
				final Constructor ctor = new Constructor (cls);
				final PropertyUtils propertyUtils = new PropertyUtils () {
					@Override
					public Property getProperty (final Class <? extends Object> obj,
						final String name) {
						String propertyName = name;
						if (propertyName.indexOf ('_') > -1) {
							propertyName = CaseFormat.LOWER_UNDERSCORE.to (CaseFormat.LOWER_CAMEL,
								propertyName);
						}
						return super.getProperty (obj, propertyName);
					}
				};
				ctor.setPropertyUtils (propertyUtils);
				final Yaml yaml = new Yaml (ctor);
				return yaml.load (in);
			}
		}
		catch (final Exception e) {
			fail (CoteafsConfigNotLoadedError.class, "Error loading config file.", e);
		}
		final String MSG = "%s not found.";
		fail (CoteafsConfigFileNotFoundError.class, String.format (MSG, path));
		return null;
	}
}