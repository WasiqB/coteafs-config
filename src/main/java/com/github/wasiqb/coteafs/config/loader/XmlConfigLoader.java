/**
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
import static com.github.wasiqb.coteafs.error.util.ErrorUtil.fail;
import static java.text.MessageFormat.format;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.wasiqb.coteafs.config.error.CoteafsConfigFileNotFoundError;
import com.github.wasiqb.coteafs.config.error.CoteafsConfigNotLoadedError;

/**
 * @author Wasiq Bhamla
 * @since 07-Sep-2019
 */
class XmlConfigLoader extends AbstractConfigLoader {
    /**
     * @author Wasiq Bhamla
     * @since 07-Sep-2019
     * @param path
     */
    XmlConfigLoader (final String path) {
        super (path);
    }

    /*
     * (non-Javadoc)
     * @see @see
     * com.github.wasiqb.coteafs.config.loader.IConfigSource#load(java.lang.Class)
     */
    @Override
    public <T> T load (final Class<T> cls) {
        try {
            final XmlMapper mapper = new XmlMapper ();
            mapper.setPropertyNamingStrategy (SNAKE_CASE);
            return mapper.readValue (new File (this.path), cls);
        } catch (final FileNotFoundException e) {
            final String MSG = "{0} not found.";
            fail (CoteafsConfigFileNotFoundError.class, format (MSG, this.path), e);
        } catch (final IOException e) {
            fail (CoteafsConfigNotLoadedError.class, "Error loading config file.", e);
        }
        return null;
    }
}