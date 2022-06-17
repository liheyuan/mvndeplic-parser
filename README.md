# mvndeplic-parser
An parser for license-maven-plugin's output THIRD-PARTY.txt


## How to get THIRD-PARTY.txt
```shell
# First run
mvn license:add-third-party

# Get file under target/generated-sources/license/THIRD-PARTY.txt
```
## How to get using maven
```
<!-- https://mvnrepository.com/artifact/com.coder4/mvndeptree-flatparser -->
<dependency>
    <groupId>com.coder4</groupId>
    <artifactId>mvndeplic-parser</artifactId>
    <version>0.0.2</version>
</dependency>

```

## How to use mvndeplic-parser
```
// str mode
LicParseResult res = LicParser.parse(str);

// File mode
LicParseResult res = LicParser.parse(file);

// Get result
for (LicParseItem item : res.getItems()) {
    // dep item
    System.out.println(item.getDep().getGroupId());
    System.out.println(item.getDep().getArtifactId());
    System.out.println(item.getDep().getVersion());
    
    // licenses
    for (String lic: item.getLicenses()) {
        System.out.println(lic);
    }
}
```
