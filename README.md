# Maven Dependency and Repository
Add the following code to your pom.xml
```xml
<repository>
    <id>github</id>
    <url>https://maven.pkg.github.com/afutik/SQLite-Tools</url>
</repository>
```
```xml
<dependency>
    <groupId>com.afutik</groupId>
    <artifactId>sqlite-tools</artifactId>
    <version>1.1.3</version>
</dependency>
```

# Guide
This Repository provides the tools that simplify work with Database and other objects. 
## Creating Config
Before creating database you need to create a configuration file "sqlite.yml" in resources directory. You can use the class 'ConfigLoader' to load a different configuration file if needed.
```yml
database-path: src/main/resources/database/database.db
schema-path: src/main/resources/database/schema.sql
```
You also need to create schema.sql at the specified schema-path.

## Connecting to the Database
In order to connect to database, use the following code. The method connect() requires the path to sqlite jdbc class, install the package with jdbc driver and write path to this class in the method. The Database file will be created automatically.
If you need to change config path or name, use 'SQLdatabase.setConfigPath()', by default path is 'src/main/resources/sqlite.yml'.
```java
public class Main {
  public static void main(String[] args) {
    SQLiteDabase.setConfigPath("config.yml");

    SQLiteDabase.connect(SQLITE JDBC CLASS); /* Can take logger */
  }
}
```

## Creating Entity
Define your entity classes with variables in the same order as the schema. Use the @Key(name = "schema_name") annotation to map the variable to a schema column. The @Value(name = "schema_name") annotation is optional but useful if you need a different name for a variable. The use of @Key is mandatory.
```java
@AllArgsConstructor /* Useful annotations from lombok */
@Getter
public class SimpleEntity implements Entity {
  @Key private String name;
}
```

## Creating Repository
The project has the annotation '@Repo' for creating repositories.
```java
@Repo(entityClass = SimpleEntity.class, table = "some table")
public class SimpleRepository extends Repository<SimpleEntity> {}
```
The repository includes methods for executing a queries ->
```
create()
update()
getById()
getManyById()
delete()
```


## Executing Queries
To execute an update or a query, use: ->
```
SQLiteDatabase.statement.executeQuery(String query)
```

To prepare a statement with parameters, use ->
```
SQLiteDatabase.prepareStatement(String query, Object... args)
```
 
