#!/bin/bash

cd /home/ec2-user/bankbank/ && git pull && cp /home/ec2-user/application.properties /home/ec2-user/bankbank/src/main/resources && cp /home/ec2-user/bankbank/target/bankbank.jar /home/ec2-user