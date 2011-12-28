namespace java com.riffbit.lucien.thrift

service Lucien {

    void ping(),

    string get(1:string bucket, 2:string key),

    void put(1:string bucket, 2:string key, 3:string value),
    
    string run(1:string script, 2:list<string> arguments)
}