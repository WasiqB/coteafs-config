package com.github.wasiqb.coteafs.config.loader;

import static com.fasterxml.jackson.databind.PropertyNamingStrategy.SNAKE_CASE;
import static com.github.wasiqb.coteafs.error.util.ErrorUtil.fail;
import static com.github.wasiqb.coteafs.error.util.ErrorUtil.handleError;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.dataformat.javaprop.JavaPropsMapper;
import com.github.wasiqb.coteafs.config.error.CoteafsConfigNotLoadedError;

/**
 * @author Wasiq Bhamla
 * @since 07-Sep-2019
 */
class PropertiesConfigLoader extends AbstractConfigLoader {
    private final JavaPropsMapper mapper;

    /**
     * @param path Path
     * @author Wasiq Bhamla
     * @since 06-Sep-2019
     */
    PropertiesConfigLoader (final String path) {
        super (path);
        this.mapper = new JavaPropsMapper ();
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