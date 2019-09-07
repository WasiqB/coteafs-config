package com.github.wasiqb.coteafs.config.loader;

import static com.fasterxml.jackson.databind.PropertyNamingStrategy.SNAKE_CASE;
import static com.github.wasiqb.coteafs.error.util.ErrorUtil.fail;
import static java.text.MessageFormat.format;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.fasterxml.jackson.dataformat.javaprop.JavaPropsMapper;
import com.github.wasiqb.coteafs.config.error.CoteafsConfigFileNotFoundError;
import com.github.wasiqb.coteafs.config.error.CoteafsConfigNotLoadedError;

/**
 * @author Wasiq Bhamla
 * @since 07-Sep-2019
 */
class PropertiesConfigLoader extends AbstractConfigLoader {
    /**
     * @param path Path
     * @author Wasiq Bhamla
     * @since 06-Sep-2019
     */
    PropertiesConfigLoader (final String path) {
        super (path);
    }

    @Override
    public <T> T load (final Class<T> cls) {
        try {
            final JavaPropsMapper mapper = new JavaPropsMapper ();
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
