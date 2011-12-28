# Lucien Key-Value Store #

Lucien is a proof of concept key value store.

I wanted to try out the idea of a coprocessor (i.e. stored procedures) to a key value model.

Many times, interacting with a kv-store (i.e. NoSQL) has a lot of latency.
If you wanted to grab a post with all it's comments, a lot of requests could occur to display a view.
If the database was smart enough to get all the data you need to display the page in one request, there would be a lot less latency.

The idea of having the database doing the work is often seen as a bad idea for scalability.
This may be true and something to consider.

This implementation is terrible, and should not be used in production, it is only a proof of concept to see what this model could look like.

## Buckets ##

A bucket is a namespace of data, a key/value pair only belongs to one bucket.
A keys are unique in a bucket but not necessarily duplicated across buckets.

Keys are always strings, values are always JSON objects.

    client.put("someBucket", "some_key", "{'a':'value'}")
    
    client.get("someBucket", "some_key")
    
## Backing Store ##
The backing store at this time is Google's [LevelDB](http://code.google.com/p/leveldb/) which I've used in a few projects with success.
The backing store is plug-able and there is nothing about this design that couldn't be done with another backing store.

## Scripting ##
This is the cool part, you can write a script that you can run later to collect data and send results.

    Write more here...
    
## Clients ###

    More stuff..

## Built On ##
Lucien is built on:

*   Java
*   LevelDB
*   Thrift


It is Apache Licensed.

## Contact ##

If you have any questions or comments, contact me at stephen.holiday@gmail.com

