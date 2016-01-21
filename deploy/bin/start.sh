#! /bin/sh
#dir=`dirname $0`
dir=`dirname "${BASH_SOURCE-$0}"`
dir=`cd "$bin">/dev/null; pwd`
echo "start rest server monitor log ..."
cd $dir/../../
mvn package -DskipTests
cp target/monitor_log-0.1.jar deploy/lib/
nohup java -cp $dir/../lib:$dir/../conf:$dir/../conf/*:$dir/../lib/*:$dir/../lib/monitor_log-0.1.jar com.elex.bigdata.web.RestMain >  /dev/null 2>&1 &
echo "start rest server monitor log finish."
