/*
 * Copyright (c) 2019, Wasiq Bhamla.
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
import static java.lang.System.getProperty;
import static java.lang.System.getenv;
import static java.text.MessageFormat.format;
import static org.apache.commons.lang3.StringUtils.isEmpty;

import com.github.wasiqb.coteafs.config.error.ConfigNotSupportedError;

/**
 * @author Wasiq Bhamla
 * @since 06-Sep-2019
 */
public class ConfigLoader {
    /**
     * @return config
     * @author Wasiq Bhamla
     * @since 06-Sep-2019
     */
    public static ConfigLoader settings() {
        return new ConfigLoader();
    }

    private final String dir;
    private       String key;
    private       String value;

    private ConfigLoader() {
        this.dir = format("{0}/src/test/resources/", getProperty("user.dir"));
        this.key = "coteafs.config";
        this.value = "test-config.yaml";
    }

    /**
     * @param <T> any object
     * @param cls class
     * @return config object
     * @author Wasiq Bhamla
     * @since 06-Sep-2019
     */
    public <T> T load(final Class<T> cls) {
        final IConfigSource config = getConfig();
        return config.load(cls);
    }

    /**
     * @param defaultValue default value
     * @return current instance
     * @author Wasiq Bhamla
     * @since 06-Sep-2019
     */
    public ConfigLoader withDefault(final String defaultValue) {
        this.value = defaultValue;
        return this;
    }

    /**
     * @param configKey config key
     * @return current instance
     * @author Wasiq Bhamla
     * @since 06-Sep-2019
     */
    public ConfigLoader withKey(final String configKey) {
        this.key = configKey;
        return this;
    }

    private IConfigSource getConfig() {
        final String path = getConfigPath();
        final String ext = path.substring(path.lastIndexOf('.') + 1);
        switch (ext.toLowerCase()) {
            case "yaml":
            case "yml":
                return new YamlConfigLoader(path);
            case "json":
                return new JsonConfigLoader(path);
            case "properties":
                return new PropertiesConfigLoader(path);
            case "xml":
                return new XmlConfigLoader(path);
            default:
                fail(ConfigNotSupportedError.class,
                    format("This config file format [{0}] is not supported.", ext));
        }
        return null;
    }

    private String getConfigPath() {
        final String envPath = getenv(this.key);
        return isEmpty(envPath)
            ? getProperty(this.key, format("{0}{1}", this.dir, this.value))
            : envPath;
    }
}