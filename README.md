# differ
Compares 2 or more files with one another, either via specific naming or to find duplicates.

## DualCompare (-d|-dual-compare)
Compare 2 files with one another, line per line, and find different lines.
Not to be combined with TreeCompare (see further).
```
java -jar differ.jar -d file1.csv [file1_diff.csv]
```

## TreeCompare (-t|-tree-compare)
Comb through a folder (or working dir) to find duplicate files.
Not to be combined with DualCompare (see before).
```
java -jar differ.jar -t [/path/to/examine]
```

## Run PITest mutation coverage

``` shell
mvn -DwithHistory test-compile org.pitest:pitest-maven:mutationCoverage
```