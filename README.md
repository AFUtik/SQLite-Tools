# Maven Dependency and Repository
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
    <version>1.1.2</version>
</dependency>
```

# Guide
This Repository provides the tools that simplify work with Database and other objects. 
## Config
Before creating database you need to create config "sqlite.yml" in resources directory. You can use the class 'ConfigLoader' to load another config.
```yml
database-path: src/main/resources/database/database.db
schema-path: src/main/resources/database/schema.sql
```
After you need to create schema.sql in your schema-path.

## Connecting
In order to connect to database, use the following code. The Database file will be created automatically.
If you need to change config path or name, use 'SQLdatabase.setConfigPath()', by default path is 'src/main/resources/sqlite.yml'.
```java
public class Main {
  public static void main(String[] args) {
    SQLiteDabase.setConfigPath("config.yml");

    SQLiteDabase.connect(); /* Can take logger */
  }
}
```

## Entity
You need place the variables in the same order as the schema.
Use @Key(name = name in schema) or @Value(name = name in schema) If you need another name for a variable.
Using of Key is required.
Using of @Value is optional.
```java
@AllArgsConstructor /* Useful annotations from lombok */
@Getter
public class SimpleEntity implements Entity {
  @Key private String name;
}
```

## Repository
The project has the annotation '@Repo' for creating repositories.
```java
@Repo(entityClass = SimpleEntity.class, table = "some table")
public class SimpleRepository extends Repository<SimpleEntity> {}
```
The repository has such methods for executing a query ->
```
create()
update()
getById()
getManyById()
delete()
```


## Queries
To execute an update or a query, use: ->
```
SQLiteDatabase.statement.executeQuery(String query)
```

To prepare a statement with parameters, use ->
```
SQLiteDatabase.prepareStatement(String query, Object... args)
```
 
