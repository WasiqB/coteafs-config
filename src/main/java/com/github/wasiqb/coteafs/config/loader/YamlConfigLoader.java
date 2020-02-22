/*
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

import static com.github.wasiqb.coteafs.config.util.StringSubstituter.interpolate;
import static com.github.wasiqb.coteafs.error.util.ErrorUtil.fail;
import static java.util.Objects.requireNonNull;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import com.github.wasiqb.coteafs.config.error.ConfigNotLoadedError;
import com.github.wasiqb.coteafs.config.error.ConfigNotSavedError;
import com.google.common.base.CaseFormat;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.introspector.PropertyUtils;
import org.yaml.snakeyaml.nodes.ScalarNode;

/**
 * @author wasiq.bhamla
 * @since 09-Jun-2017 4:36:27 PM
 */
class YamlConfigLoader extends AbstractConfigLoader {
    YamlConfigLoader(final String path) {
        super(path);
    }

    @Override
    public <T> void create(final T data) {
        try (final FileWriter out = new FileWriter(this.path)) {
            final Yaml yaml = new Yaml();
            yaml.dump(requireNonNull(data, "Object can't be null."), out);
        } catch (final IOException e) {
            fail(ConfigNotSavedError.class, "Error saving config file.", e);
        }
    }

    @Override
    public <T> T load(final Class<T> cls) {
        checkAndCreateDefaultConfig(cls);
        try (final FileInputStream in = new FileInputStream(this.path)) {
            final Constructor ctor = new Constructor(cls) {
                @Override
                protected String constructScalar(final ScalarNode node) {
                    return interpolate(node.getValue());
                }
            };
            final PropertyUtils propertyUtils = new PropertyUtils() {
                @Override
                public Property getProperty(final Class<?> obj, final String name) {
                    String propertyName = name;
                    if (propertyName.indexOf('_') > -1) {
                        propertyName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,
                            propertyName);
                    }
                    return super.getProperty(obj, propertyName);
                }
            };
            ctor.setPropertyUtils(propertyUtils);
            final Yaml yaml = new Yaml(ctor);
            return yaml.load(in);
        } catch (final Exception e) {
            fail(ConfigNotLoadedError.class, "Error loading config file.", e);
        }
        return null;
    }
}