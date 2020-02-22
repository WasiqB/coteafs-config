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

/**
 * @author Wasiq Bhamla
 * @since 06-Sep-2019
 */
public interface IConfigSource {
    /**
     * @param <T>
     * @param data
     * @author Wasiq Bhamla
     * @since 05-Oct-2019
     */
    <T> void create(T data);

    /**
     * @param <T> any object
     * @param cls class
     * @return config object
     * @author Wasiq Bhamla
     * @since 06-Sep-2019
     */
    <T> T load(final Class<T> cls);
}