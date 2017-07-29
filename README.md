
## Affinity Propagation on Spark

Affinity Propagation (AP), a graph clustering algorithm based on the concept of "message passing" between data points. Unlike clustering algorithms such as k-means or k-medoids, AP does not require the number of clusters to be determined or estimated before running it. AP is developed by Frey and Dueck. Please refer to the paper[1].

Affinity Propagation on Spark implements Affinity Propagation algorithm on cluster computing system Spark. By leveraging computing cluster, you can run this clustering algorithm on large-scale data sets.

[1] Brendan J. Frey; Delbert Dueck (2007). "Clustering by passing messages between data points". Science. 315 (5814): 972-976 [PDF](http://www.psi.toronto.edu/affinitypropagation/FreyDueckScience07.pdf)

## Build

Currently it supports Spark 2.2.0. It also has tested on Spark 1.6.0 before. You can build the package by using sbt.

    sbt/sbt assembly

## Spark package

You can simply use the affinity propagation on Spark by importing the spark package [SparkAffinityPropagation](https://spark-packages.org/package/viirya/SparkAffinityPropagation)

    bin/spark-shell --packages viirya:SparkAffinityPration:1.0

## API

`AffinityPropagation` class provides the API for performing clustering. You can set the maximum iteration numbers for Affinity Propagation by calling `setMaxIterations`. The graph used as input to Affinity Propagation algorithm is represented as a RDD of similarities between vertices. The vertices are represented by theirs ids in `Long` type. The similarities are `Double` type.

For example, a RDD of similarities from local data can be initialized:

    val similarities = Seq[(Long, Long, Double)]((0, 1, -8.2), (0, 3, -5.8), (1, 2, -0.4))
    val rdd = sc.parallelize(similarities, 2)

Then, use this RDD as input to `AffinityPropagation.run`:

    val ap = new AffinityPropagation()
    val similaritiesWithPreferneces = ap.determinePreferences(rdd)
    val model = ap
      .setMaxIterations(30)
      .run(similaritiesWithPreferneces)

## Example

In the unit test `org.viirya.AffinityPropagationSuite`, you can find how to run it with its Scala API.

For Java users, `org.viirya.exemplar.JavaAffinityPropagation` provides Java example to run this clustering algorithm. 

