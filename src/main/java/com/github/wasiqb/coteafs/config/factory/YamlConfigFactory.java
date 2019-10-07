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
package com.github.wasiqb.coteafs.config.factory;

import java.io.CharArrayReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactoryBuilder;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import com.github.wasiqb.coteafs.config.parser.YamlConfigParser;

/**
 * @author Wasiq Bhamla
 * @since 05-Oct-2019
 */
public class YamlConfigFactory extends YAMLFactory {
    private static final long serialVersionUID = 3637276386422339393L;

    /**
     * @author Wasiq Bhamla
     * @since 05-Oct-2019
     */
    public YamlConfigFactory () {
        super ();
    }

    /**
     * @author Wasiq Bhamla
     * @since 05-Oct-2019
     * @param oc
     */
    public YamlConfigFactory (final ObjectCodec oc) {
        super (oc);
    }

    /**
     * @author Wasiq Bhamla
     * @since 05-Oct-2019
     * @param src
     * @param oc
     */
    public YamlConfigFactory (final YAMLFactory src, final ObjectCodec oc) {
        super (src, oc);
    }

    /**
     * @author Wasiq Bhamla
     * @since 05-Oct-2019
     * @param b
     */
    public YamlConfigFactory (final YAMLFactoryBuilder b) {
        super (b);
    }

    /*
     * (non-Javadoc)
     * @see @see
     * com.fasterxml.jackson.dataformat.yaml.YAMLFactory#_createParser(byte[], int,
     * int, com.fasterxml.jackson.core.io.IOContext)
     */
    @Override
    protected YAMLParser _createParser (final byte [] data, final int offset, final int len, final IOContext ctxt)
        throws IOException {
        return new YamlConfigParser (ctxt, _getBufferRecycler (), this._parserFeatures, this._yamlParserFeatures,
            this._objectCodec, _createReader (data, offset, len, null, ctxt));
    }

    /*
     * (non-Javadoc)
     * @see @see
     * com.fasterxml.jackson.dataformat.yaml.YAMLFactory#_createParser(char[], int,
     * int, com.fasterxml.jackson.core.io.IOContext, boolean)
     */
    @Override
    protected YAMLParser _createParser (final char [] data, final int offset, final int len, final IOContext ctxt,
        final boolean recyclable) throws IOException {
        return new YamlConfigParser (ctxt, _getBufferRecycler (), this._parserFeatures, this._yamlParserFeatures,
            this._objectCodec, new CharArrayReader (data, offset, len));
    }

    /*
     * (non-Javadoc)
     * @see @see
     * com.fasterxml.jackson.dataformat.yaml.YAMLFactory#_createParser(java.io.
     * InputStream, com.fasterxml.jackson.core.io.IOContext)
     */
    @Override
    protected YAMLParser _createParser (final InputStream in, final IOContext ctxt) throws IOException {
        return new YamlConfigParser (ctxt, _getBufferRecycler (), this._parserFeatures, this._yamlParserFeatures,
            this._objectCodec, _createReader (in, null, ctxt));
    }

    /*
     * (non-Javadoc)
     * @see @see
     * com.fasterxml.jackson.dataformat.yaml.YAMLFactory#_createParser(java.io.
     * Reader, com.fasterxml.jackson.core.io.IOContext)
     */
    @Override
    protected YAMLParser _createParser (final Reader r, final IOContext ctxt) throws IOException {
        return new YamlConfigParser (ctxt, _getBufferRecycler (), this._parserFeatures, this._yamlParserFeatures,
            this._objectCodec, r);
    }
}