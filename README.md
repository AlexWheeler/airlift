# Airlift

A Clojure library for building .jars to export Microsft Sql Server tables as csv files to your SFTP server.

## Usage

Will eventually host jar, but for now assuming you're using leiningen, build .jar:

`lein uberjar`

Fill out config.edn file with database / sftp credentials and place in same directory as .jar.

```edn
{
 :sftp {
        :username  ""
        :password ""
        :host ""
        }
 :db {
      :username ""
      :password ""
      :address "localhost"
      :port "1433"
      :name ""
      :tables [
               ""
               ""
               ""
               ""
               ]
      }
 }
```

Configure task to run `java -jar path_to_jar`.

## License

Distributed under the Eclipse Public License version 1.0 or any later version.
