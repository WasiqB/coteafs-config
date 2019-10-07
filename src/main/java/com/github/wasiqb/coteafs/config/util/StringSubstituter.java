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
package com.github.wasiqb.coteafs.config.util;

import static java.lang.System.getenv;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.text.StringSubstitutor.replaceSystemProperties;

/**
 * @author Wasiq Bhamla
 * @since 05-Oct-2019
 */
public final class StringSubstituter {
    /**
     * @author Wasiq Bhamla
     * @since 05-Oct-2019
     * @param value
     * @return string
     */
    public static String interpolate (final String value) {
        String res = value;
        if (isEmpty (res)) {
            res = EMPTY;
        }
        if (res.startsWith ("${")) {
            final String key = res.substring (2, res.length () - 1);
            final String env = getenv (key);
            if (isEmpty (env)) {
                res = replaceSystemProperties (res);
                if (isEmpty (res)) {
                    res = EMPTY;
                }
            } else {
                res = env;
            }
        }
        return res;
    }

    private StringSubstituter () {
        // Util class.
    }
}