#!/bin/sh
screen -dmS new_screen sh
screen -S new_screen -X stuff "nohup java -jar /home/ec2-user/stocktake/target/jhipster-sample-application-react-0.0.1-SNAPSHOT.jar ^M"