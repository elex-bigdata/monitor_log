#! /bin/sh
#dir=`dirname $0`
dir=`dirname "${BASH_SOURCE-$0}"`
dir=`cd "$bin">/dev/null; pwd`
echo "start rest server monitor log ..."
nohup java -cp $dir/../lib:$dir/../conf:$dir/../conf/*:$dir/../lib/*:$dir/../lib/monitor_log-0.1.jar com.elex.bigdata.web.RestMain > $dir/monitor_hbase.log 2>&1 &
echo "start rest server monitor log finish."
