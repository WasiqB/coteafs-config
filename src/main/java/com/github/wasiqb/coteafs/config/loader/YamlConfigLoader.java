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

import static com.fasterxml.jackson.databind.PropertyNamingStrategy.SNAKE_CASE;
import static com.github.wasiqb.coteafs.error.util.ErrorUtil.fail;
import static com.github.wasiqb.coteafs.error.util.ErrorUtil.handleError;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.wasiqb.coteafs.config.error.CoteafsConfigNotLoadedError;
import com.github.wasiqb.coteafs.config.factory.YamlConfigFactory;

/**
 * @author wasiq.bhamla
 * @since 09-Jun-2017 4:36:27 PM
 */
class YamlConfigLoader extends AbstractConfigLoader {
    private final ObjectMapper mapper;

    YamlConfigLoader (final String path) {
        super (path);
        final YAMLFactory factory = new YamlConfigFactory ();
        this.mapper = new ObjectMapper (factory);
        this.mapper.setPropertyNamingStrategy (SNAKE_CASE);
    }

    /*
     * (non-Javadoc)
     * @see @see
     * com.github.wasiqb.coteafs.config.loader.IConfigSource#create(java.lang.Class)
     */
    @Override
    public <T> void create (final Class<T> cls) {
        try {
            final T obj = cls.newInstance ();
            this.mapper.writeValue (new File (this.path), obj);
        } catch (final IOException | InstantiationException | IllegalAccessException e) {
            handleError ("com.github.wasiqb", e).forEach (System.err::println);
        }
    }

    @Override
    public <T> T load (final Class<T> cls) {
        try {
            checkAndCreateDefaultConfig (cls);
            return this.mapper.readValue (new File (this.path), cls);
        } catch (final IOException e) {
            fail (CoteafsConfigNotLoadedError.class, "Error loading config file.", e);
        }
        return null;
    }
}