# differ
Compares 2 or more files with one another, either via specific naming or to find duplicates.

## DualCompare (-d|--dual-compare)
Compare 2 files with one another, line per line, and find different lines.
Not to be combined with TreeCompare (see further).
```
java -jar differ.jar -d file1.txt [file1_diff.txt]
```

### CSVCompare (-c|--csv)
##### To be implemented
To be used in combination with DualCompare.
Compare 2 files with one another, line per line, finding which line has the largest number of similar elements.
Not to be combined with TreeCompare (see further).
```
java -jar differ.jar -dc file1.csv [file1_diff.csv]
```

#### Show all similarities (-a|--all)
##### To be implemented
To be used in combination with CSVCompare. List all possibly related records.
```
java -jar differ.jar -dca file1.csv [file1_diff.csv]
```

## TreeCompare (-t|--tree-compare)
##### To be implemented
Comb through a folder (or working dir) to find duplicate files.
Not to be combined with DualCompare (see before).
```
java -jar differ.jar -t [/path/to/examine]
```

## Print help message (-h|--help)
Provides a short guide to help the user.
```
java -jar differ.jar -h
```

## Run PITest mutation coverage

``` shell
mvn -DwithHistory test-compile org.pitest:pitest-maven:mutationCoverage
```