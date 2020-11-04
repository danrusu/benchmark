## benchmark 

#### Maven archetype
```bash
mvn archetype:generate \
   -DinteractiveMode=false \
   -DarchetypeGroupId=org.openjdk.jmh \
   -DarchetypeArtifactId=jmh-java-benchmark-archetype \
   -DgroupId=org.sample \
   -DartifactId=test \
   -Dversion=1.0
```

#### Execution
```bash
java -jar target/benchmarks.jar ParallelStreamBenchmark
```
Or you can run the BenchmarkRunner class.


