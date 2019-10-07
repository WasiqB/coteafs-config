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
package com.github.wasiqb.coteafs.config.parser;

import static com.github.wasiqb.coteafs.config.util.StringSubstituter.interpolate;

import java.io.IOException;
import java.io.Reader;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;

/**
 * @author Wasiq Bhamla
 * @since 05-Oct-2019
 */
public class YamlConfigParser extends YAMLParser {
    /**
     * @author Wasiq Bhamla
     * @since 05-Oct-2019
     * @param ctxt
     * @param br
     * @param parserFeatures
     * @param formatFeatures
     * @param codec
     * @param reader
     */
    public YamlConfigParser (final IOContext ctxt, final BufferRecycler br, final int parserFeatures,
        final int formatFeatures, final ObjectCodec codec, final Reader reader) {
        super (ctxt, br, parserFeatures, formatFeatures, codec, reader);
    }

    /*
     * (non-Javadoc)
     * @see @see com.fasterxml.jackson.dataformat.yaml.YAMLParser#getText()
     */
    @Override
    public String getText () throws IOException {
        return interpolate (super.getText ());
    }
}