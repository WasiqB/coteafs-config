# coteafs-config
This is a lite weight library to allow YAML file configuration for any project using POJO's.

# Usage:
You can use this library in your project by adding below snippet in your project pom file.
```xml
  <dependency>
    <groupId>com.github.wasiqb.coteafs</groupId>
    <artifactId>configs</artifactId>
    <version>0.0.1</version>
  </dependency>
```

If you are not using Maven, you can download the latest version of Jar files from [Release](https://github.com/WasiqB/coteafs-config/releases) tab.

# How to use?
To use this library, you need to define your project specific config's POJO in whichever package as you may wish.

_**ServiceSetting.java**_
```java
public class ServiceSetting {
	private int		port;
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
Once this is done, you now need to define your YAML config for your project, as shown below,

_**test-config.yaml**_
```yaml
url: http://localhost
port: 8080
type: SOAP
```

Once this is done, now you are ready to use this library, as shown,
```java
  . . .
  ServiceSetting setting = ConfigLoader.settings (ServiceSetting.class);
  String url = setting.getUrl ();
  . . .
```
