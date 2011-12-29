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
There is a sample [python client](https://github.com/sholiday/lucien-python-client) available, the example below shows some usage.

It uses [thrift](http://thrift.apache.org/) so many languages can be used.

## Example ##
Here's an example of what Lucien can currently do:

    # Simple put
    client.put("hey","there", "{'hey':'there'}")

    # Simple get
    print client.get("hey","there")


    # Basic Script
    script = '''result = {"success": True, "answer" : 42}'''
    v = {"engine":"python", "script":script}
    client.put("_scripts","the_ultimate_method",json.dumps(v))

    # Check that it's in the database
    print client.get("_scripts","the_ultimate_method")

    # Check run the method with no arguments
    print client.run("the_ultimate_method", [])


    # Let's do a more advanced query
    post = {
        "title" : "Some entry",
        "content": "It's really cool",
        "comments" : ["comment_1","comment_2"]
    }
    client.put("posts","post_1",json.dumps(post))

    comment_1 = {
        "user_key":"bob",
        "post":"some_post_key",
        "comment": "This is an insightful comment!"
        }
    client.put("comments","comment_1",json.dumps(comment_1))

    comment_2 = {
        "user_key":"dave",
        "post":"some_post_key",
        "comment": "This is a boring comment!"
    }
    client.put("comments","comment_2",json.dumps(comment_2))

    bob = {"name":"Bob The Guy", "email":"bob@gmail.com"}
    client.put("users","bob",json.dumps(bob))

    dave = {"name":"David King", "email":"dave@gmail.com"}
    client.put("users","dave",json.dumps(dave))

    script = """
    posts = buckets.getBucket("posts")
    comments = buckets.getBucket("comments")
    users = buckets.getBucket("users")

    post_1 = dict(posts.get("post_1"))

    result = post_1

    comment_list = list()
    for comment_key in post_1['comments']:
        comment = dict(comments.get(comment_key))
        user_key = comment['user_key']
        user = dict(users.get(user_key))
        comment.update(user)

        comment_list.append(comment)

    result['comments'] = comment_list
        """
    v = {"engine":"python", "script":script}
    client.put("_scripts","render_post",json.dumps(v))

    # Check run the method with the post as an argument
    print client.run("render_post", ["post_1"])

## Built On ##
Lucien is built on:

*   Java
*   [LevelDB](http://code.google.com/p/leveldb/)
*   [Thrift](http://thrift.apache.org/)


It is Apache Licensed.

## Contact ##

If you have any questions or comments, contact me at stephen.holiday@gmail.com

