/*
 * Copyright (c) 2017, Wasiq Bhamla.
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

import static com.github.wasiqb.coteafs.config.loader.ConfigLoader.settings;
import static com.google.common.truth.Truth.assertThat;
import static java.text.MessageFormat.format;

import java.io.File;

import com.github.wasiqb.coteafs.config.error.ConfigNotLoadedError;
import com.github.wasiqb.coteafs.config.error.ConfigNotSupportedError;
import org.testng.annotations.Test;

/**
 * @author wasiq.bhamla
 * @since 09-Jun-2017 6:39:43 PM
 */
public class ConfigLoaderTest {
    private static void deleteIfExists(final String fileName) {
        final File config = new File(format("./src/test/resources/{0}", fileName));
        config.delete();
    }

    /**
     * @author wasiq.bhamla
     * @since 09-Jun-2017 6:48:05 PM
     */
    @Test(expectedExceptions = ConfigNotLoadedError.class)
    public void testConfigWhenFileIsIncorrect() {
        settings().withKey("coteafs.failed.setting")
            .withDefault("test-config-malformed.yaml")
            .load(ServiceSetting.class);
    }

    /**
     * @author Wasiq Bhamla
     * @since 06-Oct-2019
     */
    @Test
    public void testConfigWhenJsonFileNotExsits() {
        final ServiceSetting setting = settings().withDefault("sconfig.json")
            .load(ServiceSetting.class);
        assertThat(setting.getApiUrl()).isEqualTo("https://localhost");
        assertThat(setting.getApiPort()).isEqualTo(3000);
        assertThat(setting.getApiType()).isEqualTo("Rest");
        deleteIfExists("sconfig.json");
    }

    /**
     * @author Wasiq Bhamla
     * @since 06-Oct-2019
     */
    @Test
    public void testConfigWhenPropertiesFileNotExsits() {
        final ServiceSetting setting = settings().withDefault("sconfig.properties")
            .load(ServiceSetting.class);
        assertThat(setting.getApiUrl()).isEqualTo("https://localhost");
        assertThat(setting.getApiPort()).isEqualTo(3000);
        assertThat(setting.getApiType()).isEqualTo("Rest");
        deleteIfExists("sconfig.properties");
    }

    /**
     * @author Wasiq Bhamla
     * @since 06-Oct-2019
     */
    @Test
    public void testConfigWhenXmlFileNotExsits() {
        final ServiceSetting setting = settings().withDefault("sconfig.xml")
            .load(ServiceSetting.class);
        assertThat(setting.getApiUrl()).isEqualTo("https://localhost");
        assertThat(setting.getApiPort()).isEqualTo(3000);
        assertThat(setting.getApiType()).isEqualTo("Rest");
        deleteIfExists("sconfig.xml");
    }

    /**
     * @author wasiq.bhamla
     * @since 09-Jun-2017 6:47:13 PM
     */
    @Test
    public void testConfigWhenYamlFileNotExsits() {
        final ServiceSetting setting = settings().withDefault("sconfig.yaml")
            .load(ServiceSetting.class);
        assertThat(setting.getApiUrl()).isEqualTo("https://localhost");
        assertThat(setting.getApiPort()).isEqualTo(3000);
        assertThat(setting.getApiType()).isEqualTo("Rest");
        deleteIfExists("sconfig.yaml");
    }

    /**
     * @author Wasiq Bhamla
     * @since 07-Sep-2019
     */
    @Test(expectedExceptions = ConfigNotSupportedError.class)
    public void testIncorrectConfigFormat() {
        settings().withDefault("test-config.txt")
            .load(ServiceSetting.class);
    }

    /**
     * @author Wasiq Bhamla
     * @since 06-Sep-2019
     */
    @Test
    public void testJsonConfigLoaderWithServiceSettings() {
        final ServiceSetting setting = settings().withDefault("test-config.json")
            .load(ServiceSetting.class);
        assertThat(setting.getApiUrl()).isEqualTo("http://localhost");
        assertThat(setting.getApiPort()).isEqualTo(8080);
        assertThat(setting.getApiType()).isEqualTo("SOAP");
    }

    /**
     * @author Wasiq Bhamla
     * @since 07-Sep-2019
     */
    @Test
    public void testPropertiesConfigLoaderWithServiceSettings() {
        final ServiceSetting setting = settings().withDefault("test-config.properties")
            .load(ServiceSetting.class);
        assertThat(setting.getApiUrl()).isEqualTo("http://localhost");
        assertThat(setting.getApiPort()).isEqualTo(8080);
        assertThat(setting.getApiType()).isEqualTo("SOAP");
    }

    /**
     * @author Wasiq Bhamla
     * @since 07-Sep-2019
     */
    @Test
    public void testXmlConfigLoaderWithServiceSettings() {
        final ServiceSetting setting = settings().withDefault("test-config.xml")
            .load(ServiceSetting.class);
        assertThat(setting.getApiUrl()).isEqualTo("http://localhost");
        assertThat(setting.getApiPort()).isEqualTo(8080);
        assertThat(setting.getApiType()).isEqualTo("SOAP");
    }

    /**
     * @author wasiq.bhamla
     * @since 09-Jun-2017 6:44:40 PM
     */
    @Test
    public void testYamlConfigLoaderWithServiceSettings() {
        final MainSetting setting = settings().load(MainSetting.class);
        assertThat(setting.getService("s1")
            .getApiUrl()).isEqualTo("http://localhost");
        assertThat(setting.getService("s2")
            .getApiPort()).isEqualTo(8080);
        assertThat(setting.getService("s3")
            .getApiType()).isEqualTo("SOAP");
    }
}