# coteafs-config
At times we need to have YAML config support in our framework or any Java project. To leverage this, coteafs-config, a lite weight library comes to the rescue. It is a very flexible library which we can use in our day-to-day projects by simply defining a basic POJO for our setting requirements. Let's see how.

# Usage:
You can use this library in your project by adding below snippet in your project pom file.
```xml
  <dependency>
    <groupId>com.github.wasiqb.coteafs</groupId>
    <artifactId>configs</artifactId>
    <version>1.8.0</version>
  </dependency>
```

If you are not using Maven, you can download the latest version of Jar files from [Release](https://github.com/WasiqB/coteafs-config/releases) tab.

# How to use?
To use this library, you need to define your project specific config's POJO in whichever package as you may wish. You need to define your POJO's fields using [lowerCaseNamingConvention](http://wiki.c2.com/?LowerCamelCase), and then you must generate getter-setters for each fields instead of writing it manually. This will speed up your work.

_**ServiceSetting.java**_
```java
public class ServiceSetting {
	private int	port;
	private String	type;
	private String	url;

	public int getPort () {
		return this.port;
	}

	public String getType () {
		return this.type;
	}

	public String getUrl () {
		return this.url;
	}

	public void setPort (final int port) {
		this.port = port;
	}

	public void setType (final String type) {
		this.type = type;
	}

	public void setUrl (final String url) {
		this.url = url;
	}
}
```
Once this is done, you now need to define your POJO's corresponding YAML config file for your project, as shown below,

The config file path can be provided in system property with key `coteafs.config`, if it is not defined, then by default, library will look for file named `test-config.yaml` under `src/test/resources` directory.

_**test-config.yaml**_
```yaml
url: http://localhost
port: 8080
type: SOAP
```
You need to make sure that keys should be **lower_case_with_words_seperated_with_underscore**.

Once this is done, now you are ready to use this library, as shown,
```java
  . . .
  // This class can be any class you define for your config file.
  ServiceSetting setting = ConfigLoader.settings (ServiceSetting.class);
  String url = setting.getUrl ();
  . . .
```

# Future features:
* Support to JSON and XML formats.
