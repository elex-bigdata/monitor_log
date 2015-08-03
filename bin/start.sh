#! /bin/sh
dir=`dirname $0`
nohup java -cp $dir/lib:$dir/conf:$dir/conf/*:$dir/lib/*:$dir/lib/phoenix-rest-server-post.jar web.RestMain > $dir/monitor_hbase.log 2>&1 &
