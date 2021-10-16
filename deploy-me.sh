#!/bin/bash

cd /home/ec2-user/bankbank/ && git pull && cp /home/ec2-user/application.properties /home/ec2-user/bankbank/src/main/resources && cd /home/ec2-user/bankbank && mvn clean dependency:copy-dependencies -DoutputDirectory=lib antrun:run package && cp /home/ec2-user/bankbank/target/bankbank.jar /home/ec2-user