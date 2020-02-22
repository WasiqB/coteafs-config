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
import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.wasiqb.coteafs.config.error.ConfigNotLoadedError;
import com.github.wasiqb.coteafs.config.error.ConfigNotSavedError;
import com.github.wasiqb.coteafs.config.error.DefaultConfigNotCreatedError;

/**
 * @author Wasiq Bhamla
 * @since 06-Sep-2019
 */
class AbstractConfigLoader implements IConfigSource {
    protected final String path;
    ObjectMapper mapper;

    AbstractConfigLoader(final String path) {
        this.path = path;
    }

    @Override
    public <T> void create(final T data) {
        try {
            this.mapper.writeValue(new File(this.path),
                requireNonNull(data, "Object can't be null."));
        } catch (final IOException e) {
            fail(ConfigNotSavedError.class, "Error saving config file.", e);
        }
    }

    @Override
    public <T> T load(final Class<T> cls) {
        try {
            checkAndCreateDefaultConfig(cls);
            return this.mapper.readValue(new File(this.path), cls);
        } catch (final IOException e) {
            fail(ConfigNotLoadedError.class, "Error loading config file.", e);
        }
        return null;
    }

    protected <T> void checkAndCreateDefaultConfig(final Class<T> cls) {
        final File config = new File(this.path);
        if (!config.exists()) {
            try {
                final T data = cls.newInstance();
                create(data);
            } catch (final InstantiationException | IllegalAccessException e) {
                fail(DefaultConfigNotCreatedError.class, "Error loading config file.", e);
            }
        }
    }
}