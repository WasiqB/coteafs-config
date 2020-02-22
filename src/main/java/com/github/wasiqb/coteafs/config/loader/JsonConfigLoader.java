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

import static com.fasterxml.jackson.databind.PropertyNamingStrategy.SNAKE_CASE;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.wasiqb.coteafs.config.factory.JsonConfigFactory;

/**
 * @author Wasiq Bhamla
 * @since 06-Sep-2019
 */
class JsonConfigLoader extends AbstractConfigLoader {
    /**
     * @param path path
     * @author Wasiq Bhamla
     * @since 06-Sep-2019
     */
    JsonConfigLoader(final String path) {
        super(path);
        final JsonFactory factory = new JsonConfigFactory();
        this.mapper = new ObjectMapper(factory);
        this.mapper.setPropertyNamingStrategy(SNAKE_CASE);
    }
}