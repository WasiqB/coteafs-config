/*
 * Copyright (c) 2020, Wasiq Bhamla.
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
package com.github.wasiqb.coteafs.config;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Wasiq Bhamla
 * @since 05-Jan-2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MainSetting {
    private ServiceSetting              service;
    private Map<String, ServiceSetting> services;

    /**
     * @param name name of service
     *
     * @return service
     *
     * @author Wasiq Bhamla
     * @since 05-Jan-2020
     */
    public ServiceSetting getService (final String name) {
        return this.services.get (name);
    }
}